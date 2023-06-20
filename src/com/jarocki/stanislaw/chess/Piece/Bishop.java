package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Bishop extends Basic {
    public Bishop(Color color, Row row, Column col) {
        super(color, row, col);
        setSymbol(getSymbol().BISHOP);
    }

    @Override
    public boolean isMoveValid(int toRow, int toCol, Board board) {
        int row = this.getRow().getNum();
        int col = this.getColumn().getNum();

        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        int dRow = toRow - row;
        int dCol = toCol - col;

        // check if moving on diagonal
        if (Math.abs(dRow) != Math.abs(dCol)) {
            return false;
        }

        // check if something between bishop and destination tile
        int stepRow = dRow > 0 ? 1 : -1;
        int stepCol = dCol > 0 ? 1 : -1;

        int currentRow = row + stepRow;
        int currentCol = col + stepCol;

        while (currentRow != toRow || currentCol != toCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false;
            }

            currentRow += stepRow;
            currentCol += stepCol;
        }

        // check destination clear/with opponent
        Basic destinationPiece = board.getPiece(toRow, toCol);
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    public Basic copy() {
        return new Bishop(this.getColor(), this.getRow(), this.getColumn());
    }
}
