package main.coffeevan;

import main.coffeevan.util.CoffeeManager;

public class SortCoffeeCommand implements Command {
    private CoffeeManager coffeeManager;

    public SortCoffeeCommand(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        coffeeManager.sortCoffee(); // Викликаємо метод для сортування кави
    }

    @Override
    public String getDescription() {
        return "Сортувати каву за критеріями"; // Повертає опис команди для відображення в меню
    }
}
