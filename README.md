# CustomerServiceSNProject
# Introduction
In this project I have wanted to show that knowing the basics of object-oriented programming, we are able to create a simple console application for customer service.
The application was created with the use:

- loops,
- conditional statements,
- switch statement,
- primitive types,
- arrays,
- lists,
- static methods,
- encapsulation,
- Scanner class.

# Description
The application is designed for employees of the wedding hall and enables the user to add a client to an Arraylist-based database.              
With the application, we are able to enter and extract customer data quickly and efficiently.                                
Once entered, the database will contain such information as:
 - First and last name,
- phone number,
- e-mail,
- type of event,
- number of people,
- date of the event,
- the menu selected by the customer,      

Functionality of the application, will improve the operation of any wedding hall !!!

# Creation Process
The first step was the creation of 4 classes Customer, TypeOfEvent, DateOfEvent, Menu.                                                    
The customer class with the fields assigned to it represented general information about the customer.                                                       
Please find below these fields:                                               
- name (first and last name),
- phone number,
- email.
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
The DateOfEvent class  has an override method toString, which was created to provide information about the day and month of the event.
```java
 @Override
    public String toString() {
        return "Date: " + day + " " + month.toUpperCase();
    }
```
The Menu class and fields belonging to it allowed to collect information about the menu option, name, price and list of dishes.
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

- findContact (A method that checks if the customer is registered in the araylist database, if so, the method returns its index in the arraylist),
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

- findDate (A method that checks whether a date is registered in the araylist database, whether the date is occupied),
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
- addCustomer (method, which will add an object from the Customer class to the appropriate ArrayList),
```java
public void addCustomer(Customer customer) {
        if (findContact(customer.getEmail()) < 0) {
            customersBase.add(customer);
            System.out.println("Customer: " + customer.getName() + " with email: " + customer.getEmail() + " was successfully added\n");
        }

    }
```    
- addTypeOfEvent (method, which will add an object from the TypeOfEvent class to the appropriate ArrayList),
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
- addMenu (method, which will add an object from the Menu class to the appropriate ArrayList),
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
- addDate (method, which will add an object from the DateOfTheEvent class to the appropriate ArrayList),
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
- removeCustomerAndMenuAndTypeOfEvent (method that will remove the objects of the Customer, TypeOfEvent, Menu, DateOfTheEvent classes from the specific arraylists).
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
In the main class, methods have been created to allow the user to enter customer data.                                                                              
Please find these methods below: 
- addBaseOfCustomer (The method responsible for creating the database in a given year),
```java
private static CustomerDataBase addBaseOfCustomer(Validation validation) {
        System.out.println("Please enter the year for the database you are creating: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        return new CustomerDataBase(validation.correctNumberOfYear(year));
    }
```
- getCustomerName (method that uses methods from the scanner class and allows the user to enter the name of the client ).
```java
private static String getCustomerName(Validation validation) {

        String customerName = scanner.nextLine().toUpperCase();
        return validation.emptyString(customerName);
    }
```
- getEmail (method that allows the user to enter the email of the client).
```java
private static String getEmail(CustomerDataBase customerDataBase, Validation validation) {
        String email = scanner.nextLine();
        email = validation.emptyString(email);


        return validation.correctCustomerEmail(customerDataBase, email);
    }
```
- getPhoneNumber (method that allows the user to enter the phone number of the client).
```java
private static String getPhoneNumber(Validation validation) {
        String phoneNumber = scanner.nextLine();
        phoneNumber = validation.emptyString(phoneNumber);
        return validation.correctPhoneNumber(phoneNumber);
    }
```    
- getDay, getMonth (methods that allow the user to enter the day and month).
```java
public static int getDay(int upperBorder, Validation validation) {
        int day = scanner.nextInt();
        scanner.nextLine();
        return validation.correctDay(day, upperBorder);
    }

    public static String getMonth(Validation validation) {
        int option = scanner.nextInt();
        scanner.nextLine();
        return validation.correctMonth(option);
    }
```
- getTypeOfEvent (method that allows the user to enter the name of the type of event).
```java
private static String getTypeOfEvent(Validation validation) {

        String nameOfEvent = scanner.nextLine().toUpperCase();
        return validation.emptyString(nameOfEvent);
    }
```
- getOption (method that allows the user to enter the menu options that customer prefer).
```java
private static int getOption(Validation validation) {
        int option = scanner.nextInt();
        scanner.nextLine();

        return validation.correctMenuOption(option);
    }
```
Validation methods in a separate Validation class has been applied to above methods.                                                        
These methods have been created to protect against possible user error or exceeding the assumed ranges.     

