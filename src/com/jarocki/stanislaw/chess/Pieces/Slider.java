package com.jarocki.stanislaw.chess.Pieces;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinates.Column;
import com.jarocki.stanislaw.chess.Coordinates.Row;

public class Slider extends Basic {
    public boolean isMovingDiagonally;
    public boolean isMovingOrtagonally;

    public Slider(Basic basic, boolean isMovingDiagonally, boolean isMovingOrtagonally) {
        super(basic.getColor(), basic.getColumn(), basic.getRow());
        this.isMovingDiagonally = isMovingDiagonally;
        this.isMovingOrtagonally = isMovingOrtagonally;
    }

    public boolean getIsMovingDiagonally() {
        return isMovingDiagonally;
    }

    public boolean getIsMovingOrtagonally() {
        return isMovingOrtagonally;
    }

    public boolean checkIsPathClear(Column targetCol, Row targetRow, Board board) {
        int currentCol = getColumn().ordinal();
        int currentRow = getRow().ordinal();

        // Check if the path is clear for diagonal movement
        if (this.isMovingDiagonally) {
            int colDiff = targetCol.ordinal() - currentCol;
            int rowDiff = targetRow.ordinal() - currentRow;
            int colDirection = Integer.signum(colDiff);
            int rowDirection = Integer.signum(rowDiff);

            // Check each position along the diagonal path
            for (int i = 1; i < Math.abs(colDiff); i++) {
                int checkCol = currentCol + i * colDirection;
                int checkRow = currentRow + i * rowDirection;
                if (board.getPiece(checkRow, checkCol) != null) {
                    return false; // Path is not clear
                }
            }
        }

        // Check if the path is clear for orthogonal movement
        if (this.isMovingOrtagonally) {
            int colDiff = targetCol.ordinal() - currentCol;
            int rowDiff = targetRow.ordinal() - currentRow;

            // Check if moving horizontally
            if (colDiff != 0 && rowDiff == 0) {
                int colDirection = Integer.signum(colDiff);
                for (int i = 1; i < Math.abs(colDiff); i++) {
                    int checkCol = currentCol + i * colDirection;
                    if (board.getPiece(currentRow, checkCol) != null) {
                        return false; // Path is not clear
                    }
                }
            }

            // Check if moving vertically
            if (colDiff == 0 && rowDiff != 0) {
                int rowDirection = Integer.signum(rowDiff);
                for (int i = 1; i < Math.abs(rowDiff); i++) {
                    int checkRow = currentRow + i * rowDirection;
                    if (board.getPiece(checkRow, currentCol) != null) {
                        return false; // Path is not clear
                    }
                }
            }
        }

        return true; // Path is clear
    }
}
