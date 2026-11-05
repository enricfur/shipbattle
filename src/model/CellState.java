package model;

public enum CellState {
    EMPTY_CLOSED, // закрытая пустая клетка
    EMPTY_HIT,    // пустая клетка, по которой был прилет (мимо)
    SHIP_CLOSED,  // закрытая клетка с кораблем
    SHIP_HIT      // клетка с кораблем, по которой был прилет (попадание)
}