- correctCustomerEmail and wrongCustomerEmail (methods to check if a customer with a specific email is in the database and in case of discrepancy, re-enter the correct value).   

```java
public String correctCustomerEmail(CustomerDataBase customerDataBase, String email) {

        while (customerDataBase.findContact(email) >= 0) {
            System.out.println("""
                    the customer with this e-mail is already in the database
                    Please enter below correct e-mail:""");
            email = scanner.nextLine();
        }
        return email;
    }

    public String wrongCustomerEmail(CustomerDataBase baseOfCustomer) {
        String customerMail = scanner.nextLine();
        while (baseOfCustomer.findContact(customerMail) < 0) {
            System.out.println("Customer with e-mail: " + customerMail + " is not a customer database please enter below correct e-mail:  ");
            customerMail = scanner.nextLine();
        }
        return customerMail;
    }
 ```
 
 - correctMenuOption, correctMonth, correctDay (methods that check if the entered data for menu options, day and month are within the assumed range).
 ```java
 public int correctMenuOption(int option) {
        while (option < 1 || option > 3) {
            System.out.println("Number of option must be within the range 1-3 inclusive");
            System.out.println("Please enter correct number of option: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }
    
    public String correctMonth(int option) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        Calendar calendar = Calendar.getInstance();

        while (option < 1 || option > 12) {
            System.out.println("A month must be within the range 1-12 inclusive");
            System.out.println("Please enter a correct value: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }


        String month = "";
        switch (option) {
            case 1:
                calendar.set(Calendar.MONTH, 0);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 2:
                calendar.set(Calendar.MONTH, 1);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 3:
                calendar.set(Calendar.MONTH, 2);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 4:
                calendar.set(Calendar.MONTH, 3);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 5:
                calendar.set(Calendar.MONTH, 4);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 6:
                calendar.set(Calendar.MONTH, 5);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 7:
                calendar.set(Calendar.MONTH, 6);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 8:
                calendar.set(Calendar.MONTH, 7);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 9:
                calendar.set(Calendar.MONTH, 8);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 10:
                calendar.set(Calendar.MONTH, 9);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 11:
                calendar.set(Calendar.MONTH, 10);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
            case 12:
                calendar.set(Calendar.MONTH, 11);
                month = dateFormat.format(calendar.getTime()).toUpperCase();
                break;
        }
        return month;
    }

    public int correctDay(int day, int upperBorder) {
        while (day < 1 || day > upperBorder) {
            System.out.println("A day must be within the range 1-" + upperBorder + " inclusive");
            System.out.println("Please enter a correct value: ");
            day = scanner.nextInt();
            scanner.nextLine();
        }
        return day;
    }
```
- correctDayScope (method that assigns the appropriate number of days to a specific month).

```java
public int correctDayScope(String month) {
        int upperBorder = 0;
        switch (month) {
            case "JANUARY":
            case "MAY":
            case "MARCH":
            case "JULY":
            case "AUGUST":
            case "DECEMBER":
                upperBorder = 31;
                break;
            case "APRIL":
            case "NOVEMBER":
            case "JUNE":
            case "SEPTEMBER":
                upperBorder = 30;
                break;
        }

        if (month.equals("FEBRUARY") && !leapYear(currentYear())) {
            upperBorder = 28;
        }

        if (month.equals("FEBRUARY") && leapYear(currentYear())) {
            upperBorder = 29;
        }
        return upperBorder;

    }
```
- correctDate (A method to check if a date is already taken).

```java
public DateOfTheEvent correctDate(CustomerDataBase customerDataBase, Validation validation, String month, int day) {

        while (customerDataBase.findDate(month, day) == null) {
            System.out.println("""
                    This date is already taken, please select another one
                    Please enter below the correct date:""");
            System.out.println("Please enter the another month with numbers. Month: ");
            month = Main.getMonth(validation);

            System.out.println("Please enter the other day with numbers. Day: ");
            day = Main.getDay(validation.correctDayScope(month), validation);

        }
        return customerDataBase.findDate(month, day);
    }
```
- currentYear (A method that gives the acrual year).
```java
public int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
```
- leapYear (A method that checks if the year is leap year).
```java
public boolean leapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
```    
- correctPhoneNumber (A method that verifies that a number has exactly 9 digits).
```java
public String correctPhoneNumber(String phoneNumber) {
        while (phoneNumber.length() != 9) {
            System.out.println("Phone number must be exactly 9 digits long");
            System.out.println("Please enter a valid phone number");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }
```    
- emptyString (method that checks if the entered value is not an empty string).
```java
public String emptyString(String nonEmptyString) {
        while (nonEmptyString.equals("")) {
            System.out.println("String is empty please enter non-empty name below:  ");
            nonEmptyString = scanner.nextLine();
        }
        return nonEmptyString;
    }
```

