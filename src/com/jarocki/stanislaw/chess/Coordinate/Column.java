package com.jarocki.stanislaw.chess.Coordinate;

public enum Column {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public int getNum() {
        return this.ordinal();
    }

    public static Column getColByNum(String number) {
        switch (number) {
            case "1":
                return Column.A;
            case "2":
                return Column.B;
            case "3":
                return Column.C;
            case "4":
                return Column.D;
            case "5":
                return Column.E;
            case "6":
                return Column.F;
            case "7":
                return Column.G;
            case "8":
                return Column.H;
            default:
                throw new IllegalArgumentException("Invalid column number: " + number);
        }
    }
}
