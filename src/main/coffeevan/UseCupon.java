package main.coffeevan;

import main.coffeevan.util.CoffeeManager;
import java.util.Scanner;
public class UseCupon implements Command {
    private CoffeeManager coffeeManager;

    public UseCupon(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть код купона: ");
        String cuponCode = scanner.nextLine();
        coffeeManager.useCupon(cuponCode);
    }

    @Override
    public String getDescription() {
        return "Використати купон";
    }
}
