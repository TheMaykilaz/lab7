package main.coffeevan.command;
import java.util.List;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import main.coffeevan.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import main.coffeevan.LoadFromFileCommand;
import main.coffeevan.model.Coffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LoadFromFileCommandTest {

    private CoffeeManager coffeeManager;
    private LoadFromFileCommand command;

    @BeforeEach
    void setUp() {
        coffeeManager = new CoffeeManager();
        command = new LoadFromFileCommand(coffeeManager);
    }

    @Test
    void execute() {
        Coffee coffee = new Coffee("Arabica", "Grain", 250.5, 1.5);

        // Додаємо каву до списку через метод addCoffee
        coffeeManager.addCoffee(coffee);

        // Зберігаємо каву у файл
        coffeeManager.saveCoffeesToFile();

        // Очистимо список перед завантаженням
        coffeeManager.getCoffeeList().clear();

        // Перевіряємо, що список порожній до виконання команди
        assertTrue(coffeeManager.getCoffeeList().isEmpty(), "Список кав має бути порожнім перед завантаженням.");

        // Мокаємо ввід
        String input = "y\n"; // Симулюємо, що користувач підтверджує дію
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Виконуємо команду завантаження з файлу
        command.execute();

        // Перевіряємо, чи кава завантажена з файлу
        assertFalse(coffeeManager.findCoffeeByPriceRange(0, 1000).isEmpty(),
                "Команда має завантажити каву з файлу.");

        // Перевіряємо, що завантажена кава відповідає доданій
        List<Coffee> coffees = coffeeManager.findCoffeeByPriceRange(0, 1000);
        assertTrue(coffees.stream().anyMatch(c -> c.getName().equals("Arabica")),
                "Завантажена кава повинна містити 'Arabica'.");
    }



    @Test
    void getDescription() {
        assertEquals("Завантажити каву з файлу", command.getDescription(),
                "Опис команди має бути коректним.");
    }
}
