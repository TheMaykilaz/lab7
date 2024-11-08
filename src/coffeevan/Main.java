package coffeevan;

import coffeevan.util.Menu;
import java.util.Scanner;

public class Main {
    //SERVICES
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ласкаво просимо вас відвідати запашної кави. Введіть своє ім'я: ");
        String name = scanner.nextLine();

        // Створюємо меню з іменем користувача
        Menu menu = new Menu(name);

        // Запускаємо меню
        menu.run();
    }
}
