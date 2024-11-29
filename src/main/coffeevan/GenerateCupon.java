package main.coffeevan;

import main.coffeevan.util.CoffeeManager;

public class GenerateCupon implements Command {
    private CoffeeManager coffeeManager;

    public GenerateCupon(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        coffeeManager.generateCupon(); // Виклик методу для генерації купона
    }

    @Override
    public String getDescription() {
        return "Згенерувати новий купон";
    }
}
