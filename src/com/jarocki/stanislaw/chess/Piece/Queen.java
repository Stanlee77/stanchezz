package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Queen extends Basic {
    public Queen(Color color, Row row, Column col) {
        super(color, row, col);
        setSymbol(Symbol.QUEEN);
    }

    public boolean isMoveValid(int toRow, int toCol, Board board) {
        int row = this.getRow().getNum();
        int col = this.getColumn().getNum();
        // Ensure the destination position is within the bounds of the board
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        int dRow = toRow - row;
        int dCol = toCol - col;

        // Ensure the queen is moving along a straight line or a diagonal
        if (dRow != 0 && dCol != 0 && Math.abs(dRow) != Math.abs(dCol)) {
            return false;
        }

        // Check for obstruction along the path
        int stepRow = dRow == 0 ? 0 : (dRow > 0 ? 1 : -1);
        int stepCol = dCol == 0 ? 0 : (dCol > 0 ? 1 : -1);

        int currentRow = row + stepRow;
        int currentCol = col + stepCol;

        while (currentRow != toRow || currentCol != toCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false;
            }

            currentRow += stepRow;
            currentCol += stepCol;
        }

        // Ensure the destination position is either empty or occupied by an opponent's piece
        Basic destinationPiece = board.getPiece(toRow, toCol);
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    public Basic copy() {
        return new Queen(this.getColor(), this.getRow(), this.getColumn());
    }
}
