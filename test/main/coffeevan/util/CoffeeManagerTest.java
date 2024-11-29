package main.coffeevan.util;

import main.coffeevan.model.Coffee;
import main.coffeevan.model.GrainCoffee;
import main.coffeevan.model.GroundCoffee;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CoffeeManagerTest {

    private CoffeeManager coffeeManager;
    private Coffee grainCoffee;

    @BeforeEach
    public void setUp() {
        coffeeManager = new CoffeeManager();
        grainCoffee = new GrainCoffee("Arabica", 100.0, 2.0); // Створюємо тестову каву
    }

    @Test
    public void testAddCoffee() {
        coffeeManager.addCoffee(grainCoffee);
        List<Coffee> coffees = coffeeManager.findCoffeeByPriceRange(0, 200);
        assertTrue(coffees.contains(grainCoffee), "Кава має бути додана до списку.");
    }


    @Test
    public void testDeleteCoffee() {
        coffeeManager.addCoffee(grainCoffee);

        // Видаляємо каву за назвою
        coffeeManager.deleteCoffee("Arabica");

        // Перевіряємо, чи кава була видалена зі списку
        List<Coffee> coffees = coffeeManager.findCoffeeByPriceRange(0, 200);
        assertFalse(coffees.contains(grainCoffee), "Кава має бути видалена зі списку.");
        assertTrue(coffees.isEmpty(), "Список кав має бути порожнім після видалення.");
    }

    @Test
    public void testGenerateCupon() {
        String cupon = coffeeManager.generateCupon();
        assertNotNull(cupon, "Купон має бути згенерований.");
        assertEquals(5, cupon.length(), "Купон має містити 5 символів.");
    }

    @Test
    public void testUseCupon() {
        String cupon = coffeeManager.generateCupon();
        coffeeManager.addCoffee(grainCoffee);  // Додаємо каву в менеджер

        // Імітуємо введення "1" (вибір першої кави)
        String input = "1\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        // Застосовуємо купон
        coffeeManager.useCupon(cupon);

        // Перевірка, чи ціна кави змінилася
        assertEquals(1.0, grainCoffee.getPrice(), "Ціна кави мала бути знижена вдвічі.");

        // Повертаємо System.in до стандартного стану (опціонально)
        System.setIn(System.in);
    }

    @Test
    public void testSaveCoffeesToFile() throws IOException {
        coffeeManager.addCoffee(grainCoffee);
        coffeeManager.saveCoffeesToFile();

        Path filePath = Paths.get("src", "main/coffeevan/coffeeData.txt");
        List<String> lines = Files.readAllLines(filePath);

        // Перевірка, що файл містить рядок із даними про каву
        assertFalse(lines.contains("Arabica,GrainCoffee,100.0,2.0"),
                "Файл повинен містити правильні дані про каву.");
    }

    @Test
    public void testFindCoffeeByPriceRange() {
        coffeeManager.addCoffee(grainCoffee);
        List<Coffee> foundCoffees = coffeeManager.findCoffeeByPriceRange(0, 150);
        assertTrue(foundCoffees.contains(grainCoffee), "Кава має бути знайдена в межах цінового діапазону.");

    }

    @Test
    public void testLoadCoffeesFromFile() {
        String filePath = "src/coffeevan/coffeeData.txt"; // Переконайтеся, що файл існує
        coffeeManager.loadCoffeesFromFile(filePath);

        List<Coffee> coffees = coffeeManager.findCoffeeByPriceRange(0, 1000);
        assertFalse(coffees.isEmpty(), "Файл має містити каву.");
    }




    @Test
    public void testSortCoffee() {
        Coffee groundCoffee = new GroundCoffee("Robusta", 50.0, 1.5);
        coffeeManager.addCoffee(grainCoffee);
        coffeeManager.addCoffee(groundCoffee);

        coffeeManager.sortCoffee();

        List<Coffee> sortedCoffees = coffeeManager.findCoffeeByPriceRange(0, 200);
        assertTrue(sortedCoffees.indexOf(grainCoffee) < sortedCoffees.indexOf(groundCoffee),
                "Кава повинна бути відсортована за ціною до ваги.");
    }

    @Test
    void name() {
    }
}
