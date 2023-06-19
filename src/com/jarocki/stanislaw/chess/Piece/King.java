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

    public boolean isValidMove(int toRow, int toCol, Board board) {
        int row = this.getRow().getNum();
        int col = this.getColumn().getNum();

        // Ensure the destination position is within the bounds of the board
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        int dRow = Math.abs(toRow - row);
        int dCol = Math.abs(toCol - col);

        // Ensure the king is moving to an adjacent position
        if ((dRow != 1 || dCol != 0) && (dRow != 0 || dCol != 1) && (dRow != 1 || dCol != 1)) {
            return false;
        }

        // Ensure the destination position is either empty or occupied by an opponent's piece
        Basic destinationPiece = board.getPiece(toRow, toCol);
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    public Basic copy() {
        King copy = new King(this.getColor(), this.getRow(), this.getColumn());
        copy.hasMoved = hasMoved;
        return copy;
    }
}
