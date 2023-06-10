package com.jarocki.stanislaw.chess;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Player.Player;
import com.jarocki.stanislaw.chess.Player.Role;

public class Main {
    public static void main(String[] args) {
        // setup
        Board chessBoard = new Board();
        Player me = new Player(Role.STANCHESS);
        Player opponent = new Player(Role.OPPONENT);

        chessBoard.setUpPieces();

        // initial state check
        chessBoard.printBoard();
        System.out.println();
        me.displayMoveInfo();

        // first move
        me.setLastMove("B2", "B6");
        me.convertMoveToCoordinates();

        chessBoard.movePiece(me.getLastMoveFromCol(), me.getLastMoveFromRow(), me.getLastMoveToCol(), me.getLastMoveToRow());

        chessBoard.printBoard();
        System.out.println();
        me.displayMoveInfo();
    }
}