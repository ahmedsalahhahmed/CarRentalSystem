package carrentalsystem;

import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.*;

/**
 *
 * @author VIRTUOSO
 */
public class CarRentalSystem {

    private Map<String, Vehicle> vehicles;
    private Map<String, Customer> customers;
    private Map<String, Rental> rentals;
    private Set<String> availableVehicleIds;

    public CarRentalSystem() {
        vehicles = new HashMap<>();
        customers = new HashMap<>();
        rentals = new HashMap<>();
        availableVehicleIds = new HashSet<>();
    }
    private static final String vehiclesFile = "vehicles.txt";
    private static final String customersFIle = "customers.txt";
    private static final String rentalsFile = "rentals.txt";

    public void saveDataToTxt() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(vehiclesFile))) {
            for (Vehicle vehicle : vehicles.values()) {
                writer.write(vehicle.dataToString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving vehicles");
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customersFIle))) {
            for (Customer customer : customers.values()) {
                writer.write(customer.dataToString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving customers");
        }

       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rentalsFile))) {
            for (Rental rental : rentals.values()) {
                writer.write(rental.dataToString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving rentals");
        }
        System.out.println("Data saved to .txt files successfully.");
    }

    public void loadDataFromTxt() {

        try (BufferedReader reader = new BufferedReader(new FileReader(vehiclesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];

                String id = data[1];
                String brand = data[2];
                String model = data[3];
                int year = Integer.parseInt(data[4]);
                double price = Double.parseDouble(data[5]);
                boolean available = Boolean.parseBoolean(data[6]);
                String color = data[7];

                Vehicle vehicle = null;
                switch (type) {
                    case "Car":
                        int seatingCapacity = Integer.parseInt(data[8]);
                        String carType = data[9];
                        vehicle = new Car(seatingCapacity, carType, id, brand, model, year, price, available, color);
                        break;
                    case "Motorcycle":
                        int engineCapacity = Integer.parseInt(data[8]);
                        vehicle = new Motorcycle(engineCapacity, id, brand, model, year, price, available, color);
                        break;
                    case "Truck":
                        double loadCapacity = Double.parseDouble(data[8]);
                        vehicle = new Truck(loadCapacity, id, brand, model, year, price, available, color);
                        break;
                }
                if (vehicle != null) {
                    vehicles.put(id, vehicle);
                    if (available) {
                        availableVehicleIds.add(id);
                    }
                }
            }
        } catch (IOException e) {
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(customersFIle))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Customer customer = new Customer(data[1], data[0], data[2], data[3]);
                customers.put(customer.getID(), customer);
            }
        } catch (IOException e) {
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(rentalsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String rentalId = data[0];
                Vehicle vehicle = vehicles.get(data[1]); // Look up vehicle by ID
                Customer customer = customers.get(data[2]); // Look up customer by ID
                LocalDate startDate = LocalDate.parse(data[3]);
                LocalDate endDate = LocalDate.parse(data[4]);
                double totalCost = Double.parseDouble(data[5]);

                if (vehicle != null && customer != null) {
                    Rental rental = new Rental(rentalId, startDate, endDate, vehicle, customer, totalCost, null);
                    rentals.put(rentalId, rental);
                }
            }
        } catch (IOException e) {
        }
        System.out.println("Data loaded from .txt files.");
    }

    public void addVehicle(Vehicle vehicle) {
        String VehicleID = vehicle.getClass().getSimpleName().charAt(0) + Integer.toString(vehicles.size() + 1);
        vehicle.setVehicleID(VehicleID);
        vehicles.put(vehicle.getVehicleID(), vehicle);
        availableVehicleIds.add(vehicle.getVehicleID());
        System.out.println("Vehicle added successfully");
    }

    public void removeVehicle(String vehicleId) {
        vehicles.remove(vehicleId);
        availableVehicleIds.remove(vehicleId);
        System.out.println("Vehicle removed successfully");
    }

    public void registerCustomer(Customer customer) {
        String customerID = customer.getClass().getSimpleName().charAt(0) + Integer.toString(customers.size() + 1);
        customer.setID(customerID);
        customers.put(customer.getID(), customer);
        System.out.println("Customer registered successfully");
    }

    public void displayAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("There is no vehicles");
            return;
        }
        System.out.println("All Vehicles:");
        for (Vehicle vehicle : vehicles.values()) {

            vehicle.displayInfo();
            System.out.println("---------------");

        }
    }

    public void displayAvailableVehicles() {
        if (availableVehicleIds.isEmpty()) {
            System.out.println("There is no avalible vehicles");
            return;
        }
        System.out.println("Available Vehicles:");
        for (String vehicleID : availableVehicleIds) {
            Vehicle vehicle = vehicles.get(vehicleID);

            vehicle.displayInfo();
            System.out.println("---------------");

        }
    }

    public void displayCustomers() {
        System.out.println("Registered Customers:");
        for (Customer c : customers.values()) {
            c.displayInfo();
            System.out.println("---------------");

        }
    }

    public void rentVehicle(Scanner scanner) {
        System.out.println("=== Rent a Vehicle ===");

        this.displayAvailableVehicles();
        System.out.print("Enter Vehicle ID (or type '0' to return): ");

        String input = scanner.next();
        if (input.equals("0")) {
            return;
        }
        Vehicle vehicle = vehicles.get(input);
        if (vehicle == null || !vehicle.isAvailable()) {
            System.out.println(" Invalid Vehicle ID or vehicle not available.");
            return;
        }

        System.out.print("Enter Customer ID (or type '0' to return): ");
        input = scanner.next();
        if (input.equals("0")) {
            return;
        }

        Customer customer = customers.get(input);
        if (customer == null) {
            System.out.println(" Invalid Customer ID.");
            return;
        }

        System.out.print("Enter rental start date (yyyy-mm-dd): ");
        String start = scanner.next();
        System.out.print("Enter rental end date (yyyy-mm-dd): ");
        String end = scanner.next();
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            if (endDate.isBefore(startDate)) {
                System.out.println("End date cannot be before start date.");
                return;
            }

            long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
            double totalCost = vehicle.calculateRentalCost((int) days);

            Payment payment;

            System.out.println("Choose payment method:");
            System.out.println("1. Cash");
            System.out.println("2. Credit Card");
            System.out.print("Enter choice: ");

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    System.out.print("Enter amount: ");
                    int amount = scanner.nextInt();
                    payment = new CashPayment(amount);
                    if (!payment.processPayment(totalCost)) {
                        return;
                    }
                    break;
                case "2":
                    System.out.print("Enter credit card number: ");
                    String cardNumber = scanner.next();

                    System.out.print("Enter Card Holder Name: ");
                    String cardHolder = scanner.next();

                    payment = new CreditCardPayment(cardNumber, cardHolder);
                    payment.processPayment(totalCost);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            String rentalId = "R" + (rentals.size() + 1);
            Rental rental = new Rental(rentalId, startDate, endDate, vehicle, customer, totalCost, null);
            availableVehicleIds.remove(vehicle.getVehicleID());

            rentals.put(rentalId, rental);
            vehicle.markAsRented();

            System.out.println("Rental successful!");
            rental.displayRentalReceipt();

        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }

    }

    public void returnVehicle(String rentalId) {
        Rental rental = rentals.get(rentalId);
        if (rental == null) {
            System.out.println("Invalid rental ID.");
            return;
        }
        Vehicle vehicle = rental.getVehicle();
        vehicle.markAsReturned();
        availableVehicleIds.add(vehicle.getVehicleID());
        System.out.println("Vehicle returned successfully.");
    }

    public void displayRentals() {
        for (Rental rental : rentals.values()) {
            rental.displayRentalDetails();
            System.out.println("---------------");
        }
    }

}
