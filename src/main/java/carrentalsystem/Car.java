package carrentalsystem;

/**
 *
 * @author VIRTUOSO
 */
public class Car extends Vehicle {

    private int seatingCapacity;
    private String type;

    public Car() {

    }

    public Car(int seatingCapacity, String type, String vehicleID, String brand, String model, int year, double rentalPrice, boolean available, String color) {
        super(vehicleID, brand, model, year, rentalPrice, available, color);
        this.seatingCapacity = seatingCapacity;
        this.type = type;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getType() {
        return type;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("%-15s: %d\n", "Seating Capacity", seatingCapacity);
        System.out.printf("%-15s: %s\n", "Car Type", type);
    }

    @Override
    public String dataToString() {
        return "Car," + super.dataToString() + "," + seatingCapacity + "," + type;
    }
}
