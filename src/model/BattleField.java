package model;

import java.util.Random;

public class BattleField {
    public static final int SIZE = 10;
    private static final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    private final CellState[][] field = new CellState[SIZE][SIZE];
    private final Ship[][] shipsGrid = new Ship[SIZE][SIZE];
    private final Random random = new Random(); // Без фиксированного seed для уникальных расстановок
    private int totalShipsDecks = 0;

    public BattleField() {
        initNewGame();
    }

    // Метод для инициализации и сброса поля (пригодится и при старте, и при перезапуске)
    public final void initNewGame() {
        initEmptyField();
        placeRandomShips();
    }

    private void initEmptyField() {
        totalShipsDecks = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                field[r][c] = CellState.EMPTY_CLOSED;
                shipsGrid[r][c] = null; // Очищаем старые корабли при перезапуске
            }
        }
    }

    public void placeRandomShips() {
        for (int decks : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(SIZE);
                int col = random.nextInt(SIZE);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(decks, row, col, horizontal)) {
                    placeShip(new Ship(decks), row, col, horizontal);
                    placed = true;
                }
            }
        }
    }

    // Проверка: помещается ли корабль и нет ли рядом других кораблей
    private boolean canPlaceShip(int decks, int row, int col, boolean horizontal) {
        for (int i = 0; i < decks; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;

            // 1. Проверка выхода за границы поля
            if (r >= SIZE || c >= SIZE) {
                return false;
            }

            // 2. Проверка самой клетки и всех соседних клеток (включая диагонали)
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int nr = r + dr;
                    int nc = c + dc;
                    // Если соседняя клетка в пределах поля и там уже есть корабль — ставить нельзя
                    if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE) {
                        if (field[nr][nc] == CellState.SHIP_CLOSED) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeShip(Ship ship, int row, int col, boolean horizontal) {
        for (int i = 0; i < ship.getDecks(); i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            field[r][c] = CellState.SHIP_CLOSED;
            shipsGrid[r][c] = ship;
            totalShipsDecks++;
        }
    }

    public CellState getFieldStatus(int row, int col) {
        return field[row][col];
    }

    public void setFieldStatus(int row, int col, CellState status) {
        field[row][col] = status;
    }

    public Ship getShipAt(int row, int col) {
        return shipsGrid[row][col];
    }

    public boolean areAllShipsSunk() {
        int hitDecks = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (field[r][c] == CellState.SHIP_HIT) {
                    hitDecks++;
                }
            }
        }
        return hitDecks == totalShipsDecks;
    }
}