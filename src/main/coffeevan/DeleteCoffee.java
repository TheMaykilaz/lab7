package main.coffeevan;

import main.coffeevan.util.CoffeeManager;
import java.util.Scanner;

public class DeleteCoffee implements Command {
    private CoffeeManager coffeeManager;

    public DeleteCoffee(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        String coffeeName = "Arabica";
        coffeeManager.deleteCoffee(coffeeName);



    }

    @Override
    public String getDescription() {
        return "Delete coffee"; // Англійський текст для узгодження з тестами
    }
}
