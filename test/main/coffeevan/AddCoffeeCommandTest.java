package main.coffeevan.command;

import main.coffeevan.AddCoffeeCommand;
import main.coffeevan.model.Coffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AddCoffeeCommandTest {

    private CoffeeManager coffeeManager;
    private AddCoffeeCommand command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        command = new AddCoffeeCommand(coffeeManager);
    }

    @Test
    void testExecuteValidCoffee() {
        String input = "Arabica\nGrain\n250.5\n1.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        command.execute();

        assertFalse(coffeeManager.getCoffeeList().isEmpty(), "Список кави не має бути порожнім.");
        Coffee coffee = coffeeManager.getCoffeeList().get(0);
        assertEquals("Arabica", coffee.getName());
        assertEquals("Grain", coffee.getType());
        assertEquals(250.5, coffee.getPrice());
        assertEquals(1.5, coffee.getWeight());
    }

    @Test
    void testExecuteInvalidCoffee() {
        String input = "Arabica\nGrain\nabc\n1.5\n"; // Невірна ціна
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        command.execute();

        assertTrue(coffeeManager.getCoffeeList().isEmpty(), "Список кави має бути порожнім після невірного вводу.");
        String result = output.toString();
        assertTrue(result.contains("Помилка"), "Команда має вивести повідомлення про помилку.");
    }

    @Test
    void testGetDescription() {
        assertEquals("Add new coffee", command.getDescription(), "Опис команди має бути коректним.");
    }
}
