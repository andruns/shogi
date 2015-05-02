package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Position {
    private Board board;
//    private PiecesInHand pih;
    private Player white;
    private Player black;
    private Turn turn;

    Position(Player wht, Player blk) {
        this.board = new Board();
        this.white = wht;
        this.black = blk;
        this.turn = Turn.WHITE;
    }

    Position(Board b, Player wht, Player blk, Turn t) {
        this.board = b;
        this.white = wht;
        this.black = blk;
        this.turn = t;
    }

    Boolean moveNextBoard(Move move) {
        int fromSuji = move.getFromSuji();
        int fromDan = move.getFromDan();
        int toSuji = move.getToSuji();
        int toDan = move.getToDan();
//        if(!canMove(move)) {
//            return false;
//        }
        int from = board.getCell(fromSuji, fromDan);
        board.setCell(fromSuji, fromDan, 0);
        board.setCell(move.getToSuji(), move.getToDan(), from);
        return true;
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
