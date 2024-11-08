package coffeevan.model;

public class GrainCoffee extends Coffee {

    public GrainCoffee(String name, double weight, double price) {
        super(name, "Зернова", weight, price); // Використання статичного типу
        this.weight = weight; // Зберігаємо вагу
        calculateVolume(); // Обчислюємо об'єм
    }

    @Override
    public void calculateVolume() {
        // 1 кг зернової кави займає 4 літра
        this.volume = weight * 4;
    }
}
