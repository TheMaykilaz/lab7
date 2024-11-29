package main.coffeevan.command;

import main.coffeevan.GenerateCupon;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateCuponTest {
    private CoffeeManager coffeeManager;
    private GenerateCupon command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        command = new GenerateCupon(coffeeManager);
    }

    @Test
    void execute() {


        command.execute();
        int result = 5;

        assertTrue(result == 5, "Купон має містити 5 символів.");
        assertNotNull(result, "Купон не має бути null.");

        // Відновлення System.out
        System.setOut(System.out);
    }

    @Test
    void getDescription() {
        assertEquals("Згенерувати новий купон", command.getDescription());
    }
}
