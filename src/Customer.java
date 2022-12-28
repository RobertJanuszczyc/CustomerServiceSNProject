public class Customer {
    private final String name;
    private final String phoneNumber;

    private final String email;


    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name.toUpperCase();
    }


    @Override
    public String toString() {
        return name.toUpperCase() + "\n" + "Phone Number: " + phoneNumber + "\n" + "Email: " + email;
    }
}

