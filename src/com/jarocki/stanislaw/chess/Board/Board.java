package com.jarocki.stanislaw.chess.Board;

import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;
import com.jarocki.stanislaw.chess.Piece.*;

import javax.xml.stream.events.StartDocument;

public class Board {
    private final Basic[][] fields;
    private boolean hasWhiteKingMoved;
    private boolean hasBlackKingMoved;
    private Pawn lastMovedPawn;

    public Board() {
        fields = new Basic[8][8];
        this.hasWhiteKingMoved = false;
        this.hasBlackKingMoved = false;
        this.setUpPieces();
    }

    public void setUpPieces() {
        for (int col = 0; col < 8; col++) {
            fields[Row.TWO.getNum()][col] = new Pawn(new Basic(Color.WHITE, Row.TWO, Column.values()[col]));
            fields[Row.SEVEN.getNum()][col] = new Pawn(new Basic(Color.BLACK, Row.SEVEN, Column.values()[col]));
        }

        fields[Row.ONE.getNum()][Column.A.getNum()] = new Rook(Color.WHITE, Row.ONE, Column.A);
        fields[Row.ONE.getNum()][Column.H.getNum()] = new Rook(Color.WHITE, Row.ONE, Column.H);
        fields[Row.EIGHT.getNum()][Column.A.getNum()] = new Rook(Color.BLACK, Row.EIGHT, Column.A);
        fields[Row.EIGHT.getNum()][Column.H.getNum()] = new Rook(Color.BLACK, Row.EIGHT, Column.H);

        fields[Row.ONE.getNum()][Column.E.getNum()] = new King(Color.WHITE, Row.ONE, Column.E);
        fields[Row.EIGHT.getNum()][Column.E.getNum()] = new King(Color.BLACK, Row.EIGHT, Column.E);

        fields[Row.ONE.getNum()][Column.D.getNum()] = new Queen(Color.WHITE, Row.ONE, Column.D);
        fields[Row.EIGHT.getNum()][Column.D.getNum()] = new Queen(Color.BLACK, Row.EIGHT, Column.D);

        fields[Row.ONE.getNum()][Column.B.getNum()] = new Knight(Color.WHITE, Row.ONE, Column.B);
        fields[Row.ONE.getNum()][Column.G.getNum()] = new Knight(Color.WHITE, Row.ONE, Column.G);
        fields[Row.EIGHT.getNum()][Column.B.getNum()] = new Knight(Color.BLACK, Row.EIGHT, Column.B);
        fields[Row.EIGHT.getNum()][Column.G.getNum()] = new Knight(Color.BLACK, Row.EIGHT, Column.G);

        fields[Row.ONE.getNum()][Column.C.ordinal()] = new Bishop(Color.WHITE, Row.ONE, Column.C);
        fields[Row.ONE.getNum()][Column.F.ordinal()] = new Bishop(Color.WHITE, Row.ONE, Column.F);
        fields[Row.EIGHT.getNum()][Column.C.ordinal()] = new Bishop(Color.BLACK, Row.EIGHT, Column.C);
        fields[Row.EIGHT.getNum()][Column.F.ordinal()] = new Bishop(Color.BLACK, Row.EIGHT, Column.F);
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

        for (int row = 7; row >= 0; row--) {
            System.out.print(Row.values()[row].ordinal() + 1 + " ");

            for (int col = 0; col < 8; col++) {
                Basic piece = fields[row][col];
                boolean isLightPurple = (row + col) % 2 == 0;
                boolean isLightBlue = (row + col) % 2 != 0;

                if (isLightPurple) {
                    System.out.print("\u001B[48;5;53m");
                } else if (isLightBlue) {
                    System.out.print("\u001B[48;5;17m");
                }

                if (piece != null) {
                    if (piece.getColor() == Color.WHITE) {
                        System.out.print(" \u001B[93m" + piece.getSymbol().getChar() + " \u001B[0m");  // light yellow - white
                    } else {
                        System.out.print(" \u001B[94m" + piece.getSymbol().getChar() + " \u001B[0m");  // black - light blue
                    }
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("\u001B[0m");
        }
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Basic piece = fields[fromRow][fromCol];
        fields[fromRow][fromCol] = null;

        Row targetRow = Row.getRowByNum(String.valueOf(toRow+1));
        Column targetCol = Column.getColByNum(String.valueOf(toCol+1));
        piece.setRow(targetRow);
        piece.setColumn(targetCol);

        // remove en passant captured pawn from board
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            if (Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1 && fields[toRow][toCol] == null) {
                int capturedPawnRow = fromRow;
                int capturedPawnCol = toCol;
                removePiece(capturedPawnRow, capturedPawnCol);
            }
        }

        fields[toRow][toCol] = piece;

        // en passant, castling
        if (piece instanceof King) {
            if (piece.getColor() == Color.WHITE) {
                this.hasWhiteKingMoved = true;
            } else {
                this.hasBlackKingMoved = true;
            }
        } else if (piece instanceof Pawn) {
            if (Math.abs(fromRow - toRow) == 2) {
                lastMovedPawn = (Pawn) piece;
            } else {
                lastMovedPawn = null;
            }
        }
    }

    public boolean isMoveValid(int fromRow, int fromCol, int toRow, int toCol, Color currPlayerColor) {
        // check if not trying to go from or to outside the board
        if (fromRow < 0 || fromRow >= 8 || fromCol < 0 || fromCol >= 8 ||
                toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            System.out.println("Coordinates out of the board! (Board)");
            return false;
        }

        Basic piece = fields[fromRow][fromCol];

        // check if there's a piece to pick and move
        if (piece == null) {
            System.out.println("There's no piece in this field. (Board)");
            return false;
        }

        // make sure the piece belongs to the current player
        if (piece.getColor() != currPlayerColor) {
            System.out.println("Oh come on, It's not even your piece! (Board)");
            return false;
        }

        // check if target field is not occupied by piece of the same color
        if (fields[toRow][toCol] != null && fields[toRow][toCol].getColor() == currPlayerColor) {
            System.out.println("There is your piece already! (Board)");
            return false;
        }

        // check stuff specific for particular piece
        if (!piece.isMoveValid(toRow, toCol, this)) {
            System.out.println("Move invalid for a particular piece. (Board)");
            return false;
        }
        // Ensure the move does not result in the current player's king being in check
//        Board tempBoard = getCopy();
//        tempBoard.makeMove(fromRow, fromCol, toRow, toCol);
//        if (tempBoard.isKingInCheck(currentPlayerColor)) {
//            return false;
//        }

        return true;
    }

    public void removePiece(int row, int col) {
        fields[row][col] = null;
    }

    public Basic getPiece(int row, int column) {
        if (row >= 0 && row < 8 && column >= 0 && column < 8) {
            return fields[row][column];
        } else {
            return null;
        }
    }

    public Pawn getLastMovedPawn() {
        return lastMovedPawn;
    }

    public void setPiece(int row, int col, Basic piece) {
        fields[row][col] = piece;
    }
}
