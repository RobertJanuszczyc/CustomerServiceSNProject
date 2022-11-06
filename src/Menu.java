public class Menu {
    private String name;
    private final int option;

    private double price;



    @Override
    public String toString() {

        return  "Option number: " + option + "." + name;
    }


    public double getPrice() {
        return price;
    }

    public Menu(int option) {
        this.option = option;

        if (option == 1){
            this.name = "Basic option";
            this.price = 55.67;
        }
        if (option == 2){
            this.name = "Basic option with cakes";
            this.price = 74.21;
        }
        if (option == 3){
            this.name = "Full option";
            this.price = 89.34;
        }
    }
}
