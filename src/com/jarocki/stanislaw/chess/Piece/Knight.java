package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Knight extends Basic {
    public Knight(Color color, Row row, Column col) {
        super(color, row, col);
        setSymbol(Symbol.KNIGHT);
    }

    public boolean isMoveValid(int toRow, int toCol, Board board) {
        int row = this.getRow().getNum();
        int col = this.getColumn().getNum();
        // Ensure the destination position is within the bounds of the board
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        int dRow = Math.abs(toRow - row);
        int dCol = Math.abs(toCol - col);

        // Ensure the knight is moving in an L-shape
        return (dRow == 2 && dCol == 1) || (dRow == 1 && dCol == 2);
    }

    public Basic copy() {
        return new Knight(this.getColor(), this.getRow(), this.getColumn());
    }
}
