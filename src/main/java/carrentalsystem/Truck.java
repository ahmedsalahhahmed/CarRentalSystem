package carrentalsystem;

/**
 *
 * @author VIRTUOSO
 */
public class Truck extends Vehicle {

    private double loadCapacity;

    public Truck() {
    }

    public Truck(double loadCapacity, String vehicleID, String brand, String model, int year, double rentalPrice, boolean available, String color) {
        super(vehicleID, brand, model, year, rentalPrice, available, color);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("%-15s: %.2f\n", "Load Capacity", loadCapacity);

    }

    @Override
    public String dataToString() {
        return "Truck," + super.dataToString() + "," + loadCapacity;
    }
}
