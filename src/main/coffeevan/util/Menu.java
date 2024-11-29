    package main.coffeevan.util;

    import main.coffeevan.*;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Scanner;

    public class Menu {
        private Map<Integer, Command> commandMap;
        private String userName;
        private CoffeeManager coffeeManager;

        public Menu(String userName) {
            this.userName = userName;
            this.coffeeManager = new CoffeeManager();
            initializeCommands();

        }


        // Ініціалізуємо команди та мапу команд
        private void initializeCommands() {
            commandMap = new HashMap<>();
            commandMap.put(1, new AddCoffeeCommand(coffeeManager));
            commandMap.put(2, new ShowCoffeeCommand(coffeeManager));
            commandMap.put(3, new SortCoffeeCommand(coffeeManager));
            commandMap.put(4, new FindCoffee(coffeeManager));
            commandMap.put(5, new LoadFromFileCommand(coffeeManager));
            commandMap.put(6, new SaveToFileCommand(coffeeManager));
            commandMap.put(7, new DeleteCoffee(coffeeManager));
            commandMap.put(8, new UseCupon(coffeeManager));
            commandMap.put(9, new GenerateCupon(coffeeManager));
        }
        public Map<Integer, Command> getCommandMap() {
            return commandMap;
        }

        // Метод для запуску меню
        public void run() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                printMenu();
                System.out.print("Введіть номер дії: ");
                int choice = scanner.nextInt();

                if (choice == 0) {
                    System.out.println("Вихід з програми...");
                    break;
                }

                // Викликаємо команду відповідно до вибору користувача
                Command command = commandMap.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
                }
            }

            scanner.close();
        }

        // Метод для виводу меню
        public void printMenu() {
            System.out.println("Виберіть дію, " + userName + ":");
            System.out.println("0. Вихід");
            for (Map.Entry<Integer, Command> entry : commandMap.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
            }
        }

    }
