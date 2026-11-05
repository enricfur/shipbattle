package ui;

import model.BattleField;
import model.CellState;

public class ConsoleWriter {
    public static final char SYMBOL_MISS = '*';
    public static final char SYMBOL_HIT = 'x';
    public static final char SYMBOL_HIDDEN = ' ';

    public void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Если консоль не поддерживает очистку (например, внутри IDE), просто делаем отступ
            System.out.println("\n-------------------------------------------");
        }
    }

    public void printGreeting(String lastName, String firstName) {
        System.out.println("Игрок " + lastName + " " + firstName);
    }

    public void printField(BattleField field) {
        System.out.println("    А   Б   В   Г   Д   Е   Ж   З   И   К");
        System.out.println("  ┌───────────────────────────────────────┐");

        for (int r = 0; r < BattleField.SIZE; r++) {
            System.out.printf("%2d│", r + 1);
            for (int c = 0; c < BattleField.SIZE; c++) {
                CellState state = field.getFieldStatus(r, c);
                char symbol = SYMBOL_HIDDEN;
                if (state == CellState.EMPTY_HIT) {
                    symbol = SYMBOL_MISS;
                } else if (state == CellState.SHIP_HIT) {
                    symbol = SYMBOL_HIT;
                }
                System.out.print(" " + symbol + "  ");
            }
            System.out.println("│");
        }
        System.out.println("  └───────────────────────────────────────┘");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}