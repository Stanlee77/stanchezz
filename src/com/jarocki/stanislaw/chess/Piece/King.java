package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class King extends Basic {
    private boolean hasMoved;

    public King(Color color, Row row, Column col) {
        super(color, row, col);
        this.setSymbol(Symbol.KING);
        hasMoved = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public boolean isMoveValid(int toRow, int toCol, Board board) {
        int row = this.getRow().getNum();
        int col = this.getColumn().getNum();

        // check if inside the board
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        int dRow = Math.abs(toRow - row);
        int dCol = Math.abs(toCol - col);

        // king can move only by one tile
        if ((dRow != 1 || dCol != 0) && (dRow != 0 || dCol != 1) && (dRow != 1 || dCol != 1)) {
            return false;
        }

        // check if destination is empty or with opponent
        Basic destinationPiece = board.getPiece(toRow, toCol);
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    public Basic copy() {
        King copy = new King(this.getColor(), this.getRow(), this.getColumn());
        copy.hasMoved = hasMoved;
        return copy;
    }
}
