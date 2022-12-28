public class Menu {
    private String name;
    private final int option;

    private double price;

    private String listOfDishes;


    @Override
    public String toString() {

        return option + "." + name + "." + "\n" + "Price per person: " + price + " zloty." + "\n" + "List of dishes: " + "\n" + listOfDishes;
    }


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
}

