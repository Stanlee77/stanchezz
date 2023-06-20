package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Knight extends Basic {
    public Knight(Color color, Row row, Column col) {
        super(color, row, col);
        setSymbol(Symbol.KNIGHT);
    }
    @Override
    public boolean isMoveValid(int toRow, int toCol, Board board) {
        int row = this.getRow().getNum();
        int col = this.getColumn().getNum();
        // make sure the destination coordinates are on the board
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        int dRow = Math.abs(toRow - row);
        int dCol = Math.abs(toCol - col);

        // knight can move only in "L" path = to cols up one row vert and vice versa
        return (dRow == 2 && dCol == 1) || (dRow == 1 && dCol == 2);
    }

    public Basic copy() {
        return new Knight(this.getColor(), this.getRow(), this.getColumn());
    }
}
