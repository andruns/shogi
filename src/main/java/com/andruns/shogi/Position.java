package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;
import com.andruns.shogi.Constant.PieceName;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Position {
    private Turn turn;
    private Board board;
    private PiecesInHand pWhite;
    private PiecesInHand pBlack;

    Position() {
        this.turn = Turn.WHITE;
        this.board = new Board();
        this.pWhite = new PiecesInHand();
        this.pBlack = new PiecesInHand();
    }

    // TODO: start from suspended position
    Position(Board b, PiecesInHand pWhite, PiecesInHand pBlack, Turn t) {
    }

    Boolean moveNextBoard(Move move) {
        int fromSuji = move.getFromSuji();
        int fromDan = move.getFromDan();
        int toSuji = move.getToSuji();
        int toDan = move.getToDan();
        if(!isValidMove(move)) {
            return false;
        }
        int fromPiece = board.getCell(fromSuji, fromDan);
        board.setCell(fromSuji, fromDan, 0);
        board.setCell(toSuji, toDan, fromPiece);
        turn = turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE;
        return true;
    }

    public ArrayList<Move> getMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();
        int pieceID;
        for(int dan = 1; dan <=9; dan++) {
            for(int suji = 1; suji <= 9; suji++) {
                if((turn == Turn.WHITE && board.getCell(suji, dan) > 0) ||
                    (turn == Turn.BLACK && board.getCell(suji, dan) < 0)) {
                    pieceID = Math.abs(board.getCell(suji, dan));
                    PieceName piece = PieceName.valueOf(pieceID);
                    for(int[] movement: piece.getMovement(suji, dan, turn)) {
                        moves.add(new Move(suji, dan, movement[0], movement[1]));
                    }
                }
            }
        }

        filterExistMyPiece(moves);
        filterIsCheck(moves);

        return moves;
    }

    boolean isValidMove(Move move) {
        ArrayList<Move> moves = getMoves();
        return moves.contains(move);
    }

    void filterExistMyPiece(ArrayList<Move> moves){
        Iterator<Move> iMoves = moves.iterator();
        while(iMoves.hasNext()) {
            Move move = iMoves.next();
            if(
                (turn == Turn.WHITE && board.getCell(move.getToSuji(), move.getToDan()) > 0) ||
                (turn == Turn.BLACK && board.getCell(move.getToSuji(), move.getToDan()) < 0))
            {
                iMoves.remove();
            }
        }
    }

    // TODO
    void filterIsCheck(ArrayList<Move> moves){}

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("TEBAN: ").append(turn).append("\n");
        out.append(board.toString());
        out.append(getMoves().toString());
        return out.toString();
    }
}
