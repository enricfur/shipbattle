package logic;

import model.BattleField;
import model.CellState;
import model.Ship;

public class Cannon {
    // Константы класса для результатов выстрела
    public static final String RESULT_MISS = "Выстрел мимо!";
    public static final String RESULT_HIT = "Попадание в корабль (ранил)!";
    public static final String RESULT_SUNK = "Корабль потоплен!";
    public static final String RESULT_ALREADY_SHOT = "По этим координатам уже стреляли!";

    private final BattleField battleField;

    public Cannon(BattleField battleField) {
        this.battleField = battleField;
    }

    public String fire(int row, int col) {
        CellState currentState = battleField.getFieldStatus(row, col);

        if (currentState == CellState.EMPTY_HIT || currentState == CellState.SHIP_HIT) {
            return RESULT_ALREADY_SHOT;
        }

        if (currentState == CellState.EMPTY_CLOSED) {
            battleField.setFieldStatus(row, col, CellState.EMPTY_HIT);
            return RESULT_MISS;
        }

        if (currentState == CellState.SHIP_CLOSED) {
            battleField.setFieldStatus(row, col, CellState.SHIP_HIT);
            Ship ship = battleField.getShipAt(row, col);
            if (ship != null) {
                ship.hit();
                if (ship.isSunk()) {
                    return RESULT_SUNK;
                }
            }
            return RESULT_HIT;
        }

        return RESULT_MISS;
    }
}