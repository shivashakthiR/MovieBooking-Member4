
public class Customer {

    private int customerId;
    private String customerName;
    private String phoneNumber;

    // Constructor
    public Customer(int customerId, String customerName,
                    String phoneNumber) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    // Getter methods
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Display customer details
    public void displayCustomer() {

        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Phone Number: " + phoneNumber);
    }
}
