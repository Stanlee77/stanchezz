package com.jarocki.stanislaw.chess.Pieces;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinates.Column;
import com.jarocki.stanislaw.chess.Coordinates.Row;

public class Pawn extends Basic {

    public Pawn(Basic basic) {
        super(basic.getColor(), basic.getColumn(), basic.getRow());
        this.setSymbol('P');
    }


    public void calcLegalMoves(Row currentRow, Column currentColumn, Board board) {
        cleanLegalMoves();

        boolean isGoingUp = getColor() == Color.WHITE;

        int targetRow = currentRow.ordinal() + (isGoingUp ? 1 : -1);

        if (isValidMove(targetRow, currentColumn.ordinal(), board)) {
            addLegalMove(currentColumn, Row.values()[targetRow]);
        }

        // Check if the pawn can move two steps forward from its starting position
        if (isStartingPosition(currentRow, getColor())) {
            targetRow = currentRow.ordinal() + (2 * (isGoingUp ? 1 : 1));
            if (isValidMove(targetRow, currentColumn.ordinal(), board)) {
                addLegalMove(currentColumn, Row.values()[targetRow]);
            }
        }

        // Check if the pawn can capture diagonally to the left
        int leftColumn = currentColumn.ordinal() - 1;
        if (isValidCapture(targetRow, leftColumn, board)) {
            addLegalMove(Column.values()[leftColumn], Row.values()[targetRow]);
        }

        // Check if the pawn can capture diagonally to the right
        int rightColumn = currentColumn.ordinal() + 1;
        if (isValidCapture(targetRow, rightColumn, board)) {
            addLegalMove(Column.values()[rightColumn], Row.values()[targetRow]);
        }
    }

    private boolean isValidMove(int targetRow, int targetColumn, Board board) {
        // Check if the target position is within the board boundaries and unoccupied
        return targetRow >= 0 && targetRow < 8 && targetColumn >= 0 && targetColumn < 8
                && board.getPiece(targetRow, targetColumn) == null;
    }

    private boolean isValidCapture(int targetRow, int targetColumn, Board board) {
        // Check if the target position is within the board boundaries and contains an opponent's piece
        return targetRow >= 0 && targetRow < 8 && targetColumn >= 0 && targetColumn < 8
                && board.getPiece(targetRow, targetColumn) != null
                && board.getPiece(targetRow, targetColumn).getColor() != getColor();
    }

    private boolean isStartingPosition(Row currentRow, Color color) {
        // Check if the pawn is in its starting position (second row for white, seventh row for black)
        return (color == Color.WHITE && currentRow == Row.TWO)
                || (color == Color.BLACK && currentRow == Row.SEVEN);
    }

    public void move(Column targetColumn, Row targetRow, Board board) {
        calcLegalMoves(getRow(), getColumn(), board);
        if (!legalMoves[targetRow.ordinal()][targetColumn.ordinal()]) {
            System.out.println("This is an illegal move!");
        } else {
            // Perform the move
            board.movePiece(this.getColumn(), this.getRow(), targetColumn, targetRow);
            setRow(targetRow);
            setColumn(targetColumn);
        }
    }
}
