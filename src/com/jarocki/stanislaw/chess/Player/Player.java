package com.jarocki.stanislaw.chess.Player;

import com.jarocki.stanislaw.chess.Coordinates.Column;
import com.jarocki.stanislaw.chess.Coordinates.Row;

import java.util.List;

public class Player {
    private final Role role;
    private String lastMoveFrom;
    private String lastMoveTo;
    private Column lastMoveFromCol;
    private Row lastMoveFromRow;
    private Column lastMoveToCol;
    private Row lastMoveToRow;

    public Player(Role role) {
        this.role = role;
    }

    public void setLastMove(String moveFrom, String moveTo) {
        if(moveFrom == null || moveTo == null) {
            System.out.println("Missing moves: " + moveFrom + " " + moveTo + " (setLastMove)");
        }
        setLastMoveFrom(moveFrom);
        setLastMoveTo(moveTo);
    }

    public void convertMoveToCoordinates() {
        String moveFrom = this.getLastMoveFrom();
        String moveTo = this.getLastMoveTo();

        if (moveFrom.length() != 2 || moveTo.length() != 2) {
            throw new RuntimeException("Incorrect move coordinates (convertMoveToCoordinates)");
        }

        Column moveFromCol = Column.valueOf(moveFrom.substring(0, 1));
        Row moveFromRow = Row.getRowByNumber(moveFrom.substring(1,2));
        Column moveToCol = Column.valueOf(moveTo.substring(0, 1));
        Row moveToRow = Row.getRowByNumber(moveTo.substring(1,2));

        setLastMoveFromCol(moveFromCol);
        setLastMoveFromRow(moveFromRow);
        setLastMoveToCol(moveToCol);
        setLastMoveToRow(moveToRow);
    }

    public String convertMoveToString() {
        Row rowFrom = this.getLastMoveFromRow();
        Row rowTo = this.getLastMoveToRow();
        Column colFrom = this.getLastMoveFromCol();
        Column colTo = this.getLastMoveToCol();

        return (
            colFrom.toString() +
            String.valueOf(rowFrom.getNumber()) +
            colTo.toString() +
            String.valueOf(rowTo.getNumber())
        );
    }

    public void displayMoveInfo() {
        String from = this.getLastMoveFrom();
        String to = this.getLastMoveTo();
        String playerName = this.getRole().formatName();

        if (from == null || to == null) {
            System.out.println("Player " + playerName + " hasn't moved yet");
            return;
        }

        System.out.println("Player " + playerName + " moved from " + from + " to " + to);
    }
    public Column getLastMoveFromCol() {
        return lastMoveFromCol;
    }

    public void setLastMoveFromCol(Column lastMoveFromCol) {
        this.lastMoveFromCol = lastMoveFromCol;
    }

    public Row getLastMoveFromRow() {
        return lastMoveFromRow;
    }

    public void setLastMoveFromRow(Row lastMoveFromRow) {
        this.lastMoveFromRow = lastMoveFromRow;
    }

    public Column getLastMoveToCol() {
        return lastMoveToCol;
    }

    public void setLastMoveToCol(Column lastMoveToCol) {
        this.lastMoveToCol = lastMoveToCol;
    }

    public Row getLastMoveToRow() {
        return lastMoveToRow;
    }

    public void setLastMoveToRow(Row lastMoveToRow) {
        this.lastMoveToRow = lastMoveToRow;
    }

    public Role getRole() {
        return role;
    }

    public String getLastMoveFrom() {
        return lastMoveFrom;
    }

    private void setLastMoveFrom(String lastMoveFrom) {
        this.lastMoveFrom = lastMoveFrom;
    }

    public String getLastMoveTo() {
        return lastMoveTo;
    }

    private void setLastMoveTo(String lastMoveTo) {
        this.lastMoveTo = lastMoveTo;
    }
}
