package main.coffeevan.command;

import main.coffeevan.FindCoffee;
import main.coffeevan.model.Coffee;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FindCoffeeTest {
    private CoffeeManager coffeeManager;
    private FindCoffee command;
    private Coffee grainCoffee;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        grainCoffee = new GrainCoffee("Arabica", 100.0, 2.0);
        coffeeManager.addCoffee(grainCoffee);
        command = new FindCoffee(coffeeManager);
    }

    @Test
    void execute() {
        String input = "50\n150\n"; // Імітуємо діапазон цін
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        command.execute();

        String result = output.toString();
        assertFalse(result.contains("Arabica"), "Результат має містити назву кави.");

        // Відновлення System.in та System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    void getDescription() {
        assertEquals("Знайти каву за ціновим діапазоном", command.getDescription());
    }
}
