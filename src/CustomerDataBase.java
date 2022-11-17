import java.util.ArrayList;

public class CustomerDataBase {
    private final ArrayList<Customer> customersBase;
    private final int year;

    private final ArrayList<Menu> customerMenuBase;
    private final ArrayList<TypeOfEvent> customerTypeOfEventBase;

    private final ArrayList<DateOfTheEvent> customerDateBase;


    public void addTypeOfEvent(String nameOfEvent, int numberOfPeople, Customer customer) {

        if (findContact(customer.getEmail()) >= 0) {
            customerTypeOfEventBase.add(findContact(customer.getEmail()), new TypeOfEvent(nameOfEvent, numberOfPeople));
            System.out.println(nameOfEvent + " for " + customer.getName() + " for " + numberOfPeople + " people" + " was successfully saved\n");
        } else {
            customerIsNotInTheBase();
        }
    }

    public void addDate(DateOfTheEvent dateOfTheEvent, Customer customer) {
        if (findContact(customer.getEmail()) >= 0) {
            customerDateBase.add(findContact(customer.getEmail()), dateOfTheEvent);
            System.out.println("Date: " + dateOfTheEvent.getDay() + " " + dateOfTheEvent.getMonth() + " for " + customer.getName() + " with email " + customer.getEmail() + " was successfully saved\n");
        } else {
            customerIsNotInTheBase();
        }
    }

    private static void customerIsNotInTheBase() {
        System.out.println("The customer is not in the database\n");
    }

    public int getYear() {
        return year;
    }

    public ArrayList<DateOfTheEvent> getCustomerDateBase() {
        return customerDateBase;
    }

    public int findContact(String mail) {
        for (int i = 0; i < customersBase.size(); i++) {
            Customer customer = customersBase.get(i);
            if (customer.getEmail().equals(mail)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        return "Customers database for " + year + " year";

    }

    public void addCustomer(Customer customer) {

        if (findContact(customer.getEmail()) < 0) {
            customersBase.add(customer);
            System.out.println("Customer: " + customer.getName() + " with email: " + customer.getEmail() + " was successfully added\n");
        }

    }

    public void removeCustomerAndMenuAndTypeOfEvent(Customer customer) {

        if (findContact(customer.getEmail()) >= 0) {
            int position = findContact(customer.getEmail());
            customerMenuBase.remove(position);
            customerTypeOfEventBase.remove(position);
            customersBase.remove(position);
            customerDateBase.remove(position);
            System.out.println("Customer: " + customer.getName() + " with email: " + customer.getEmail() + " was successfully removed");

        } else {
            customerIsNotInTheBase();
        }
    }

    public Customer queryCustomer(String customerMail) {
        if (findContact(customerMail) >= 0) {
            return customersBase.get(findContact(customerMail));

        }
        return null;
    }


    public void addMenu(int option, Customer customer) {
        if (findContact(customer.getEmail()) >= 0) {
            customerMenuBase.add(findContact(customer.getEmail()), new Menu(option));
            System.out.println("Menu for " + customer.getName().toUpperCase() + " with email " + customer.getEmail() + " was successfully added\n");
        } else {
            customerIsNotInTheBase();
        }
    }

    public DateOfTheEvent findDate(String month, int day) {
        DateOfTheEvent dateOfTheEvent = new DateOfTheEvent(day, month);
        for (int i = 0; i < getCustomerDateBase().size(); i++) {
            dateOfTheEvent = getCustomerDateBase().get(i);
            if (dateOfTheEvent.getMonth().equals(month) && dateOfTheEvent.getDay() == day) {
                return null;
            } else dateOfTheEvent = new DateOfTheEvent(day, month);
        }
        return dateOfTheEvent;

    }


    public ArrayList<Menu> getCustomerMenuBase() {
        return customerMenuBase;
    }


    public CustomerDataBase(int year) {
        this.customersBase = new ArrayList<>();
        this.year = year;
        this.customerMenuBase = new ArrayList<>();
        this.customerTypeOfEventBase = new ArrayList<>();
        this.customerDateBase = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomersBase() {
        return customersBase;
    }


    public ArrayList<TypeOfEvent> getCustomerTypeOfEventBase() {
        return customerTypeOfEventBase;
    }
}
