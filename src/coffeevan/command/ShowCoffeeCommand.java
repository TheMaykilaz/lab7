package coffeevan.command;

import coffeevan.util.CoffeeManager;

public class ShowCoffeeCommand implements Command {
    private CoffeeManager coffeeManager;

    public ShowCoffeeCommand(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        coffeeManager.showCoffees(); // Виклик методу для показу всіх кав
    }

    @Override
    public String getDescription() {
        return "Показати весь асортимент"; // Опис команди для відображення в меню
    }
}
