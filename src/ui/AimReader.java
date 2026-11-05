package ui;

import java.util.Scanner;

public class AimReader {
    public static final String LETTERS = "АБВГДЕЖЗИК";
    private final Scanner scanner;

    public AimReader() {
        this.scanner = new Scanner(System.in);
    }

    public int[] readCoordinates() {
        while (true) {
            System.out.print("Введите координаты для удара (например, Е4 или ВЫХОД): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("ВЫХОД") || input.equals("EXIT")) {
                return null; // Сигнал для завершения игры
            }

            if (input.length() < 2 || input.length() > 3) {
                System.out.println("Ошибка формата! Введите букву (А-К) и число (1-10).");
                continue;
            }

            char letter = input.charAt(0);
            int col = LETTERS.indexOf(letter);

            if (col == -1) {
                System.out.println("Неверная буква! Используйте русские буквы: А, Б, В, Г, Д, Е, Ж, З, И, К.");
                continue;
            }

            try {
                int row = Integer.parseInt(input.substring(1)) - 1;
                if (row >= 0 && row < 10) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Число должно быть от 1 до 10!");
                }
            } catch (NumberFormatException e) {
                System.out.println("После буквы должно идти число от 1 до 10!");
            }
        }
    }
}