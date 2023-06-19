package com.jarocki.stanislaw.chess;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Row;
import com.jarocki.stanislaw.chess.Piece.Color;
import com.jarocki.stanislaw.chess.Player.Player;
import com.jarocki.stanislaw.chess.Player.Role;
import java.io.ByteArrayInputStream;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;

    private boolean isGameOn;

    private boolean isWhiteTurn = true;

    public Game() {
        board = new Board();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        this.setIsGameOn(true);
        Board board = new Board();
        Player currPlayer = new Player(Role.WHITE);

        board.setUpPieces();

        int iteration = 0; // tests only


        String[] testMoves = {
                "e2 e4", "d7 d5",
                "c2 c4", "e7 e5",
                "e4 d5",
//                "e4 d5", "e5 e4",
        };

//        String[] testMoves = {
//                "e2 e4", "e7 e5",
//                "g1 f3", "b8 c6",
//                "f1 c4", "g8 f6",
//                "d2 d4", "e5 d4",
//                "f3 d4", "f6 d5",
//                "e4 d5", "e7 e6",
//                "c1 g5", "d8 f6",
//                "d1 f3", "e8 g8",
//                "e1 g1", "f8 e7",
//                "g1 h1", "d5 c6",
//                "d4 c5", "c8 d7",
//                "g5 h6", "f6 h6",
//                "f3 g5", "d7 e6",
//                "h1 h3", "g8 h8",
//                "h3 h6", "f7 h6",
//                "c4 f7", "e6 f7",
//                "g5 f7", "e7 f7",
//                "f1 f7", "c6 e5",
//                "d1 d8", "h8 d8",
//                "d4 e5", "d8 d1",
//                "d1 d2", "d7 d2",
//                "e5 d6", "d2 c2",
//                "g2 g4", "c2 c1",
//                "e2 e3", "c1 g5",
//                "d6 f8", "e1 f1"
//        };


        while(getIsGameOn()) {
            board.printBoard();

            Color playerColor = this.getIsWhiteTurn() ? Color.WHITE : Color.BLACK;

//            System.out.println("Enter " + playerColor.toString() + " next move: ");

            // 0.5s delay for playing comp vs comp
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            String input = scanner.nextLine();
            // for tests comment upper line and uncomment the 3 below
            String input;
            if(iteration >= testMoves.length) { input = scanner.nextLine(); } else {
                input = testMoves[iteration];
                iteration = iteration + 1;
            }

            String[] moves = input.toUpperCase().split(" ");

            if (moves.length != 2) {
                System.out.println("Incorrect move format. Try \"e2 e4\". (Game)");
                continue;
            }

            currPlayer.setLastMove(moves[0], moves[1]);
            currPlayer.convertMoveToCoordinates();

            int fromRow = currPlayer.getLastMoveFromRow().getNum();
            int fromCol = currPlayer.getLastMoveFromCol().getNum();
            int toRow = currPlayer.getLastMoveToRow().getNum();
            int toCol = currPlayer.getLastMoveToCol().getNum();

            System.out.println("Moving " + currPlayer.getRole().formatName() + " from " + moves[0] + " to " + moves[1] + " [debug]"); // temp for debug

            if (!board.isMoveValid(fromRow, fromCol, toRow, toCol, playerColor)) {
                System.out.println("Moving " + currPlayer.getRole().formatName() + " from " + moves[0] + " to " + moves[1] + " is illegal. (Game)");
                this.switchTurn();
                currPlayer.switchRole();
                continue;
            }

            board.makeMove(fromRow, fromCol, toRow, toCol);
            currPlayer.displayMoveInfo();
            this.switchTurn();
            currPlayer.switchRole();
        }
        scanner.close();
    }

    public void stopGame() {
        this.setIsGameOn(false);
    }

    private boolean getIsGameOn() {
        return isGameOn;
    }

    private void setIsGameOn(boolean isGameOn) {
        this.isGameOn = isGameOn;
    }

    public boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }

    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
    }
}
