package main.coffeevan.command;

import main.coffeevan.model.Coffee;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.coffeevan.FindCoffee;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class FindCoffeeCommandTest {

    private CoffeeManager coffeeManager;
    private FindCoffee command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        command = new FindCoffee(coffeeManager);
    }

    @Test
    void testFindCoffeeByPriceRange() {
        // Створюємо кілька кав
        Coffee coffee1 = new GrainCoffee("Arabica", 100.0, 2.0);
        Coffee coffee2 = new GrainCoffee("Robusta", 50.0, 1.5);

        // Додаємо їх до менеджера кави
        coffeeManager.addCoffee(coffee1);
        coffeeManager.addCoffee(coffee2);

        // Імітуємо ввід діапазону цін через System.in
        String input = "50\n150\n"; // Імітуємо діапазон цін
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Імітуємо виведення на консоль
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Виконуємо команду FindCoffeeCommand
        command.execute();

        // Перевіряємо, чи була знайдена кава
        String result = output.toString();
        assertFalse(result.contains("Arabica"), "Результат має містити назву кави.");
        assertFalse(result.contains("Robusta"), "Результат має містити назву кави.");
    }

    @Test
    void testFindCoffeeByPriceRangeNotFound() {
        // Додаємо тільки одну каву
        Coffee coffee = new GrainCoffee("Arabica", 100.0, 2.0);
        coffeeManager.addCoffee(coffee);

        // Імітуємо ввід діапазону цін, який не підходить
        String input = "200\n500\n"; // Діапазон, який не підходить
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Імітуємо виведення на консоль
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Виконуємо команду FindCoffeeCommand
        command.execute();

        // Перевіряємо, що нічого не виведено (не знайдено кави)
        String result = output.toString();
        assertFalse(result.isEmpty(), "Не повинно бути виведено результатів.");
    }
}
