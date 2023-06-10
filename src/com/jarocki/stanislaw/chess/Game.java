package com.jarocki.stanislaw.chess;

import com.jarocki.stanislaw.chess.Board.Board;

import java.util.Scanner;

public class Game {
    private Board board;

    private boolean isGameOn;

    public Game() {
        board = new Board();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        this.setIsGameOn(true);
        board = new Board();

        while(getIsGameOn()) {
            board.printBoard();

            System.out.println("Enter your next move (e.g. e2 e4): ");
            String input = scanner.nextLine();
            String[] move = input.split(" ");

            if (move.length != 2) {
                System.out.println("Invalid move format. Try again.");
                continue;
            }

            // Convert the move positions to row and column indices
            int fromRow = 8 - Integer.parseInt(move[0].charAt(1) + "");
            int fromCol = move[0].charAt(0) - 'a';
            int toRow = 8 - Integer.parseInt(move[1].charAt(1) + "");
            int toCol = move[1].charAt(0) - 'a';
        }
    }

    public void stopGame() {

    }

    private boolean getIsGameOn() {
        return isGameOn;
    }

    private void setIsGameOn(boolean isGameOn) {
        this.isGameOn = isGameOn;
    }
}
