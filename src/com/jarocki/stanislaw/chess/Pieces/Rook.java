package com.jarocki.stanislaw.chess.Pieces;

public class Rook extends Slider {
    public Rook(Slider slider) {
        super(slider, slider.getIsMovingDiagonally(), slider.getIsMovingOrtagonally());
    }
}
