package main.coffeevan.command;

import main.coffeevan.SaveToFileCommand;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveToFileCommandTest {

    private CoffeeManager coffeeManager;
    private SaveToFileCommand command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        command = new SaveToFileCommand(coffeeManager);
    }

    @Test
    void execute() {
        coffeeManager.addCoffee(new GrainCoffee("Arabica", 100.0, 2.0));
        command.execute();

        assertDoesNotThrow(() -> coffeeManager.loadCoffeesFromFile("src/coffeevan/coffeeData.txt"),
                "Команда має зберегти каву у файл.");
    }

    @Test
    void getDescription() {
        assertEquals("Зберегти дані про каву у файл", command.getDescription(),
                "Опис команди має бути коректним.");
    }
}
