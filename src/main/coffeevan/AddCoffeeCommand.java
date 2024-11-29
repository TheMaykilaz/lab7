package main.coffeevan;

import main.coffeevan.model.Coffee;

import main.coffeevan.util.CoffeeManager;

import java.util.Scanner;

public class AddCoffeeCommand implements Command {
    private CoffeeManager coffeeManager;

    public AddCoffeeCommand(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введіть назву кави: ");
            String name = scanner.nextLine();

            System.out.print("Введіть тип кави: ");
            String type = scanner.nextLine();

            System.out.print("Введіть ціну кави: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введіть вагу кави: ");
            double weight = Double.parseDouble(scanner.nextLine().trim());

            Coffee coffee = new Coffee(name, type, price, weight);
            coffeeManager.addCoffee(coffee);
            System.out.println("Додано: " + coffee);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректні числові значення для ціни та ваги.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public String getDescription() {
        return "Add new coffee";
    }
}
