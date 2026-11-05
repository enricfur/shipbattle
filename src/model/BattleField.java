package model;

public class BattleField {
    public static final int SIZE = 10;

    private final CellState[][] field = new CellState[SIZE][SIZE];
    private final Ship[][] shipsGrid = new Ship[SIZE][SIZE];
    private int totalShipsDecks = 0;

    public BattleField() {
        initEmptyField();
        placeStaticShips();
    }

    private void initEmptyField() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                field[r][c] = CellState.EMPTY_CLOSED;
            }
        }
    }

    // Статическая расстановка классического набора кораблей (1х4, 2х3, 3х2, 4х1)
    private void placeStaticShips() {
        placeShip(new Ship(4), 6, 1, true);  // 4-палубный: строка 7 (индекс 6), Б-Д

        placeShip(new Ship(3), 0, 0, false); // 3-палубный: А1-А3
        placeShip(new Ship(3), 2, 8, false); // 3-палубный: И3-И5

        placeShip(new Ship(2), 2, 1, false); // 2-палубный: Б3-Б4 (как в прототипе)
        placeShip(new Ship(2), 0, 4, true);  // 2-палубный: Д1-Е1
        placeShip(new Ship(2), 9, 5, true);  // 2-палубный: Е10-Ж10

        placeShip(new Ship(1), 3, 5, true);  // 1-палубный: Е4 (как в прототипе)
        placeShip(new Ship(1), 8, 1, true);  // 1-палубный: Б9
        placeShip(new Ship(1), 5, 6, true);  // 1-палубный: Ж6
        placeShip(new Ship(1), 9, 9, true);  // 1-палубный: К10
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