- lowerBorderNumberOfPeople, upperBorderNumberOfPeople (methods that allow you to determine the upper and lower limits for the number of people at an event).
```java
private static int lowerBorderNumberOfPeople() {
        System.out.println("Please enter a new lower limit below: ");
        int lowerBorder = scanner.nextInt();
        scanner.nextLine();
        return lowerBorder;
    }

    private static int upperBorderNumberOfPeople(int lowerBorder) {
        System.out.println("Please enter a new upper limit below: ");
        int upperBorder = scanner.nextInt();
        scanner.nextLine();
        while (lowerBorder >= upperBorder) {
            System.out.println("""
                    Lower limit is greater or equal than the upper limit.
                    Please enter below correct upper limit:""");
            upperBorder = scanner.nextInt();
            scanner.nextLine();
        }
        return upperBorder;
    }
```    
- settingProperNumberOfPeopleScope (method that allows you to set a new range for the number of people at an event).
```java
public int[] settingProperNumberOfPeopleScope(Validation validation) {
        int[] outputArray = new int[2];
        int upperBorder;
        int lowerBorder;
        System.out.println("""
                The default range is 50-180 inclusive.
                Do you want to set a number of people new range?
                If yes please enter "yes".
                If not please enter "no" below.""");

        String newScope = scanner.nextLine().toUpperCase();
        newScope = validation.emptyString(newScope);

        if (newScope.equals("YES")) {
            lowerBorder = lowerBorderNumberOfPeople();
            upperBorder = upperBorderNumberOfPeople(lowerBorder);
        } else {
            lowerBorder = 50;
            upperBorder = 180;
        }

        outputArray[0] = lowerBorder;
        outputArray[1] = upperBorder;

        return outputArray;
    }
```
- getNumberOfPeople (method that checks whether the data entered is within range in terms of the number of people at the event).
```java
public int getNumberOfPeople(int[] inputArray) {
        int lowerBorder = inputArray[0];
        int upperBorder = inputArray[1];

        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        while (numberOfPeople < lowerBorder || numberOfPeople > upperBorder) {
            System.out.println("Number of people must be within the range " + lowerBorder + "-" + upperBorder + " inclusive");
            System.out.println("Please enter the correct number of people: ");
            numberOfPeople = scanner.nextInt();
            scanner.nextLine();
        }
        return numberOfPeople;
    }
 ```
 In the main class, the most important methods include:
