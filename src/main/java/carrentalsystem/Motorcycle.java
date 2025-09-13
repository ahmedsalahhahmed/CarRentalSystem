package carrentalsystem;

/**
 *
 * @author VIRTUOSO
 */
public class Motorcycle extends Vehicle {

    private int engineCapacity;

    public Motorcycle() {
    }

    public Motorcycle(int engineCapacity, String vehicleID, String brand, String model, int year, double rentalPrice, boolean available, String color) {
        super(vehicleID, brand, model, year, rentalPrice, available, color);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("%-15s: %d\n", "Engine Capacity ", engineCapacity);

    }

    @Override
    public String dataToString() {
        return "Motorcycle," + super.dataToString() + "," + engineCapacity;
    }
}
