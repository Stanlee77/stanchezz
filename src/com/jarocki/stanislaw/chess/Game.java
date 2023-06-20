package com.jarocki.stanislaw.chess;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Piece.Color;
import com.jarocki.stanislaw.chess.Player.Player;
import com.jarocki.stanislaw.chess.Player.Role;

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

//        String[] testMoves = Test.getOnlyPawnsOpening();
//        String[] testMoves = Test.getWrongPawnMoves();
//        String[] testMoves = Test.takeQueenOut();
//        String[] testMoves = Test.takeBishopOut();
//        String[] testMoves = Test.takeKnightsOut();
//        String[] testMoves = Test.takeRookOut();
//        String[] testMoves = Test.takeKingOut();
//        String[] testMoves = Test.getCastlingMoves();
        String[] testMoves = Test.getPawnPromotion();
//        String[] testMoves = Test.getOpeningMoves();


        while(getIsGameOn()) {
            board.printBoard();

            Color playerColor = this.getIsWhiteTurn() ? Color.WHITE : Color.BLACK;

            System.out.println("Enter " + playerColor.toString() + " next move: ");

            Test.delay(300);

            // tests
            String input;
            if(iteration >= testMoves.length) {
                input = scanner.nextLine();
            } else { // tests only
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

            Test.displayCurrentMove(currPlayer.getRole().formatName(), moves[0], moves[1]);

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
