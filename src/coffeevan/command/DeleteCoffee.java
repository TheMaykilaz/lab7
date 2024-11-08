package coffeevan.command;

import coffeevan.util.CoffeeManager;
import java.util.Scanner;

public class DeleteCoffee implements Command {
    private CoffeeManager coffeeManager;

    public DeleteCoffee(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву кави для видалення: ");
        String coffeeName = scanner.nextLine();

        boolean isDeleted = coffeeManager.deleteCoffee(coffeeName);

        if (isDeleted) {
            System.out.println("Каву '" + coffeeName + "' успішно видалено.");
        } else {
            System.out.println("Каву з назвою '" + coffeeName + "' не знайдено.");
        }
    }

    @Override
    public String getDescription() {
        return "Видалити каву";
    }
}
