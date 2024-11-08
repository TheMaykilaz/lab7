package coffeevan.model;

public class InstantCoffeeInBags extends Coffee {
    private int numberOfBags; // Кількість пакетиків

    public InstantCoffeeInBags(String name, int numberOfBags, double price) {
        super(name, "Розчинна кава в пакетиках", numberOfBags * 0.002, price); // 1 пакетик = 2 г = 0.002 кг
        this.numberOfBags = numberOfBags;
        this.type = "Розчинна кава в пакетиках";
        calculateVolume();
    }

    @Override
    public void calculateVolume() {
        // 100 пакетиків розчинної кави (по 2 г кожен) займають 2 літра
        this.volume = (numberOfBags / 100.0) * 2;
    }
}
