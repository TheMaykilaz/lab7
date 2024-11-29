package main.coffeevan.model;

public class InstantCoffeeInBags extends Coffee {
    private int numberOfBags;
    private int bags;// Кількість пакетиків

    public InstantCoffeeInBags(String name, int numberOfBags, double price) {
        super(name, "Розчинна кава в пакетиках", numberOfBags * 0.002, price); // 1 пакетик = 2 г = 0.002 кг
        this.numberOfBags = numberOfBags;
        this.bags = bags;
        this.type = "Розчинна кава в пакетиках";
        calculateVolume();
    }
    // Гетер для кількості пакетиків
    public int getBags() {
        return bags;
    }

    // Сетер для кількості пакетиків
    public void setBags(int bags) {
        this.bags = bags;
    }

    @Override
    public void calculateVolume() {
        // 100 пакетиків розчинної кави (по 2 г кожен) займають 2 літра
        this.volume = (numberOfBags / 100.0) * 2;
    }
}
