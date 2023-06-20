package com.jarocki.stanislaw.chess;

public class Test {
    static public String[] getOnlyPawnsOpening() {
       return new String[]{
               "e2 e4", "d7 d5",
               "c2 c4", "e7 e5",
               "e4 d5", "e5 e4",
               "a2 a4", "a7 a6",
               "h2 h3", "h7 h5",
               "a4 a5", "c7 c5",
               "d5 c6", "b7 b5",
               "a5 b6",
       };
    }

    static public String[] getWrongPawnMoves() {
        return new String[]{
                "e2 e4", "d7 d5",
                "c2 c4", "e7 e5",
                "e4 d5", "f4 f5",
                "c4 c6", "g6 g5",
                "b2 b1", "d5 d4",
        };
    }

    static public String[] takeRookOut() {
        return new String[]{
                "h2 h4", "g7 g5",
                "h4 g5", "b7 b5",
                "h1 h7", "f7 f5",
                "h7 d7", "a8 a5", // wrong move over opponents and self pieces
                "h7 e7", "a7 a5",
                "e7 e5", "h8 h7",
                "e5 b5", "h7 h1",
                "b5 f5", "h1 g1",
        };
    }

    static public String[] takeBishopOut() {
        return new String[]{
                "e2 e4", "b7 b6",
                "f1 c4", "c8 b7",
                "c4 g8", "b7 a8", // wrong moves - over piece/onto own piece
                "c4 d4", "b7 b4", // wrong moves - horizontal i vertical
                "c4 f7", "b7 e4",
                "f7 g8", "e4 c2", // in future there's a check
        };
    }

    static public String[] getOpeningMoves() {
       return new String[]{
                "e2 e4", "e7 e5",
                "g1 f3", "b8 c6",
                "f1 c4", "g8 f6",
                "d2 d4", "e5 d4",
                "f3 d4", "f6 d5",
                "e4 d5", "e7 e6",
                "c1 g5", "d8 f6",
                "d1 f3", "e8 g8",
                "e1 g1", "f8 e7",
                "g1 h1", "d5 c6",
                "d4 c5", "c8 d7",
                "g5 h6", "f6 h6",
                "f3 g5", "d7 e6",
                "h1 h3", "g8 h8",
                "h3 h6", "f7 h6",
                "c4 f7", "e6 f7",
                "g5 f7", "e7 f7",
                "f1 f7", "c6 e5",
                "d1 d8", "h8 d8",
                "d4 e5", "d8 d1",
                "d1 d2", "d7 d2",
                "e5 d6", "d2 c2",
                "g2 g4", "c2 c1",
                "e2 e3", "c1 g5",
                "d6 f8", "e1 f1"
        };
   }

   static public void delay(int milliseconds) {
       try {
           Thread.sleep(milliseconds);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

    public static void displayCurrentMove(String playerColor, String moveFrom, String moveTo) {
        System.out.println("[DEBUG] Moving " + playerColor + " from " + moveFrom + " to " + moveTo);
    }
}
