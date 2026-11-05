package ui;

import java.util.Scanner;

public class AimReader {
    public static final String LETTERS = "АБВГДЕЖЗИК";
    public static final int[] COMMAND_EXIT = null;
    public static final int[] COMMAND_NEW_GAME = {-1, -1};
    public static final int[] COMMAND_DEBUG = {-2, -2};

    private final Scanner scanner;

    public AimReader() {
        this.scanner = new Scanner(System.in);
    }

    public int[] readCoordinates() {
        while (true) {
            System.out.print("Введите координаты (например, Е4), 'НОВАЯ ИГРА' или 'ВЫХОД': ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("ВЫХОД") || input.equals("EXIT")) {
                return COMMAND_EXIT;
            }

            if (input.equals("НОВАЯ ИГРА") || input.equals("NEW GAME")) {
                return COMMAND_NEW_GAME;
            }

            if (input.equals("ОТЛАДКА") || input.equals("DEBUG")) {
                return COMMAND_DEBUG;
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