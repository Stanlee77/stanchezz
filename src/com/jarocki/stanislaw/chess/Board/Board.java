package com.jarocki.stanislaw.chess.Board;

import com.jarocki.stanislaw.chess.Coordinates.Column;
import com.jarocki.stanislaw.chess.Coordinates.Row;
import com.jarocki.stanislaw.chess.Pieces.Basic;
import com.jarocki.stanislaw.chess.Pieces.Color;
import com.jarocki.stanislaw.chess.Pieces.Pawn;

public class Board {
    private Basic[][] fields;

    public Board() {
        fields = new Basic[8][8];
    }

    public void setUpPieces() {
        for (int col = 0; col < 8; col++) {
            Basic basicWhitePiece = new Basic(Color.WHITE, Column.values()[col], Row.TWO);
            Basic basicBlackPiece = new Basic(Color.BLACK, Column.values()[col], Row.TWO);
            fields[Row.TWO.ordinal()][Column.values()[col].ordinal()] = new Pawn(basicWhitePiece);
            fields[Row.SEVEN.ordinal()][Column.values()[col].ordinal()] = new Pawn(basicBlackPiece);
        }

//        fields[Column.A.ordinal()][Row.ONE.ordinal()] = new Rook(new Basic(Color.WHITE, Column.A, Row.ONE));
//        fields[Column.H.ordinal()][Row.ONE.ordinal()] = new Rook(new Basic(Color.WHITE, Column.H, Row.ONE));
//        fields[Column.A.ordinal()][Row.EIGHT.ordinal()] = new Rook(new Basic(Color.BLACK, Column.A, Row.EIGHT));
//        fields[Column.H.ordinal()][Row.EIGHT.ordinal()] = new Rook(new Basic(Color.BLACK, Column.H, Row.EIGHT));
//
//        fields[Column.C.ordinal()][Row.ONE.ordinal()] = new Bishop(new Basic(Color.WHITE, Column.C, Row.ONE));
//        fields[Column.F.ordinal()][Row.ONE.ordinal()] = new Bishop(new Basic(Color.WHITE, Column.F, Row.ONE));
//        fields[Column.C.ordinal()][Row.EIGHT.ordinal()] = new Bishop(new Basic(Color.BLACK, Column.C, Row.EIGHT));
//        fields[Column.F.ordinal()][Row.EIGHT.ordinal()] = new Bishop(new Basic(Color.BLACK, Column.F, Row.EIGHT));
    }

    public Basic getPiece(int row, int column) {
        if (row >= 0 && row < 8 && column >= 0 && column < 8) {
            return fields[row][column];
        } else {
            return null;
        }
    }
    public void movePiece(Column currentColumn, Row currentRow, Column targetColumn, Row targetRow) {
        int currRow = currentRow.ordinal();
        int currCol = currentColumn.ordinal();
        int targRow = targetRow.ordinal();
        int targCol = targetColumn.ordinal();
        Basic piece = fields[currRow][currCol];
        fields[currRow][currCol] = null;
        fields[targRow][targCol] = piece;
    }

    public void printBoard() {
        System.out.println();

        System.out.print("  ");
        for (Column column : Column.values()) {
            System.out.print(" " + column.name() + " ");
        }
        System.out.println();

        for (int row = 7; row >= 0; row--) {  // Iterate rows in reversed order
            System.out.print(Row.values()[row].ordinal() + 1 + " ");

            for (int col = 0; col < 8; col++) {
                Basic piece = fields[row][col];
                boolean isLightPurple = (row + col) % 2 == 0;  // Determine if the tile should be light purple
                boolean isLightBlue = (row + col) % 2 != 0;    // Determine if the tile should be light blue

                if (isLightPurple) {
                    System.out.print("\u001B[48;5;53m");
                } else if (isLightBlue) {
                    System.out.print("\u001B[48;5;17m");
                }

                if (piece != null) {
                    if (piece.getColor() == Color.WHITE) {
                        System.out.print(" \u001B[93m" + piece.getSymbol() + " \u001B[0m");  // light yellow - white
                    } else {
                        System.out.print(" \u001B[94m" + piece.getSymbol() + " \u001B[0m");  // black - light blue
                    }
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("\u001B[0m");
        }
    }

}
