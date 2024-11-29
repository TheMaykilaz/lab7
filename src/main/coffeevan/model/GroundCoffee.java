package main.coffeevan.model;

public class GroundCoffee extends Coffee {

    public GroundCoffee(String name, double weight, double price) {
        super(name, "Мелена", weight, price);
        this.type = "Мелена кава";
        calculateVolume();
    }

    @Override
    public void calculateVolume() {
        // 1 кг меленої кави займає 3 літра
        this.volume = weight * 3;
    }
}
