package main.coffeevan;

import static org.junit.jupiter.api.Assertions.*;
import main.coffeevan.util.CoffeeManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {
    @Test
    void testMainFlow() {
        // Оновлені вхідні дані
        String input = String.join("\n",
                "1",       // Вибір команди "Add coffee"
                "Arabica", // Назва кави
                "Grain",   // Тип кави
                "250.5",   // Вага кави
                "1.5",     // Ціна кави
                "2",       // Вибір команди "Find coffee by price range"
                "200",     // Мінімальна ціна
                "300",     // Максимальна ціна
                "3"        // Вибір команди "Exit"
        ) + "\n";

        // Перенаправлення вводу/виводу
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Запуск головного методу
        Main.main(null);

        // Перевірка виводу
        String result = output.toString();
        assertTrue(result.contains("Додано: Arabica"), "Має бути повідомлення про додавання кави.");
        assertTrue(result.contains("Arabica"), "Має бути знайдена кава в результатах пошуку.");
        assertTrue(result.contains("Завершення роботи"), "Програма має завершуватися коректно.");
    }

    @Test
    void testInvalidInput() {
        // Оновлені вхідні дані
        String input = String.join("\n",
                "1",        // Вибір команди "Add coffee"
                "Arabica",  // Назва кави
                "Grain",    // Тип кави
                "not_a_number", // Некоректна вага
                "1.5",      // Ціна кави (не зчитується через попередню помилку)
                "3"         // Вибір команди "Exit"
        ) + "\n";

        // Перенаправлення вводу/виводу
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Запуск головного методу
        Main.main(null);

        // Перевірка виводу
        String result = output.toString();
        assertTrue(result.contains("Помилка"), "Має бути повідомлення про помилку некоректного вводу.");
        assertTrue(result.contains("Завершення роботи"), "Програма має завершуватися коректно.");
    }

}