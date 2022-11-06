import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greetingCustomers();
        chooseOption();



    }

    public static void greetingCustomers() {
        String companyName = "Sala Napoleońska";
        System.out.println("Welcome to the customer service of the " + companyName);

    }

    public static void chooseOption() {
        int option;
        boolean end = false;

        CustomerDataBase baseOfCustomer = addBaseOfCustomer();
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
                    addNewCustomer(baseOfCustomer);
                    break;
                case 3:
                    updateCustomer(baseOfCustomer);
                    break;
                case 4:
                    removeCustomer(baseOfCustomer);
                    break;
                case 5:
                    quotation(baseOfCustomer);
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

    public static void printMenu() {
        System.out.println("Please find the customer service menu below");
        System.out.println("1 - print Instruction");
        System.out.println("2 - add new Customer, Menu, Type Of Event");
        System.out.println("3 - update existing customer");
        System.out.println("4 - remove Customer and Menu and Type Of Event");
        System.out.println("5 - make quotation");
        System.out.println("6 - print Customer Base");
        System.out.println("7 - Stop registration\n");

    }

    public static CustomerDataBase addBaseOfCustomer() {
        System.out.println("Please enter the year for the database you are creating: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        correctNumberOfYear(year);
        return new CustomerDataBase(correctNumberOfYear(year));
    }

    public static void addNewCustomer(CustomerDataBase baseOfCustomer) {
        System.out.println("Please enter the customer's first and last name");
        String customerName = scanner.nextLine().toUpperCase();
        customerName = emptyCustomerName(customerName);
        System.out.println("Please enter the customer's phone number");
        String phoneNumber = scanner.nextLine();
        phoneNumber = emptyPhoneNumber(phoneNumber);
        phoneNumber = correctPhoneNumber(phoneNumber);
        Customer customer = new Customer(customerName, phoneNumber);
        baseOfCustomer.addCustomer(customer);

        System.out.println("Please enter the date that the customer is interested in");
        System.out.println("Please enter the day with numbers. Day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        day = correctNumberOfDay(day);
        System.out.println("Please enter the month in words. Month: ");

        String month = scanner.nextLine().toUpperCase();
        month = emptyMonth(month);


        baseOfCustomer.addDate(month, day, customer);



        System.out.println("Please specify the type of event");
        String nameOfEvent = scanner.nextLine();
        nameOfEvent = emptyTypeOfEvent(nameOfEvent);
        System.out.println("Please specify the number of people");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        numberOfPeople = correctNumberOfPeople(numberOfPeople);


        baseOfCustomer.addTypeOfEvent(nameOfEvent, numberOfPeople, customer);

        System.out.println("Please find available options of the menu below \n");
        contentMenu();
        System.out.println("Please enter customer's choice");
        int option = scanner.nextInt();
        scanner.nextLine();
        option = correctNumberOfOption(option);

        baseOfCustomer.addMenu(option, customer);

    }

    public static void contentMenu() {

        System.out.println("Option number 1 has price 55.67 zloty and includes: ");
        System.out.println("1.Chicken Soup\n" + "2.Beetroot soup with dumplings\n" + "3.Red borscht with patty\n");


        System.out.println("Option number 2 has price 74.21 zloty and includes: ");
        System.out.println("1.Chicken Soup\n" + "2.Beetroot soup with dumplings\n" + "3.Red borscht with patty\n" +
                "4.Hunter's stew\n" + "5.Cheese cake\n");

        System.out.println("Option number 3 has price 89.34 zloty and includes: ");
        System.out.println("1.Chicken Soup\n" + "2.Beetroot soup with dumplings\n" + "3.Red borscht with patty\n" +
                "4.Hunter's stew\n" + "5.Cheese cake\n" + "6.Coca cola\n");
    }


    public static void removeCustomer(CustomerDataBase baseOfCustomer) {

        System.out.println("Please enter the customer's first and last name to be removed");
        String customerName = scanner.nextLine().toUpperCase();
        customerName = emptyCustomerName(customerName);
        customerName = wrongCustomerName(customerName,baseOfCustomer);
        Customer customer = baseOfCustomer.queryCustomer(customerName);
        baseOfCustomer.removeCustomerAndMenuAndTypeOfEvent(customer);
    }

    public static void printCustomerBase(CustomerDataBase baseOfCustomer) {
        if (baseOfCustomer.getCustomerMenuBase().size() == 0 && baseOfCustomer.getCustomersBase().size() == 0 && baseOfCustomer.getCustomerTypeOfEventBase().size() == 0) {
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
            System.out.println("Total Cost: " + Math.round(baseOfCustomer.getCustomerTypeOfEventBase().get(i).getPeopleQty() * baseOfCustomer.getCustomerMenuBase().get(i).getPrice()) + " zloty");
        }
    }


    public static int correctNumberOfPeople(int numberOfPeople) {
        while (numberOfPeople < 50 || numberOfPeople > 180) {
            System.out.println("Number of people must be within the range 50-180 inclusive");
            System.out.println("Please enter the correct number of people: ");
            numberOfPeople = scanner.nextInt();
            scanner.nextLine();
        }
        return numberOfPeople;
    }

    public static int correctNumberOfDay(int dayNumber) {
        while (dayNumber<1 || dayNumber>31) {
            System.out.println("A day must be within the range 1-31 inclusive");
            System.out.println("Please enter a correct value: ");
            dayNumber = scanner.nextInt();
            scanner.nextLine();
        }
        return dayNumber;
    }

    public static int correctNumberOfYear(int year) {
        while (year < 2022) {
            System.out.println("Current year is 2022, it means that year has to be exactly 2022 or higher");
            System.out.println("Please enter the correct number of year: ");
            year = scanner.nextInt();
            scanner.nextLine();
        }
        return year;
    }

    public static int correctNumberOfOption(int option) {
        while (option < 1 || option > 3) {
            System.out.println("Number of option must be within the range 1-3 inclusive");
            System.out.println("Please enter correct number of people: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }

    public static String correctPhoneNumber (String phoneNumber) {
        while (phoneNumber.length() != 9) {
            System.out.println("Phone number must be excatly 9 digits long");
            System.out.println("Please enter a valid phone number");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }

    public static void updateCustomer(CustomerDataBase baseOfCustomer) {
        System.out.println("Please enter the customer's first and last name to be updated below: ");
        String oldCustomerName = scanner.nextLine().toUpperCase();
        oldCustomerName = emptyCustomerName(oldCustomerName);
        oldCustomerName = wrongCustomerName(oldCustomerName,baseOfCustomer);
        System.out.println("Please enter the new customer's first and last name below: ");
        String newCustomerName = scanner.nextLine().toUpperCase();
        newCustomerName = emptyCustomerName(newCustomerName);
        System.out.println("Please enter a new phone number below: ");
        String newCustomerPhoneNumber = scanner.nextLine();
        newCustomerPhoneNumber = emptyPhoneNumber(newCustomerPhoneNumber);
        newCustomerPhoneNumber = correctPhoneNumber(newCustomerPhoneNumber);
        Customer customerToChange = new Customer(newCustomerName, newCustomerPhoneNumber);
        baseOfCustomer.getCustomersBase().set(baseOfCustomer.findContact(oldCustomerName), customerToChange);


        System.out.println("Please enter a new menu option below: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        option = correctNumberOfOption(option);
        Menu menuToChange = new Menu(option);
        baseOfCustomer.getCustomerMenuBase().set(baseOfCustomer.findContact(customerToChange.getName()), menuToChange);


        System.out.println("Please enter a new type of event below: ");
        String typeOfEventNewName = scanner.nextLine();
        typeOfEventNewName = emptyTypeOfEvent(typeOfEventNewName);
        System.out.println("Please enter a new number of people below: ");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        numberOfPeople = correctNumberOfPeople(numberOfPeople);
        TypeOfEvent typeOfEvent = new TypeOfEvent(typeOfEventNewName, numberOfPeople);

        baseOfCustomer.getCustomerTypeOfEventBase().set(baseOfCustomer.findContact(customerToChange.getName()), typeOfEvent);

        System.out.println("Customer: " + oldCustomerName + " was successfully updated");
        System.out.println("New first and last name is: " + newCustomerName);
    }

    public static void quotation(CustomerDataBase baseOfCustomer) {
        System.out.println("In order to make quotation please enter first and last name ");
        String customerName = scanner.nextLine();
        customerName = emptyCustomerName(customerName);
        customerName = wrongCustomerName(customerName,baseOfCustomer);
        Customer customer = baseOfCustomer.queryCustomer(customerName);
        int customerPosition = baseOfCustomer.findContact(customer.getName());
        int numberOfPeople = baseOfCustomer.getCustomerTypeOfEventBase().get(customerPosition).getPeopleQty();
        double price = baseOfCustomer.getCustomerMenuBase().get(customerPosition).getPrice();
        double quatation = numberOfPeople * price;
        System.out.println("The menu option that has been selected has a price: " + price + " zloty");
        System.out.println("Number of People selected by the client: " + numberOfPeople + " people");
        System.out.println("Total cost for " + customer.getName() + " is " + Math.round(quatation) + " zloty");
    }
    public static String emptyCustomerName(String customerName){
        while (customerName.equals("")){
            System.out.println("Customer's first and last name is empty, please enter non-empty name below:  ");
            customerName = scanner.nextLine();
        }
        return customerName;
    }

    public static String emptyPhoneNumber (String phoneNumber){
        while (phoneNumber.equals("")){
            System.out.println("Customer's phone number is empty please enter non-empty phone number below:  ");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }

    public static String emptyMonth (String month){
        while (month.equals("")){
            System.out.println("Month field is empty please enter non-empty name below:  ");
            month = scanner.nextLine();
        }
        return month;
    }

    public static String emptyTypeOfEvent (String typeOfEvent){
        while (typeOfEvent.equals("")){
            System.out.println("Type of Event name is empty please enter  non-empty name below:  ");
            typeOfEvent = scanner.nextLine();
        }
        return typeOfEvent;
    }

    public static String wrongCustomerName(String customerName,CustomerDataBase baseOfCustomer){
        while (baseOfCustomer.findContact(customerName) < 0) {
            System.out.println("Customer " + customerName + " is not a customer base please enter below correct name and surname:  ");
            customerName = scanner.nextLine();
        }
        return customerName;
    }
}
