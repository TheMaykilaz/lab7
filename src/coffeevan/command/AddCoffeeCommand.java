package coffeevan.command;

import coffeevan.model.Coffee;
import coffeevan.model.GrainCoffee;
import coffeevan.model.InstantCoffeeInBags;
import coffeevan.model.GroundCoffee;
import coffeevan.util.CoffeeManager;

import java.util.Scanner;

public class AddCoffeeCommand implements Command {
    private CoffeeManager coffeeManager;

    public AddCoffeeCommand(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Оберіть тип кави:");
        System.out.println("1. Зернова кава");
        System.out.println("2. Мелена кава");
        System.out.println("3. Розчинна кава в пакетиках");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Введіть назву кави: ");
                String grainName = scanner.next(); // Додано запит на назву
                System.out.print("Введіть вагу (кг): ");
                double grainWeight = scanner.nextDouble();
                System.out.print("Введіть ціну: ");
                double grainPrice = scanner.nextDouble();
                Coffee grainCoffee = new GrainCoffee(grainName, grainWeight, grainPrice); // Додано назву
                coffeeManager.addCoffee(grainCoffee);
                break;

            case 2:
                System.out.print("Введіть назву кави: ");
                String groundName = scanner.next(); // Додано запит на назву
                System.out.print("Введіть вагу (кг): ");
                double groundWeight = scanner.nextDouble();
                System.out.print("Введіть ціну: ");
                double groundPrice = scanner.nextDouble();
                Coffee groundCoffee = new GroundCoffee(groundName, groundWeight, groundPrice); // Додано назву
                coffeeManager.addCoffee(groundCoffee);
                break;

            case 3:
                System.out.print("Введіть назву кави: ");
                String instantName = scanner.next(); // Додано запит на назву
                System.out.print("Введіть кількість пакетиків: ");
                int numberOfBags = scanner.nextInt();
                System.out.print("Введіть ціну: ");
                double bagPrice = scanner.nextDouble();
                Coffee instantCoffee = new InstantCoffeeInBags(instantName, numberOfBags, bagPrice); // Додано назву
                coffeeManager.addCoffee(instantCoffee);
                break;

            default:
                System.out.println("Неправильний вибір.");
                break;
        }
    }

    @Override
    public String getDescription() {
        return "Додати каву"; // Опис команди для динамічного меню
    }
}
