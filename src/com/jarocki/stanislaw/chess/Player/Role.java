package com.jarocki.stanislaw.chess.Player;

import com.jarocki.stanislaw.chess.Coordinates.Row;

public enum Role {
    STANCHESS,
    OPPONENT;

    public String formatName() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }
}