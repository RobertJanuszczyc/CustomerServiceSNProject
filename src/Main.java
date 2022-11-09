import java.util.Calendar;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
     greetingCustomers();
    chooseOption();





    }

    private static void greetingCustomers() {
        String companyName = "Sala Napoleonska";
        System.out.println("Welcome to the customer service of the " + companyName);

    }

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

    private static void chooseOption() {
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

    public static int correctNumberOfYear(int year) {
        int currentYear = currentYear();
        while (year < currentYear) {
            System.out.println("Current year is: " + currentYear + " it means that year has to be exactly " + currentYear + " or higher");
            System.out.println("Please enter the correct number of year: ");
            year = scanner.nextInt();
            scanner.nextLine();
        }
        return year;
    }

    private static int currentYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }



    private static CustomerDataBase addBaseOfCustomer() {
        System.out.println("Please enter the year for the database you are creating: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        return new CustomerDataBase(correctNumberOfYear(year));
    }

    private static String emptyString(String nonEmptyString) {
        while (nonEmptyString.equals("")) {
            System.out.println("String is empty please enter non-empty name below:  ");
            nonEmptyString = scanner.nextLine();
        }
        return nonEmptyString;
    }


    private static String getCustomerName() {

        String customerName = scanner.nextLine().toUpperCase();
        return emptyString(customerName);
    }

    private static String getEmail(CustomerDataBase customerDataBase) {
        String email = scanner.nextLine();
        email = emptyString(email);
        while (customerDataBase.findContact(email) >= 0) {
            System.out.println("""
                    the customer with this e-mail is already in the database
                    Please enter below correct e-mail:""");
            email = scanner.nextLine();
        }
        return email;
    }

    private static String getPhoneNumber() {

        String phoneNumber = scanner.nextLine();
        phoneNumber = emptyString(phoneNumber);
        while (phoneNumber.length() != 9) {
            System.out.println("Phone number must be exactly 9 digits long");
            System.out.println("Please enter a valid phone number");
            phoneNumber = scanner.nextLine();
        }

        return phoneNumber;
    }

    private static boolean leapYear(int year){
        return year % 4 == 0 && year % 100 != 0 && year % 400 == 0;
    }


    private static int correctDayScope(String month) {
        int upperBorder = 0;
        switch (month){
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



    private static int getDay(int upperBorder) {
        int day = scanner.nextInt();
        scanner.nextLine();
        while (day < 1 || day > upperBorder) {
            System.out.println("A day must be within the range 1-"+ upperBorder + " inclusive");
            System.out.println("Please enter a correct value: ");
            day = scanner.nextInt();
            scanner.nextLine();
        }

        return day;
    }

    private static String getMonth(){
        int option = scanner.nextInt();
        scanner.nextLine();
        while (option < 1 || option > 12) {
            System.out.println("A month must be within the range 1-12 inclusive");
            System.out.println("Please enter a correct value: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        String month = "";
        switch (option) {
            case 1:
                month = "JANUARY";
                break;
            case 2:
                month = "FEBRUARY";
                break;
            case 3:
                month = "MARCH";
                break;
            case 4:
                month = "APRIL";
                break;
            case 5:
                month = "MAY";
                break;
            case 6:
                month = "JUNE";
                break;
            case 7:
                month = "JULY";
                break;
            case 8:
                month = "AUGUST";
                break;
            case 9:
                month = "SEPTEMBER";
                break;
            case 10:
                month = "OCTOBER";
                break;
            case 11:
                month = "NOVEMBER";
                break;
            case 12:
                month = "DECEMBER";
                break;
        }
        return month;
    }



    private static DateOfTheEvent getDate(CustomerDataBase customerDataBase) {

        System.out.println("""
        Please enter the month with numbers.
        LEGEND:
        1 = JANUARY --> 12 = DECEMBER
        Month:""");
        String month = getMonth();

        System.out.println("Please enter the day with numbers. Day: ");
        int day = getDay(correctDayScope(month));


        while (customerDataBase.findDate(month, day) == null) {
            System.out.println("""
                    This date is already taken, please select another one
                    Please enter below the correct date:""");
            System.out.println("Please enter the another month with numbers. Month: ");
            month = getMonth();

            System.out.println("Please enter the other day with numbers. Day: ");
            day = getDay(correctDayScope(month));

        }
        return customerDataBase.findDate(month, day);
    }


    private static String getTypeOfEvent() {

        String nameOfEvent = scanner.nextLine().toUpperCase();
        return emptyString(nameOfEvent);
    }


    private static int lowerBorderNumberOfPeople(){
        System.out.println("Please enter a new lower limit below: ");
        int lowerBorder = scanner.nextInt();
        scanner.nextLine();
        return lowerBorder;
    }

    private static int upperBorderNumberOfPeople(int lowerBorder){
        System.out.println("Please enter a new upper limit below: ");
        int upperBorder = scanner.nextInt();
        scanner.nextLine();
        while (lowerBorder>=upperBorder){
            System.out.println("""
        Lower limit is greater than the upper limit.
        Please enter below correct upper limit:""");
            upperBorder = scanner.nextInt();
            scanner.nextLine();
        }
        return upperBorder;
    }

    private static int [] settingProperNumberOfPeopleScope(){
        int [] outputArray = new int[2];
        int upperBorder;
        int lowerBorder;
        System.out.println("""
                The default range is 50-180 inclusive.
                Do you want to set a number of people new range?
                If yes please enter "yes".
                If not please enter "no" below.""");

        String newScope = scanner.nextLine().toUpperCase();
        newScope = emptyString(newScope);

        if (newScope.equals("YES")){
            lowerBorder = lowerBorderNumberOfPeople();
            upperBorder = upperBorderNumberOfPeople(lowerBorder);
        }
        else {
            lowerBorder = 50;
            upperBorder = 180;
        }

        outputArray[0] = lowerBorder;
        outputArray[1] = upperBorder;

        return outputArray;
    }




    private static int getNumberOfPeople(int [] inputArray) {
        int lowerBorder = inputArray[0];
        int upperBorder = inputArray[1];

        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        while (numberOfPeople < lowerBorder || numberOfPeople > upperBorder) {
            System.out.println("Number of people must be within the range " + lowerBorder +"-" + upperBorder  + " inclusive");
            System.out.println("Please enter the correct number of people: ");
            numberOfPeople = scanner.nextInt();
            scanner.nextLine();
        }
        return numberOfPeople;
    }

    private static int getOption() {

        int option = scanner.nextInt();
        scanner.nextLine();

        while (option < 1 || option > 3) {
            System.out.println("Number of option must be within the range 1-3 inclusive");
            System.out.println("Please enter correct number of option: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }


    private static void contentMenu() {

        for (int i = 1; i <= 3; i++) {
            Menu menu = new Menu(i);
            System.out.println(menu + "\n");
        }
    }


    private static void addNewCustomer(CustomerDataBase baseOfCustomer) {
        System.out.println("Please enter the customer's email");
        String email = getEmail(baseOfCustomer);

        System.out.println("Please enter the customer's first and last name");
        String customerName = getCustomerName();

        System.out.println("Please enter the customer's phone number");
        String phoneNumber = getPhoneNumber();

        Customer customer = new Customer(customerName, phoneNumber, email);
        baseOfCustomer.addCustomer(customer);

        System.out.println("Please enter the date that the customer is interested in");
        DateOfTheEvent dateOfTheEvent = getDate(baseOfCustomer);

        baseOfCustomer.addDate(dateOfTheEvent, customer);

        System.out.println("Please specify the type of event");
        String typeOfEvent = getTypeOfEvent();

        int [] numberOfPeopleRange = settingProperNumberOfPeopleScope();

        System.out.println("Please specify the number of people");
        int numberOfPeople = getNumberOfPeople(numberOfPeopleRange);

        baseOfCustomer.addTypeOfEvent(typeOfEvent, numberOfPeople, customer);

        System.out.println("Please find available options of the menu below \n");
        contentMenu();
        System.out.println("Please enter the customer's choice: ");
        int option = getOption();

        baseOfCustomer.addMenu(option, customer);

    }



    private static void removeCustomer(CustomerDataBase baseOfCustomer) {
        System.out.println("Please enter the customer's email to be removed");
        String customerMail = wrongCustomerEmail(baseOfCustomer);
        Customer customer = baseOfCustomer.queryCustomer(customerMail);
        baseOfCustomer.removeCustomerAndMenuAndTypeOfEvent(customer);
    }


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







    public static void updateCustomer(CustomerDataBase baseOfCustomer) {
        System.out.println("Please enter the customer's email below to verify that it is in database: ");
        String oldCustomerEmail = wrongCustomerEmail(baseOfCustomer);

        System.out.println("Please enter the new customer's email below: ");
        String newCustomerEmail = getEmail(baseOfCustomer);

        System.out.println("Please enter the new customer's first and last name below: ");
        String newCustomerName = getCustomerName();

        System.out.println("Please enter a new phone number below: ");
        String newCustomerPhoneNumber = getPhoneNumber();

        Customer customerToChange = new Customer(newCustomerName, newCustomerPhoneNumber, newCustomerEmail);
        baseOfCustomer.getCustomersBase().set(baseOfCustomer.findContact(oldCustomerEmail), customerToChange);


        System.out.println("Please enter the new date below");
        DateOfTheEvent newDateOfTheEvent = getDate(baseOfCustomer);
        baseOfCustomer.getCustomerDateBase().set(baseOfCustomer.findContact(customerToChange.getEmail()), newDateOfTheEvent);


        System.out.println("Please find available options of the menu below \n");
        contentMenu();

        System.out.println("Please enter a new menu option below: ");
        int option = getOption();
        Menu menuToChange = new Menu(option);
        baseOfCustomer.getCustomerMenuBase().set(baseOfCustomer.findContact(customerToChange.getEmail()), menuToChange);

        System.out.println("Please enter a new type of event below: ");
        String typeOfEventNewName = getTypeOfEvent();

        int [] numberOfPeopleRange = settingProperNumberOfPeopleScope();
        System.out.println("Please enter a new number of people below: ");
        int numberOfPeople = getNumberOfPeople(numberOfPeopleRange);

        TypeOfEvent typeOfEvent = new TypeOfEvent(typeOfEventNewName, numberOfPeople);

        baseOfCustomer.getCustomerTypeOfEventBase().set(baseOfCustomer.findContact(customerToChange.getEmail()), typeOfEvent);

        System.out.println("Customer with e-mail: " + oldCustomerEmail + " was successfully updated");

    }

    public static void quotation(CustomerDataBase baseOfCustomer) {
        System.out.println("In order to make quotation please enter customer's e-mail below: ");
        String customerMail = wrongCustomerEmail(baseOfCustomer);
        Customer customer = baseOfCustomer.queryCustomer(customerMail);

        int customerPosition = baseOfCustomer.findContact(customer.getEmail());
        int numberOfPeople = baseOfCustomer.getCustomerTypeOfEventBase().get(customerPosition).getPeopleQty();
        double price = baseOfCustomer.getCustomerMenuBase().get(customerPosition).getPrice();

        double quotation = numberOfPeople * price;

        System.out.println("The menu option that has been selected has a price: " + price + " zloty");
        System.out.println("Number of People selected by the client: " + numberOfPeople + " people");
        System.out.println("Total cost for " + customer.getName() + " with e-mail: " + customer.getEmail() + " is " + Math.round(quotation) + " zloty");
    }


    public static String wrongCustomerEmail(CustomerDataBase baseOfCustomer) {
        String customerMail = scanner.nextLine();
        while (baseOfCustomer.findContact(customerMail) < 0) {
            System.out.println("Customer with e-mail: " + customerMail + " is not a customer database please enter below correct e-mail:  ");
            customerMail = scanner.nextLine();
        }
        return customerMail;
    }
}
