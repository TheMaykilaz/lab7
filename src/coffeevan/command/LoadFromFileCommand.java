package coffeevan.command;

import coffeevan.util.CoffeeManager;

import java.util.Scanner;

public class LoadFromFileCommand implements Command {
    private CoffeeManager coffeeManager;

    // Конструктор для ініціалізації coffeeManager
    public LoadFromFileCommand(CoffeeManager coffeeManager) {
        this.coffeeManager = coffeeManager;
    }

    // Виконання команди
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу для завантаження кави: ");
        String filePath = scanner.nextLine();  // Отримання шляху до файлу
        coffeeManager.loadCoffeesFromFile(filePath);
        //C:\\Users\\Nadia\\IdeaProjects\\lab5.1111\\src\\coffee.txt
    }

    // Опис команди для динамічного меню
    @Override
    public String getDescription() {
        return "Завантажити каву з файлу"; // Повертає опис команди
    }
}
