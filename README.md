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
-findContact(A method that checks whether a contact is registered in the database),
-addCustomer (method, which will add an object from the Customer class to the appropriate ArrayList),
-addTypeOfEvent (method, which will add an object from the TypeOfEvent class to the appropriate ArrayList),
-


