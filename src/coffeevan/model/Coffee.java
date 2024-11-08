package coffeevan.model;

public abstract class Coffee {
    protected String type;
    protected String name;
    protected double weight; // Вага кави в кілограмах
    protected double volume; // Об'єм в літрах
    protected double price;

    public Coffee(String name, String type, double weight, double price) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void calculateVolume() {}


    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public void setNewPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ": " + weight + " кг, " + volume + " об'єм," + price + "₴";
    }
}
