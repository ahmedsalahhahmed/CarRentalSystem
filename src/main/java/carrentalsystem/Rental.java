package carrentalsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author VIRTUOSO
 */
public class Rental {

    private String rentalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Vehicle vehicle;
    private Customer customer;
    private double totalCost;
    private Payment payment;

    public Rental() {
    }

    public Rental(String rentalId, LocalDate startDate, LocalDate endDate, Vehicle vehicle, Customer customer, double totalCost, Payment payment) {
        this.rentalId = rentalId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicle = vehicle;
        this.customer = customer;
        this.totalCost = totalCost;
        this.payment = payment;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public double calculateTotalCost() {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) {
            days = 1;
        }
        return vehicle.calculateRentalCost((int) days);
    }

    public void displayRentalReceipt() {
        String vehicleName = vehicle.getBrand() + " " + vehicle.getModel();

        String paymentMethod = (payment != null) ? payment.getClass().getSimpleName() : "Pending";

        System.out.println("========= RENTAL RECEIPT =========");

        System.out.printf("%-15s: %s\n", "Rental ID", rentalId);
        System.out.printf("%-15s: %s\n", "Customer", customer.getName());
        System.out.printf("%-15s: %s\n", "Vehicle", vehicleName);
        System.out.printf("%-15s: %s\n", "Start Date", startDate);
        System.out.printf("%-15s: %s\n", "End Date", endDate);

        System.out.printf("%-15s: %.2f $\n", "Total Cost", totalCost);
        System.out.printf("%-15s: %s\n", "Payment Method", paymentMethod);
        System.out.println("==================================");

    }

    public void displayRentalDetails() {
        String vehicleName = vehicle.getBrand() + " " + vehicle.getModel();

       
        String paymentMethod = (payment != null) ? payment.getClass().getSimpleName() : "Not Paid";

        System.out.println("========= RENTAL DETAILS =========");
        
        System.out.printf("%-15s: %s\n", "Rental ID", rentalId);
        System.out.printf("%-15s: %s\n", "Customer ID", customer.getID());
        System.out.printf("%-15s: %s\n", "Customer Name", customer.getName());
        System.out.printf("%-15s: %s\n", "Vehicle ID", vehicle.getVehicleID());
        System.out.printf("%-15s: %s\n", "Vehicle", vehicleName);
        System.out.printf("%-15s: %s\n", "Start Date", startDate);
        System.out.printf("%-15s: %s\n", "End Date", endDate);
        // Use "%.2f EGP" to format the cost to two decimal places
        System.out.printf("%-15s: %.2f EGP\n", "Total Cost", totalCost);
        System.out.printf("%-15s: %s\n", "Payment Method", paymentMethod);
        System.out.println("==================================");

    }

    public void completeRental() {
        payment.processPayment(totalCost);
        displayRentalReceipt();
    }

    public String dataToString() {
        return String.join(",",
                rentalId,
                vehicle.getVehicleID(),
                customer.getID(),
                startDate.toString(),
                endDate.toString(),
                String.valueOf(totalCost)
        );
    }
}
