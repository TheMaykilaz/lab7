package coffeevan.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import coffeevan.model.Coffee;
import coffeevan.model.GrainCoffee;
import coffeevan.model.GroundCoffee;
import coffeevan.model.InstantCoffeeInBags;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;


public class CoffeeManager {
    private List<Coffee> coffeeList = new ArrayList<>();


    private Map<String, Coffee> coupons = new HashMap<>(); // Купони з прив'язкою до кави

    // Метод для генерації купона
    public String generateCupon() {
        Random random = new Random();

        // Генеруємо купон: 2 випадкові латинські літери і 3 цифри від 1 до 9
        StringBuilder cupon = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            char letter = (char) (random.nextInt(26) + 'A'); // Випадкова літера
            cupon.append(letter);
        }
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(9) + 1; // Випадкова цифра від 1 до 9
            cupon.append(digit);
        }

        String generatedCupon = cupon.toString();
        coupons.put(generatedCupon, null); // Додаємо купон в мапу, не прив'язуючи до кави

        System.out.println("Ваш купон: " + generatedCupon);
        return generatedCupon;
    }

    // Метод для використання купона
    public void useCupon(String cuponCode) {
        Scanner scanner = new Scanner(System.in);

        // Перевіряємо, чи існує введений купон
        if (!coupons.containsKey(cuponCode)) {
            System.out.println("Купон не знайдено або він вже використаний.");
            return;
        }


        // Виводимо список кави для вибору
        System.out.println("Виберіть каву для застосування купона:");
        for (int i = 0; i < coffeeList.size(); i++) {
            System.out.println((i + 1) + ". " + coffeeList.get(i).getName() + " - Ціна: " + coffeeList.get(i).getPrice());
        }

        System.out.print("Введіть номер кави: ");
        int coffeeChoice = scanner.nextInt();

        // Перевірка на правильність вибору кави
        if (coffeeChoice < 1 || coffeeChoice > coffeeList.size()) {
            System.out.println("Неправильний вибір.");
            return;
        }

        // Застосування купону до вибраної кави
        Coffee chosenCoffee = coffeeList.get(coffeeChoice - 1);
        chosenCoffee.setNewPrice(chosenCoffee.getPrice() / 2); // Зменшуємо ціну вдвічі
        coupons.remove(cuponCode); // Видаляємо купон після використання

        System.out.println("Купон застосовано! Нова ціна кави \"" + chosenCoffee.getName() + "\": " + chosenCoffee.getPrice());
    }

    public void addCoffee(Coffee coffee) {
        coffeeList.add(coffee);
        System.out.println("Додано: " + coffee);
    }

    public List<Coffee> findCoffeeByPriceRange(double minPrice, double maxPrice) {
        return coffeeList.stream()
                .filter(coffee -> coffee.getPrice() >= minPrice && coffee.getPrice() <= maxPrice)
                .toList();
    }

    public void loadCoffeesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim(); // Ім'я кави
                    String type = parts[1].trim(); // Тип кави
                    double price = Double.parseDouble(parts[2].trim()); // Ціна
                    double weight = Double.parseDouble(parts[3].trim()); // Вага

                    Coffee coffee = null;
                    switch (type.toLowerCase()) {
                        case "зернова":
                            coffee = new GrainCoffee(name, price, weight);
                            break;
                        case "мелена":
                            coffee = new GroundCoffee(name, price, weight);
                            break;
                        case "в пакетиках":
                            int intPrice = (int) Math.round(price);
                            coffee = new InstantCoffeeInBags(name, (int) weight, price); // Передаємо ім'я кави
                            break;
                        default:
                            System.out.println("Невідомий тип кави: " + type);
                            continue;
                    }

                    addCoffee(coffee);
                } else {
                    System.out.println("Некоректний формат рядка: " + line);
                }
            }
            System.out.println("Завантаження завершено!");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Помилка формату числових даних у файлі.");
        }
    }


    public void sortCoffee() {
        coffeeList.sort(Comparator.comparingDouble(coffee -> coffee.getPrice() / coffee.getWeight()));
        System.out.println("Кава відсортована за співвідношенням ціни до ваги.");
    }

    public void showCoffees() {
        System.out.println("Список кави у фургоні:");
        for (Coffee coffee : coffeeList) {
            System.out.println(coffee);
        }
    }

    public boolean deleteCoffee(String coffeeName) {
        return coffeeList.removeIf(coffee -> coffee.getName().equalsIgnoreCase(coffeeName));
    }

    public void saveCoffeesToFile() {
        String filePath = Paths.get("src", "coffeeData.txt").toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Coffee coffeeItem : coffeeList) {  // Використовуємо coffeeList замість coffee
                writer.write(coffeeItem.getName() + "," + coffeeItem.getType() + "," + coffeeItem.getPrice() + "," + coffeeItem.getWeight());
                writer.newLine();
            }
            System.out.println("Дані про каву збережено у файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

}
