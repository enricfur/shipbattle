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

        while (!battleField.areAllShipsSunk()) {
            writer.clearConsole();
            writer.printGreeting(lastName, firstName);
            writer.printField(battleField);

            if (!lastShotResult.isEmpty()) {
                writer.printMessage("Результат: " + lastShotResult);
            }

            int[] coords = reader.readCoordinates();
            if (coords == null) {
                writer.printMessage("Игра прервана пользователем.");
                return;
            }

            lastShotResult = cannon.fire(coords[0], coords[1]);
        }

        writer.clearConsole();
        writer.printGreeting(lastName, firstName);
        writer.printField(battleField);
        writer.printMessage("Поздравляем! Все корабли потоплены!");
    }
}