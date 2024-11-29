package main.coffeevan;

import main.coffeevan.util.CoffeeManager;

import main.coffeevan.model.Coffee; // Приклад імпорту моделі Coffee, якщо вона є в окремому пакеті

import java.util.List;
import java.util.Scanner;


public class FindCoffee implements Command {
    private final CoffeeManager coffeeManager;

    public FindCoffee(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть нижню межу ціни: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Введіть верхню межу ціни: ");
        double maxPrice = scanner.nextDouble();

        // Отримуємо список кави в заданому ціновому діапазоні
        List<Coffee> foundCoffees = coffeeManager.findCoffeeByPriceRange(minPrice, maxPrice);

        // Виведення результатів пошуку
        if (foundCoffees.isEmpty()) {
            System.out.println("Кава в заданому ціновому діапазоні не знайдена.");
        } else {
            System.out.println("Знайдена кава в діапазоні " + minPrice + " - " + maxPrice + ":");
            for (Coffee coffee : foundCoffees) {
                System.out.println(coffee);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Знайти каву за ціновим діапазоном";
    }
}
