package carrentalsystem;

/**
 *
 * @author VIRTUOSO
 */
public abstract class  Vehicle {
    private String vehicleID;
    private String brand,model;
    private int year;
    private double rentalPrice;
    private boolean available;
    private String color;
    
    public Vehicle()
    {
        
    }

    public Vehicle(String vehicleID, String brand, String model, int year, double rentalPrice, boolean available, String color) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.available = available;
        this.color = color;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void markAsRented() {
        this.available = false;
    }
    public void markAsReturned() {
        this.available = true;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getColor() {
        return color;
    }
    
    
    public double calculateRentalCost(int days)
    {
        return rentalPrice * days ;
    }
    public void displayInfo() {
    System.out.println("----------------------------------");
    System.out.printf("%-15s: %s\n", "Vehicle ID", vehicleID);
    System.out.printf("%-15s: %s\n", "Brand", brand);
    System.out.printf("%-15s: %s\n", "Model", model);
    System.out.printf("%-15s: %d\n", "Year", year);
    System.out.printf("%-15s: %.2f $\n", "Rental Price", rentalPrice);
    System.out.printf("%-15s: %b\n", "Available", available);
    System.out.printf("%-15s: %s\n", "Color", color);
    System.out.println("----------------------------------");
}
    
    public String dataToString() {
    return String.join(",",
        vehicleID,
        brand,
        model,
        String.valueOf(year),
        String.valueOf(rentalPrice),
        String.valueOf(available),
        color
    );
}
}
