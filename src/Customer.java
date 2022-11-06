public class Customer {
    private final String name;
    private final String phoneNumber;


    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name + "\n" + "Phone Number: " + phoneNumber;
    }
}