- printMenu (method that prints the customer service menu).
```java
 private static void printMenu() {
        System.out.println("""
                Please find the customer service menu below:
                1 - print Instruction,
                2 - add new customer,menu,type of event,email,
                3 - update existing customer
                4 - remove customer and menu and type of event
                5 - make quotation
                6 - print customer database
                7 - stop registration
                 """);
    }
```
- chooseOption (method that allows the user to choose which method he wants to use).
```java
private static void chooseOption() {
        int option;
        boolean end = false;
        Validation validation = new Validation();
        CustomerDataBase baseOfCustomer = addBaseOfCustomer(validation);
        System.out.println(baseOfCustomer + " is already created\n");
        printMenu();
        while (!end) {
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    printMenu();
                    break;
                case 2:
                    addNewCustomer(baseOfCustomer, validation);
                    break;
                case 3:
                    updateCustomer(baseOfCustomer, validation);
                    break;
                case 4:
                    removeCustomer(baseOfCustomer, validation);
                    break;
                case 5:
                    quotation(baseOfCustomer, validation);
                    break;
                case 6:
                    printCustomerBase(baseOfCustomer);
                    break;
                case 7:
                    end = true;
                    break;
            }
        }
    }
```
- addNewCustomer, removeCustomer,updateCustomer (methods that allow the user to add, update or remove a client from the database).
```java
private static void addNewCustomer(CustomerDataBase baseOfCustomer, Validation validation) {
        System.out.println("Please enter the customer's email");
        String email = getEmail(baseOfCustomer, validation);

        System.out.println("Please enter the customer's first and last name");
        String customerName = getCustomerName(validation);

        System.out.println("Please enter the customer's phone number");
        String phoneNumber = getPhoneNumber(validation);

        Customer customer = new Customer(customerName, phoneNumber, email);
        baseOfCustomer.addCustomer(customer);

        System.out.println("Please enter the date that the customer is interested in");
        DateOfTheEvent dateOfTheEvent = getDate(baseOfCustomer, validation);

        baseOfCustomer.addDate(dateOfTheEvent, customer);

        System.out.println("Please specify the type of event");
        String typeOfEvent = getTypeOfEvent(validation);

        int[] numberOfPeopleRange = validation.settingProperNumberOfPeopleScope(validation);

        System.out.println("Please specify the number of people");
        int numberOfPeople = validation.getNumberOfPeople(numberOfPeopleRange);

        baseOfCustomer.addTypeOfEvent(typeOfEvent, numberOfPeople, customer);

        System.out.println("Please find available options of the menu below \n");
        contentMenu();
        System.out.println("Please enter the customer's choice: ");
        int option = getOption(validation);

        baseOfCustomer.addMenu(option, customer);

    }
    
    private static void removeCustomer(CustomerDataBase baseOfCustomer, Validation validation) {
        System.out.println("Please enter the customer's email to be removed");
        String customerMail = validation.wrongCustomerEmail(baseOfCustomer);
        Customer customer = baseOfCustomer.queryCustomer(customerMail);
        baseOfCustomer.removeCustomerAndMenuAndTypeOfEvent(customer);
    }
    
    public static void updateCustomer(CustomerDataBase baseOfCustomer, Validation validation) {
        System.out.println("Please enter the customer's email below to verify that it is in database: ");
        String oldCustomerEmail = validation.wrongCustomerEmail(baseOfCustomer);

        System.out.println("Please enter the new customer's email below: ");
        String newCustomerEmail = getEmail(baseOfCustomer, validation);

        System.out.println("Please enter the new customer's first and last name below: ");
        String newCustomerName = getCustomerName(validation);

        System.out.println("Please enter a new phone number below: ");
        String newCustomerPhoneNumber = getPhoneNumber(validation);

        Customer customerToChange = new Customer(newCustomerName, newCustomerPhoneNumber, newCustomerEmail);
        baseOfCustomer.getCustomersBase().set(baseOfCustomer.findContact(oldCustomerEmail), customerToChange);


        System.out.println("Please enter the new date below");
        DateOfTheEvent newDateOfTheEvent = getDate(baseOfCustomer, validation);
        baseOfCustomer.getCustomerDateBase().set(baseOfCustomer.findContact(customerToChange.getEmail()), newDateOfTheEvent);


        System.out.println("Please find available options of the menu below \n");
        contentMenu();

        System.out.println("Please enter a new menu option below: ");
        int option = getOption(validation);
        Menu menuToChange = new Menu(option);
        baseOfCustomer.getCustomerMenuBase().set(baseOfCustomer.findContact(customerToChange.getEmail()), menuToChange);

        System.out.println("Please enter a new type of event below: ");
        String typeOfEventNewName = getTypeOfEvent(validation);

        int[] numberOfPeopleRange = validation.settingProperNumberOfPeopleScope(validation);
        System.out.println("Please enter a new number of people below: ");
        int numberOfPeople = validation.getNumberOfPeople(numberOfPeopleRange);

        TypeOfEvent typeOfEvent = new TypeOfEvent(typeOfEventNewName, numberOfPeople);

        baseOfCustomer.getCustomerTypeOfEventBase().set(baseOfCustomer.findContact(customerToChange.getEmail()), typeOfEvent);

        System.out.println("Customer with e-mail: " + oldCustomerEmail + " was successfully updated");

    }
```
- quotation (A method that makes a quote for a specific customer).
```java
public static void quotation(CustomerDataBase baseOfCustomer, Validation validation) {
        System.out.println("In order to make quotation please enter customer's e-mail below: ");
        String customerMail = validation.wrongCustomerEmail(baseOfCustomer);
        Customer customer = baseOfCustomer.queryCustomer(customerMail);

        int customerPosition = baseOfCustomer.findContact(customer.getEmail());
        int numberOfPeople = baseOfCustomer.getCustomerTypeOfEventBase().get(customerPosition).getPeopleQty();
        double price = baseOfCustomer.getCustomerMenuBase().get(customerPosition).getPrice();

        double quotation = numberOfPeople * price;

        System.out.println("The menu option that has been selected has a price: " + price + " zloty");
        System.out.println("Number of People selected by the client: " + numberOfPeople + " people");
        System.out.println("Total cost for " + customer.getName() + " with e-mail: " + customer.getEmail() + " is " + Math.round(quotation) + " zloty");
    }
```
- printCustomerBase (A method that prints all the information collected in the customer database).
```java
private static void printCustomerBase(CustomerDataBase baseOfCustomer) {
        if (baseOfCustomer.getCustomersBase().size() == 0) {
            System.out.println("Customer Base is empty");
            System.out.println("Please add items");
        }
        for (int i = 0; i < baseOfCustomer.getCustomersBase().size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(baseOfCustomer.getCustomersBase().get(i));
            System.out.print(baseOfCustomer.getCustomerDateBase().get(i));
            System.out.print(" ");
            System.out.println((baseOfCustomer.getYear()));
            System.out.println(baseOfCustomer.getCustomerMenuBase().get(i));
            System.out.println(baseOfCustomer.getCustomerTypeOfEventBase().get(i));
            System.out.println("Total Cost: " + Math.round(baseOfCustomer.getCustomerTypeOfEventBase().get(i).getPeopleQty() * baseOfCustomer.getCustomerMenuBase().get(i).getPrice()) + " zloty" + "\n");
        }
    }
```
# Running application

