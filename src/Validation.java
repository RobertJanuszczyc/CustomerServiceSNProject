import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Validation {
    private final static Scanner scanner = new Scanner(System.in);


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


    public int correctMenuOption(int option) {
        while (option < 1 || option > 3) {
            System.out.println("Number of option must be within the range 1-3 inclusive");
            System.out.println("Please enter correct number of option: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }


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

    public int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public int correctNumberOfYear(int year) {
        int currentYear = currentYear();
        while (year < currentYear) {
            System.out.println("Current year is: " + currentYear + " it means that year has to be exactly " + currentYear + " or higher");
            System.out.println("Please enter the correct number of year: ");
            year = scanner.nextInt();
            scanner.nextLine();
        }
        return year;
    }

    public boolean leapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }


    public String correctPhoneNumber(String phoneNumber) {
        while (phoneNumber.length() != 9) {
            System.out.println("Phone number must be exactly 9 digits long");
            System.out.println("Please enter a valid phone number");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }

    public String emptyString(String nonEmptyString) {
        while (nonEmptyString.equals("")) {
            System.out.println("String is empty please enter non-empty name below:  ");
            nonEmptyString = scanner.nextLine();
        }
        return nonEmptyString;
    }


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
                    Lower limit is greater than the upper limit.
                    Please enter below correct upper limit:""");
            upperBorder = scanner.nextInt();
            scanner.nextLine();
        }
        return upperBorder;
    }

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

}

