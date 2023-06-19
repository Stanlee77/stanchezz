package com.jarocki.stanislaw.chess.Player;

public enum Role {
    BLACK,
    WHITE;

    public String formatName() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }
}