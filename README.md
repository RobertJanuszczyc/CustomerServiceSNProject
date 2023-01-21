# CustomerServiceSNProject
# Introduction
In this project I have wanted to show that knowing the basics of object-oriented programming, we are able to create a simple console application for customer service.
The application was created with the use:

-loops,
-conditional statements,
-switch statement,
-primitive types,
-arrays,
-lists,
-static methods,
-encapsulation,
-Scanner class.

# Description
The application is designed for employees of the wedding hall and enables the user to add a client to an Arraylist-based database. 
With the application, we are able to enter and extract customer data quickly and efficiently.
Once entered, the database will contain such information as:
 -First and last name,
-phone number,
-e-mail,
-type of event,
-number of people,
-date of the event,
-the menu selected by the customer,
Functionality of the application, will improve the operation of any wedding hall !!!

# Creation Process
The first step was the creation of 4 classes Customer, TypeOfEvent, DateOfEvent, Menu.
The customer class with the fields assigned to it represented general information about the customer.
Please find below these fields:
-name (first and last name),
-phone number,
-email.
```java
public class Customer {
    private final String name;
    private final String phoneNumber;

    private final String email;
```

All fields have private access modifier so the next crucial step was to create a constructor and getters for the fields.
```java
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
```

The Customer class has an override method toString, which was created to display general information about the customer.
```java
@Override
    public String toString() {
        return name.toUpperCase() + "\n" + "Phone Number: " + phoneNumber + "\n" + "Email: " + email;
    }
```
The TypeOfEvent class and fields belonging to it allowed to collect necessary information about the type of event and number of people.
```java
public class TypeOfEvent {
    private final String name;
    private final int peopleQty;

    public TypeOfEvent(String name, int peopleQty) {
        this.name = name;
        this.peopleQty = peopleQty;

    }

    public int getPeopleQty() {
        return peopleQty;
    }
```    
The TypeOfEvent class  has an override method toString, which was created to provide information about the type of event and number of people.
``` java
 @Override
    public String toString() {
        return "Ceremony: " + name.toUpperCase() + " for " + peopleQty + " people";
    }
```    

The DateOfEvent class and fields belonging to it allowed to gather information about the month and day on which the event is to be held.
```java
public class DateOfTheEvent {
    private final int day;
    private final String month;

    public DateOfTheEvent(int day, String month) {
        this.day = day;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
```
The TypeOfEvent class  has an override method toString, which was created to provide information about the day and month of the event.
```java
 @Override
    public String toString() {
        return "Date: " + day + " " + month.toUpperCase();
    }
```
The Menu class and fields belonging to it allowed to collect information about the menu option, name, price, list of dishes.
```java
public class Menu {
    private String name;
    private final int option;

    private double price;

    private String listOfDishes;


    public double getPrice() {
        return price;
    }

    public Menu(int option) {
        this.option = option;

        if (option == 1) {
            this.name = "Basic option";
            this.price = 55.67;
            this.listOfDishes = """
                    1.Chicken Soup
                    2.Beetroot soup with dumplings
                    3.Red borscht with patty""";
        }
        if (option == 2) {
            this.name = "Basic option with cakes";
            this.price = 74.21;
            this.listOfDishes = """
                    1.Chicken Soup
                    2.Beetroot soup with dumplings
                    3.Red borscht with patty
                    4.Hunter's stew
                    5.Cheese cake""";
        }
        if (option == 3) {
            this.name = "Full option";
            this.price = 89.34;
            this.listOfDishes = """
                    1.Chicken Soup
                    2.Beetroot soup with dumplings
                    3.Red borscht with patty
                    4.Hunter's stew
                    5.Cheese cake
                    6.Coca cola""";
        }
    }
```

The Menu class also has an override method toString, which was created to display information about the menu selected by the customer.
```java
@Override
    public String toString() {

        return option + "." + name + "." + "\n" + "Price per person: " + price + " zloty." + "\n" + "List of dishes: " + "\n" + listOfDishes;
    }
```
The next stage was to create a CustomerDataBase class with ArrayList fields storing objects from the Customer, TypeOfEvent, DateOfEvent, Menu classes.
In this class there is also a field referring to the year of the database (database stores customer data for one designated year). 
A constructor and getters have been created for fields.
```java
public class CustomerDataBase {
    private final ArrayList<Customer> customersBase;
    private final int year;

    private final ArrayList<Menu> customerMenuBase;
    private final ArrayList<TypeOfEvent> customerTypeOfEventBase;

    private final ArrayList<DateOfTheEvent> customerDateBase;


    public CustomerDataBase(int year) {
        this.customersBase = new ArrayList<>();
        this.year = year;
        this.customerMenuBase = new ArrayList<>();
        this.customerTypeOfEventBase = new ArrayList<>();
        this.customerDateBase = new ArrayList<>();
    }

    public ArrayList<Menu> getCustomerMenuBase() {
        return customerMenuBase;
    }


    public ArrayList<Customer> getCustomersBase() {
        return customersBase;
    }


    public ArrayList<TypeOfEvent> getCustomerTypeOfEventBase() {
        return customerTypeOfEventBase;
    }

    public ArrayList<DateOfTheEvent> getCustomerDateBase() {
        return customerDateBase;
    }

    public int getYear() {
        return year;
    }

```
The most important methods in the CustomerDataBase class are:

