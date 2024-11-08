package coffeevan.command;

import coffeevan.util.CoffeeManager;

public class SaveToFileCommand implements Command {
    private CoffeeManager coffeeManager;

    public SaveToFileCommand(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    @Override
    public void execute() {
        coffeeManager.saveCoffeesToFile();  // Викликаємо метод збереження кави у файл
    }

    @Override
    public String getDescription() {
        return "Зберегти дані про каву у файл";
    }
}
