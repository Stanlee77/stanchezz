package com.jarocki.stanislaw.chess.Coordinate;

public enum Row {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT;

    public static Row getRowByNum(String number) {
        switch (number) {
            case "1":
                return Row.ONE;
            case "2":
                return Row.TWO;
            case "3":
                return Row.THREE;
            case "4":
                return Row.FOUR;
            case "5":
                return Row.FIVE;
            case "6":
                return Row.SIX;
            case "7":
                return Row.SEVEN;
            case "8":
                return Row.EIGHT;
            default:
                throw new IllegalArgumentException("Invalid row number: " + number);
        }
    }

    public int getNum() {
        return this.ordinal();
    }
}
