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


    private static CustomerDataBase addBaseOfCustomer(Validation validation) {
        System.out.println("Please enter the year for the database you are creating: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        return new CustomerDataBase(validation.correctNumberOfYear(year));
    }


    private static String getCustomerName(Validation validation) {

        String customerName = scanner.nextLine().toUpperCase();
        return validation.emptyString(customerName);
    }

    private static String getEmail(CustomerDataBase customerDataBase, Validation validation) {
        String email = scanner.nextLine();
        email = validation.emptyString(email);


        return validation.correctCustomerEmail(customerDataBase, email);
    }

    private static String getPhoneNumber(Validation validation) {
        String phoneNumber = scanner.nextLine();
        phoneNumber = validation.emptyString(phoneNumber);
        return validation.correctPhoneNumber(phoneNumber);
    }


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


    private static DateOfTheEvent getDate(CustomerDataBase customerDataBase, Validation validation) {
        System.out.println("""
                Please enter the month with numbers.
                LEGEND:
                1 = JANUARY --> 12 = DECEMBER
                Month:""");
        String month = getMonth(validation);
        System.out.println("Please enter the day with numbers. Day: ");
        int upperBorder = validation.correctDayScope(month);
        int day = getDay(upperBorder, validation);
        return validation.correctDate(customerDataBase, validation, month, day);
    }


    private static String getTypeOfEvent(Validation validation) {

        String nameOfEvent = scanner.nextLine().toUpperCase();
        return validation.emptyString(nameOfEvent);
    }


    private static int getOption(Validation validation) {
        int option = scanner.nextInt();
        scanner.nextLine();

        return validation.correctMenuOption(option);
    }


    private static void contentMenu() {

        for (int i = 1; i <= 3; i++) {
            Menu menu = new Menu(i);
            System.out.println(menu + "\n");
        }
    }


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
}


