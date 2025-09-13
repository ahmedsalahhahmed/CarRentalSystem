package carrentalsystem;

/**
 *
 * @author VIRTUOSO
 */
public class Customer {

    private String name;
    private String ID;
    private String address, phoneNumber;

    public Customer() {
    }

    public Customer(String name, String ID, String address, String phoneNumber) {
        this.name = name;
        this.ID = ID;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void displayInfo() {
        System.out.printf("%-15s: %d\n", "ID", ID);
        System.out.printf("%-15s: %d\n", "Name", name);
        System.out.printf("%-15s: %d\n", "Address", address);
        System.out.printf("%-15s: %d\n", "Phone Number", phoneNumber);

    }

    public String dataToString() {
        return String.join(",", ID, name, address, phoneNumber);
    }

}
