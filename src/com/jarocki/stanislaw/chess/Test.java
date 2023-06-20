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
                "h7 f7", "a7 a5",
                "f7 f5", "h8 h7",
                "f5 b5", "h7 h1",
                "b5 f5", "h1 g1",
                "f5 a5", "a8 a6",
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

    static public String[] takeKnightsOut() {
        return new String[]{
                "b1 a3", "g8 f6",
                "g1 g3", "f6 h7", // wrong moves - occupied own piece/illegal field
                "a3 c4", "f6 d5",
                "a3 c4", "f6 d5",
                "c4 e3", "d5 e3",
        };
    }

    static public String[] takeQueenOut() {
        return new String[]{
                "e2 e4", "e7 e6", // move pawns out
                "d1 f3", "d8 h4",   // move queens out
                "f3 b4", "h4 h1", // wrong move - random field/jump over piece <<<
                "f3 b3", "h4 f6", //  move queens other horizontally and diagonally
                "b3 b7", "f6 b2", // capture pawns with queens

        };
    }

    static public String[] takeKingOut() {
        return new String[]{
                "e2 e4", "b7 b6", // move pawns out
                "e1 e2", "f7 f5", // move one king out and pawn to it
                "e2 f3", "h7 h6", // move one king out and queen out
                "f3 f4", "b8 c6", // come with king closer to pawn and move knight for castling
                "f4 f5", "c8 b7", // take pawn by king and move bishop for castling
                "f5 f4", "e7 e5", // get back with king and attack with pawn
                "f4 e3", "d8 h4", // run away with king and get out queen for castling
                "g2 g3", "h4 g5", // attack queen and check the white king with queen
                "a2 a3", "a2 a3", // invalid move - makes CHECK, black invalid too for handicapp <<
                "f2 f4", "c6 e7", // valid move - hide behind the pawn and prepare knight to check king
                "f4 g5", "e7 d5", // kill black queen with pawn and attack white king with knight
                "d1 h5", // invalid move - try to check black king, but check from knight
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
