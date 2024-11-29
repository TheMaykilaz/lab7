package main.coffeevan.command;

import main.coffeevan.DeleteCoffee;
import main.coffeevan.util.CoffeeManager;
import main.coffeevan.model.GrainCoffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCoffeeCommandTest {

    private CoffeeManager coffeeManager;
    private DeleteCoffee command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        coffeeManager.addCoffee(new GrainCoffee("Arabica", 100.0, 2.0));
        command = new DeleteCoffee(coffeeManager);
    }

    @Test
    void execute() {
        command.execute();
        assertTrue(coffeeManager.findCoffeeByPriceRange(0, 1000).isEmpty(),
                "Команда має видалити каву.");
    }

    @Test
    void getDescription() {
        assertEquals("Delete coffee", command.getDescription(),
                "Опис команди має бути коректним.");
    }
}