findContact(A method that checks whether a contact is registered in the araylist database),
```java
public int findContact(String mail) {
        for (int i = 0; i < customersBase.size(); i++) {
            Customer customer = customersBase.get(i);
            if (customer.getEmail().equals(mail)) {
                return i;
            }
        }
        return -1;
    }
```

findDate(A method that checks whether a date is registered in the araylist database, whether the date is occupied),
```java
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
```    
addCustomer (method, which will add an object from the Customer class to the appropriate ArrayList),
```java
public void addCustomer(Customer customer) {

        if (findContact(customer.getEmail()) < 0) {
            customersBase.add(customer);
            System.out.println("Customer: " + customer.getName() + " with email: " + customer.getEmail() + " was successfully added\n");
        }

    }
```    
addTypeOfEvent (method, which will add an object from the TypeOfEvent class to the appropriate ArrayList),
```java
public void addTypeOfEvent(String nameOfEvent, int numberOfPeople, Customer customer) {

        if (findContact(customer.getEmail()) >= 0) {
            customerTypeOfEventBase.add(findContact(customer.getEmail()), new TypeOfEvent(nameOfEvent, numberOfPeople));
            System.out.println(nameOfEvent + " for " + customer.getName() + " for " + numberOfPeople + " people" + " was successfully saved\n");
        } else {
            customerIsNotInTheBase();
        }
    }
```    
addMenu (method, which will add an object from the Menu class to the appropriate ArrayList),
```java
public void addMenu(int option, Customer customer) {
        if (findContact(customer.getEmail()) >= 0) {
            customerMenuBase.add(findContact(customer.getEmail()), new Menu(option));
            System.out.println("Menu for " + customer.getName().toUpperCase() + " with email " + customer.getEmail() + " was successfully added\n");
        } else {
            customerIsNotInTheBase();
        }
    }
```    
addDate (method, which will add an object from the DateOfTheEvent class to the appropriate ArrayList),
```java
 public void addDate(DateOfTheEvent dateOfTheEvent, Customer customer) {
        if (findContact(customer.getEmail()) >= 0) {
            customerDateBase.add(findContact(customer.getEmail()), dateOfTheEvent);
            System.out.println("Date: " + dateOfTheEvent.getDay() + " " + dateOfTheEvent.getMonth() + " for " + customer.getName() + " with email " + customer.getEmail() + " was successfully saved\n");
        } else {
            customerIsNotInTheBase();
        }
    }
```    
-removeCustomerAndMenuAndTypeOfEvent (method that will remove the objects of the Customer, TypeOfEvent, Menu, DateOfTheEvent classes from the specific arraylists).
```java
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

    private static void customerIsNotInTheBase() {
        System.out.println("The customer is not in the database\n");
    }
```    
In the main class such methods were created as:
addBaseOfCustomer (The method responsible for creating the database in a given year),
```java
private static CustomerDataBase addBaseOfCustomer(Validation validation) {
        System.out.println("Please enter the year for the database you are creating: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        return new CustomerDataBase(validation.correctNumberOfYear(year));
    }
```
getCustomerName (method that uses methods from the scanner class and allows the user to enter the name of the client ).
```java
private static String getCustomerName(Validation validation) {

        String customerName = scanner.nextLine().toUpperCase();
        return validation.emptyString(customerName);
    }
```
getEmail (method that allows the user to enter the email of the client).
```java
private static String getEmail(CustomerDataBase customerDataBase, Validation validation) {
        String email = scanner.nextLine();
        email = validation.emptyString(email);


        return validation.correctCustomerEmail(customerDataBase, email);
    }
```
getPhoneNumber (method that allows the user to enter the phone number of the client).
```java
private static String getPhoneNumber(Validation validation) {
        String phoneNumber = scanner.nextLine();
        phoneNumber = validation.emptyString(phoneNumber);
        return validation.correctPhoneNumber(phoneNumber);
    }
```    
