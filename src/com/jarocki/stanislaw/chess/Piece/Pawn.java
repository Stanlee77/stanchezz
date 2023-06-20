package com.jarocki.stanislaw.chess.Piece;

import com.jarocki.stanislaw.chess.Board.Board;
import com.jarocki.stanislaw.chess.Coordinate.Column;
import com.jarocki.stanislaw.chess.Coordinate.Row;

public class Pawn extends Basic {

    public Pawn(Color color, Row row, Column col) {
        super(color, row, col);
        this.setSymbol(Symbol.PAWN);
    }

    @Override
    public boolean isMoveValid(int targetRow, int targetColumn, Board board) {
        // check if inside board
        if (targetRow < 0 || targetRow >= 8 || targetColumn < 0 || targetColumn >= 8) {
            return false;
        }

        // destination tile empty/with opponent's pawn
        if (board.getPiece(targetRow, targetColumn) == null) {
            if (getColumn().getNum() == targetColumn) {
                // moving forward by one row depending on the color
                if (getColor() == Color.WHITE) {
                    if (targetRow - getRow().getNum() == 1) {
                        return true;
                    } else if (isStartingPosition(getRow(), getColor()) && targetRow - getRow().getNum() == 2) {
                        // check if can move two tiles
                        if (board.getPiece(getRow().getNum() + 1, targetColumn) == null) {
                            return true;
                        }
                    }
                } else {
                    if (targetRow - getRow().getNum() == -1) {
                        return true;
                    } else if (isStartingPosition(getRow(), getColor()) && targetRow - getRow().getNum() == -2) {
                        // check if can move two tiles
                        if (board.getPiece(getRow().getNum() - 1, targetColumn) == null) {
                            return true;
                        }
                    }
                }
            }
            // if en passant
            return isValidCapture(targetRow, targetColumn, board);
        } else {
            // i know it's silly but works
            return isValidCapture(targetRow, targetColumn, board);
        }
    }

    private boolean isValidCapture(int targetRow, int targetColumn, Board board) {
        // inside board
        if (targetRow < 0 || targetRow >= 8 || targetColumn < 0 || targetColumn >= 8) {
            return false;
        }

        Basic targetPiece = board.getPiece(targetRow, targetColumn);
        if (targetPiece == null) {
            // en passant check
            if (targetColumn == getColumn().getNum() - 1 || targetColumn == getColumn().getNum() + 1) {
                // check target tile
                if (targetRow == getRow().getNum() + (getColor() == Color.WHITE ? 1 : -1)) {
                    Pawn lastMovedPawn = board.getLastMovedPawn();
                    if (lastMovedPawn != null && lastMovedPawn.getRow() == getRow() &&
                            lastMovedPawn.getColumn().getNum() == targetColumn) {
                        // correct en passant
                        return true;
                    }
                }
            }
            return false;
        }
        int col = getColumn().getNum();
        if (targetColumn == getColumn().getNum() - 1) {
            // diagonal to top left
            Color color = getColor();
            if (getColor() == Color.WHITE && targetRow == getRow().getNum() + 1) {
                return true;
            } else if (getColor() == Color.BLACK && targetRow == getRow().getNum() - 1) {
                return true;
            }
        } else if (targetColumn == getColumn().getNum() + 1) {
            // one tile to top right
            if (getColor() == Color.WHITE && targetRow == getRow().getNum() + 1) {
                return true;
            } else if (getColor() == Color.BLACK && targetRow == getRow().getNum() - 1) {
                return true;
            }
        }

        return false;
    }

    private boolean isStartingPosition(Row currentRow, Color color) {
        // check if the pawn is in its starting position (white row - 2, black row - 7)
        return (color == Color.WHITE && currentRow == Row.TWO)
                || (color == Color.BLACK && currentRow == Row.SEVEN);
    }
}
