package main.coffeevan.command;

import main.coffeevan.ShowCoffeeCommand;
import main.coffeevan.model.Coffee;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ShowCoffeeCommandTest {
    private CoffeeManager coffeeManager;
    private ShowCoffeeCommand command;
    private Coffee grainCoffee;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        grainCoffee = new GrainCoffee("Arabica", 100.0, 2.0);
        coffeeManager.addCoffee(grainCoffee);
        command = new ShowCoffeeCommand(coffeeManager);
    }

    @Test
    void execute() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        command.execute();

        String result = output.toString();
        assertTrue(result.contains("Arabica"), "Результат має містити назву кави.");
        assertTrue(result.contains("100.0"), "Результат має містити ціну кави.");

        // Відновлення System.out
        System.setOut(System.out);
    }

    @Test
    void getDescription() {
        assertEquals("Показати весь асортимент", command.getDescription());
    }
}
