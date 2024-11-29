package main.coffeevan.command;

import main.coffeevan.SortCoffeeCommand;
import main.coffeevan.util.CoffeeManager;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.model.GroundCoffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortCoffeeCommandTest {

    private CoffeeManager coffeeManager;
    private SortCoffeeCommand command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        coffeeManager.addCoffee(new GrainCoffee("Arabica", 100.0, 2.0)); // Ціна: 2.0, вага: 100.0
        coffeeManager.addCoffee(new GroundCoffee("Robusta", 50.0, 1.5)); // Ціна: 1.5, вага: 50.0
        command = new SortCoffeeCommand(coffeeManager);
    }

    @Test
    void execute() {
        command.execute();

        List<?> sorted = coffeeManager.findCoffeeByPriceRange(0, 1000);

        // Оскільки sorted містить конкретні підкласи, перевіряємо їх за допомогою приведення типу
        GrainCoffee firstCoffee = (GrainCoffee) sorted.get(0);
        GroundCoffee secondCoffee = (GroundCoffee) sorted.get(1);

        // Обчислюємо співвідношення ціни до ваги
        double priceToWeightRatio1 = firstCoffee.getPrice() / firstCoffee.getWeight();
        double priceToWeightRatio2 = secondCoffee.getPrice() / secondCoffee.getWeight();

        // Перевірка, що перша кава має менше співвідношення ціни до ваги
        assertTrue(priceToWeightRatio1 <= priceToWeightRatio2,
                "Кава має бути відсортована за співвідношенням ціни до ваги.");
    }

    @Test
    void getDescription() {
        assertEquals("Сортувати каву за критеріями", command.getDescription(),
                "Опис команди має бути коректним.");
    }
}
