package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Basic {
    private final Color color;
    private Column column;
    private Row row;
    private Symbol symbol;
    public Basic (Color color, Row row, Column column) {
        this.color = color;
        this.column = column;
        this.row = row;
    }

    public Color getColor() {
        return color;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Symbol getSymbol() {
        return this.symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean isMoveValid(int targetRow, int targetColumn, Board board) { return false; }

    public Basic copy(Color color, Row row, Column column) {
        Basic copy = new Basic(color, row, column);
        copy.setSymbol(this.symbol);
        return copy;
    }

}
