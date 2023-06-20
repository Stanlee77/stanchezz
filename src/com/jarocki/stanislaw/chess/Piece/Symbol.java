package com.jarocki.stanislaw.chess.Piece;

public enum Symbol {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    PAWN("P");

//    PAWN("♟");
//    QUEEN("♛"),
//    ROOK("♜"),
//    BISHOP("♝"),
//    KNIGHT("♞"),

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getChar() {
        return symbol;
    }
}
