package carrentalsystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CarRentalSystem system = new CarRentalSystem();
        system.loadDataFromTxt();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Car Rental System Menu ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Register Customer");
            System.out.println("3. Display Available Vehicles");
            System.out.println("4. Display All Vehicles");
            System.out.println("5. Display Customers");
            System.out.println("6. Rent a Vehicle");
            System.out.println("7. Display Rentals");
            System.out.println("8. Return Vehicle");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    System.out.println("Choose the type of the vehicle you want to add:");
                    System.out.println("1. Car");
                    System.out.println("2. Motorcycle");
                    System.out.println("3. Truck");
                    System.out.println("0. to return");

                    String vehiclType = scanner.next();
                    if (vehiclType.equals("0")) {
                        break;
                    }
                    if (!vehiclType.equals("1") && !vehiclType.equals("2") && !vehiclType.equals("3")) {
                        System.out.println("Invalid choice. Please try again.");
                        break;
                    }
                    Vehicle vehicle = null;
                    String brand,
                     model,
                     color;
                    int year;
                    double rentalPrice;

                    System.out.println("Enter vehicle brand: ");
                    brand = scanner.next().toUpperCase();
                    System.out.println("Enter vehicle Model: ");
                    model = scanner.next().toUpperCase();
                    System.out.println("Enter vehicle Manufacture Year: ");
                    year = scanner.nextInt();
                    System.out.println("Enter vehicle Color: ");
                    color = scanner.next().toUpperCase();
                    System.out.println("Enter vehicle Rental Price: ");
                    rentalPrice = scanner.nextDouble();

                    switch (vehiclType) {
                        case "1":
                            System.out.println("Enter seating Capacity: ");
                            int seatingcapacity = scanner.nextInt();
                            System.out.println("Enter Car type (SUV/Sedan/etc.): ");
                            String carType = scanner.next();
                            vehicle = new Car(seatingcapacity, carType,
                                    null, brand, model, year, rentalPrice, true, color);

                            break;
                        case "2":
                            System.out.println("Enter Motorcycle Engine Capacity: ");
                            int engineCapacity = scanner.nextInt();
                            vehicle = new Motorcycle(engineCapacity, null,
                                    brand, model, year, rentalPrice, running, color);
                            break;
                        case "3":
                            System.out.println("Enter Truck Load Capacity");
                            int loadCapacity = scanner.nextInt();
                            vehicle = new Truck(loadCapacity, null, brand, model,
                                    year, rentalPrice, running, color);
                    }
                    system.addVehicle(vehicle);
                    break;

                case "2":

                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Address: ");
                    String address = scanner.next();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.next();
                    boolean invalid = false;
                    for (char c : phone.toCharArray()) {
                        if (Character.isLetter(c)) {
                            invalid = true;
                            break;
                        }
                    }
                    if (invalid) {
                        System.out.println("Invalid phone number. Please try again.");
                        break;
                    }
                    Customer customer = new Customer(name, null, address, phone);
                    system.registerCustomer(customer);
                    break;

                case "3":
                    system.displayAvailableVehicles();
                    System.out.println("Enter any charachter to continue");
                    scanner.next();
                    break;

                case "4":
                    system.displayAllVehicles();
                    System.out.println("Enter any charachter to continue");
                    scanner.next();
                    break;
                case "5":
                    system.displayCustomers();
                    break;

                case "6":
                    system.rentVehicle(scanner);
                    break;

                case "7":
                    system.displayRentals();

                    break;

                case "8":
                    System.out.print("Enter Rental ID to return: ");
                    String rid = scanner.next();
                    system.returnVehicle(rid);
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        system.saveDataToTxt();
        scanner.close();
    }
}
