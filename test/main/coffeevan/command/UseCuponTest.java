package main.coffeevan.command;

import main.coffeevan.UseCupon;
import main.coffeevan.model.Coffee;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class UseCuponTest {
    private CoffeeManager coffeeManager;
    private UseCupon command;
    private Coffee grainCoffee;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        grainCoffee = new GrainCoffee("Arabica", 100.0, 2.0);
        coffeeManager.addCoffee(grainCoffee);
        command = new UseCupon(coffeeManager);
    }

    @Test
    void execute() {
        String coupon = coffeeManager.generateCupon();
        String input = "1\n"; // Імітуємо вибір першої кави
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        command.execute();

        // Перевірка, що ціна кави змінилася
        assertEquals(2.0, grainCoffee.getPrice(), "Ціна кави має бути знижена вдвічі.");

        // Відновлення System.in
        System.setIn(System.in);
    }

    @Test
    void getDescription() {
        assertEquals("Використати купон", command.getDescription());
    }
}
