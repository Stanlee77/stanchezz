package com.jarocki.stanislaw.chess.Pieces;

import com.jarocki.stanislaw.chess.Coordinates.Column;
import com.jarocki.stanislaw.chess.Coordinates.Row;

public class Basic {
    private Color color;
    private Column column;
    private Row row;
    public Boolean[][] legalMoves;
    private Character symbol;
    public Basic (Color color, Column column, Row row) {
        this.color = color;
        this.column = column;
        this.row = row;
        this.legalMoves = new Boolean[8][8];
        cleanLegalMoves();
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

    public Character getSymbol() {
        return this.symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public void cleanLegalMoves() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                legalMoves[row][column] = false;
            }
        }
    }

    public void addLegalMove(Column column, Row row) {
        int rowIdx = row.ordinal();
        int colIdx = column.ordinal();

        if (colIdx >= 0 && colIdx < 8 && rowIdx >= 0 && rowIdx < 8) {
            legalMoves[rowIdx][colIdx] = true;
        }
    }
}