## Prerequisite
If you want to run the application, java version 19.01 or later is required.                                             
Please find below link to a tutorial on how to properly install the above java version:                                                 
https://www.youtube.com/watch?v=vFBW6nMcVlU                                                                   
To download, follow the link below:                                                                                                                                                                      
https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html                                                    

## Launch process
Download a file named launcher.jar from the DateZoneProject repository located on GitHub.                                                

![thirdImage](https://user-images.githubusercontent.com/116492421/213838445-951ddd34-beec-4ca4-a54f-3efa83f1f0b4.PNG)

Then save it to a specific location on your computer.                                            
On my computer, the file is located in:                                                    
C:\Users\Robert\IdeaProjects\DateZoneProject\src                                       

Open the console by typing cmd at the command line.                                            

![image](https://user-images.githubusercontent.com/116492421/213838763-a28abb59-9ec9-4402-9801-344a4e5116b1.png)

In the next step, type the following command in the console:                                                   
java -jar launcher.jar                                                                   
After typing the command, the program should start in the console.                                              

![carbon](https://user-images.githubusercontent.com/116492421/213839258-2802c72c-d459-4c00-b920-3696805aa747.png)

# Application Development                                                      

## Prerequisite                                                     
If you want to develop the application necessary will be                                                
-	java version 19.01,
-	IntelliJ IDEA Community Edition 2022.1*.


Please find below link to a tutorial on how to properly install the above IntelliJ IDEA version:                                              
https://www.youtube.com/watch?v=S8cVBE4euus                                                          
To download, follow the link below:                                                         
https://www.jetbrains.com/idea/download/#section=windows                                                     

![thirdImage](https://user-images.githubusercontent.com/116492421/213839484-02317d47-3c3c-4e1c-aec6-fa9067d12a4c.PNG)


## Project Openning

Download the zip folder from the DateZoneProject repository with the project files.                                                           

![image](https://user-images.githubusercontent.com/116492421/213839520-4df8eea9-c05b-41ef-9598-f27baecc08c6.png)


Then save and extract project files to a specific location on your computer.                                                       
On my computer, the file is located in:                                                                        
C:\NewProject                                                                                       

![carbon](https://user-images.githubusercontent.com/116492421/213839561-8355aa13-bd94-4edc-8490-91b0c31a145f.png)


The next step is to click open in IntelliJ IDEA.                                                               

![carbon](https://user-images.githubusercontent.com/116492421/213839585-35f9352f-ae52-4ca1-b03a-9747a9d8fc16.png)


Then select the appropriate folder with the project.                                                                        

![carbon](https://user-images.githubusercontent.com/116492421/213839609-4ae90b8a-619c-4111-a6a5-94ba9d86db7b.png)


After selecting the appropriate folder, a project should open.                                                                            
We can start to make changes.                                                                                    


![carbon](https://user-images.githubusercontent.com/116492421/213839656-028c5911-c952-468a-af6b-56b9a558867f.png)
