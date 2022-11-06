import java.util.ArrayList;

public class CustomerDataBase {
    private final ArrayList<Customer> customersBase;
    private final int year;

    private final ArrayList<Menu> customerMenuBase;
    private final ArrayList<TypeOfEvent> customerTypeOfEventBase;

    private final ArrayList<DateOfTheEvent> customerDateBase;



    public void addTypeOfEvent (String nameOfEvent, int numberOfPeople, Customer customer){

        if (findContact(customer.getName())>=0){
            customerTypeOfEventBase.add(findContact(customer.getName()),new TypeOfEvent(nameOfEvent,numberOfPeople));
            System.out.println(nameOfEvent + " for " + customer.getName() + " for " + numberOfPeople + " people" + " was successfully saved\n");
        }
        else {
            System.out.println("The customer is not in the database\n");
        }
    }

    public  void addDate (String month, int day, Customer customer){

        if (findContact(customer.getName())>=0){
            customerDateBase.add(findContact(customer.getName()),new DateOfTheEvent(day , month));
            System.out.println("Date: " + day + " " + month + " for " + customer.getName()  + " was successfully saved\n");
        }
        else {
            System.out.println("The customer is not in the database\n");
        }
    }

    public int getYear() {
        return year;
    }

    public ArrayList<DateOfTheEvent> getCustomerDateBase() {
        return customerDateBase;
    }

    public int findContact (String name){
        for (int i = 0; i<customersBase.size(); i++){
            Customer customer = customersBase.get(i);
            if (customer.getName().equals(name)){
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
        if (findContact(customer.getName())< 0) {
            customersBase.add(customer);
            System.out.println("Customer: " + customer.getName()  + " was successfully added\n");


        } else {
            System.out.println("The customer is already in the database\n");
        }

    }

    public void removeCustomerAndMenuAndTypeOfEvent (Customer customer) {

        if (findContact(customer.getName())>=0) {
            int position = findContact(customer.getName());
            customerMenuBase.remove(position);
            customerTypeOfEventBase.remove(position);
            customersBase.remove(position);
            customerDateBase.remove(position);
            System.out.println("Customer: " + customer.getName() + " was successfully removed");

        } else {
            System.out.println("The customer is not in the database\n");
        }
    }
    public Customer queryCustomer(String customerName){
        if (findContact(customerName) >= 0){
            return customersBase.get(findContact(customerName));

        }
        return null;
    }


    public void addMenu(int option, Customer customer){
        if (findContact(customer.getName())>=0){
            customerMenuBase.add(findContact(customer.getName()),new Menu(option));
            System.out.println("Menu for " + customer.getName() + " was successfully added\n");
        }
        else {
            System.out.println("The customer is not in the database\n");
        }
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
