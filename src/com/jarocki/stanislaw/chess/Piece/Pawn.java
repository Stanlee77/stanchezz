package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Pawn extends Basic {

    public Pawn(Basic basic) {
        super(basic.getColor(), basic.getRow(), basic.getColumn());
        this.setSymbol(Symbol.PAWN);
    }

    @Override
    public boolean isMoveValid(int targetRow, int targetColumn, Board board) {
        // Check if the target position is within the board boundaries
        if (targetRow < 0 || targetRow >= 8 || targetColumn < 0 || targetColumn >= 8) {
            return false;
        }

        // Check if the target position is unoccupied
        if (board.getPiece(targetRow, targetColumn) == null) {
            // Regular move (no capture)
            int col = getColumn().getNum();
            if (getColumn().getNum() == targetColumn) {
                // Check if the pawn is moving forward by one row
                if (getColor() == Color.WHITE) {
                    return targetRow - getRow().getNum() == 1 || (isStartingPosition(getRow(), getColor()) && targetRow - getRow().getNum() == 2);
                } else {
                    return targetRow - getRow().getNum() == -1 || (isStartingPosition(getRow(), getColor()) && targetRow - getRow().getNum() == -2);
                }
            }
            // Pawn cannot move diagonally without capturing
            System.out.println("Invalid move (Pawn.isMoveValid)");
            return false;
        } else {
            // Check if the target position contains an opponent's piece
            return isValidCapture(targetRow, targetColumn, board);
        }
    }

    private boolean isValidCapture(int targetRow, int targetColumn, Board board) {
        // Check if the target position is within the board boundaries and contains an opponent's piece
        if (targetRow < 0 || targetRow >= 8 || targetColumn < 0 || targetColumn >= 8) {
            return false;
        }

        Basic targetPiece = board.getPiece(targetRow, targetColumn);
        if (targetPiece == null) {
            return false; // Target position is not occupied by an opponent's piece
        }

        if (targetColumn == getColumn().getNum()) {
            // Move is diagonal to the top left
            int row = getRow().getNum() + 2;
            Color color = getColor();
            if (getColor() == Color.WHITE && targetRow == getRow().getNum() + 2) {
                return true;
            } else if (getColor() == Color.BLACK && targetRow == getRow().getNum() - 2) {
                return true;
            }
        } else if (targetColumn == getColumn().getNum() + 1) {
            // Move is diagonal to the top right
            if (getColor() == Color.WHITE && targetRow == getRow().getNum() + 1) {
                return true;
            } else if (getColor() == Color.BLACK && targetRow == getRow().getNum() - 1) {
                return true;
            }
        }

        return false; // Move is not diagonal by one field to the top right or top left
    }

    private boolean isStartingPosition(Row currentRow, Color color) {
        // Check if the pawn is in its starting position (second row for white, seventh row for black)
        return (color == Color.WHITE && currentRow == Row.TWO)
                || (color == Color.BLACK && currentRow == Row.SEVEN);
    }
}
