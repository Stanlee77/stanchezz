package com.jarocki.stanislaw.chess.Piece;

public enum Color {
    WHITE,
    BLACK;

    public String toString() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }
}
