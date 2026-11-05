import logic.Cannon;
import model.BattleField;
import ui.AimReader;
import ui.ConsoleWriter;

public class App {
    public static void main(String[] args) {
        // Читаем фамилию и имя из параметров командной строки
        String lastName = (args.length > 0) ? args[0] : "Иванов";
        String firstName = (args.length > 1) ? args[1] : "Иван";

        BattleField battleField = new BattleField();
        Cannon cannon = new Cannon(battleField);
        AimReader reader = new AimReader();
        ConsoleWriter writer = new ConsoleWriter();

        String lastShotResult = "";
        boolean debugMode = false; // Флаг для проверки расстановки кораблей

        while (!battleField.areAllShipsSunk()) {
            writer.clearConsole();
            writer.printGreeting(lastName, firstName);
            writer.printField(battleField, debugMode);

            if (!lastShotResult.isEmpty()) {
                writer.printMessage("Результат: " + lastShotResult);
            }

            int[] coords = reader.readCoordinates();

            // 1. Проверка на команду ВЫХОД
            if (coords == AimReader.COMMAND_EXIT) {
                writer.printMessage("Игра прервана пользователем.");
                return;
            }

            // 2. Проверка на команду НОВАЯ ИГРА
            if (coords == AimReader.COMMAND_NEW_GAME) {
                battleField.initNewGame(); // Пересоздаём пустое поле и расставляем корабли заново
                lastShotResult = "Начата новая игра! Корабли переставлены.";
                continue;
            }

            // 3. Проверка на команду ОТЛАДКА (для проверки работы алгоритма расстановки)
            if (coords == AimReader.COMMAND_DEBUG) {
                debugMode = !debugMode;
                lastShotResult = "Режим проверки расстановки: " + (debugMode ? "ВКЛ" : "ВЫКЛ");
                continue;
            }

            // 4. Обычный выстрел
            lastShotResult = cannon.fire(coords[0], coords[1]);
        }

        writer.clearConsole();
        writer.printGreeting(lastName, firstName);
        writer.printField(battleField, debugMode);
        writer.printMessage("Поздравляем! Все корабли потоплены!");
    }
}