package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;
import com.andruns.shogi.Constant.PieceName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Position implements Cloneable {
    private Turn turn;
    private Board board;
    int[] piecesWhiteInHand;
    int[] piecesBlackInHand;

    Position() {
        this(Turn.WHITE, new Board(), new int[8], new int[8]);
    }

//    TODO: start from suspended position
//    Position(Board b) {
//        this(Turn.WHITE, b, new int[8], new int[8]);
//    }

    Position(Turn t, Board b, int[] pW, int[] pB) {
        this.turn = t;
        this.board = b;
        this.piecesWhiteInHand = pW;
        this.piecesBlackInHand = pB;
    }

    public Boolean moveNextBoard(Move move) {
        int fromSuji = move.getFromSuji();
        int fromDan = move.getFromDan();
        int toSuji = move.getToSuji();
        int toDan = move.getToDan();
        boolean promoting = move.isPromoting();
        if(!isValidMove(move)) {
            return false;
        }
        if(fromSuji == 0) {
            if (turn == Turn.WHITE) {
                piecesWhiteInHand[fromDan]--;
                board.setCell(toSuji, toDan, fromDan);
            } else {
                piecesBlackInHand[fromDan]--;
                board.setCell(toSuji, toDan, -fromDan);
            }
        } else {
            int toPiece = board.getCell(toSuji, toDan);
            if(Math.abs(toPiece) == PieceName.OU.getId()) {
                return false;
            }
            int fromPiece = board.getCell(fromSuji, fromDan);
            if (promoting) {
                fromPiece = (int)Math.signum(fromPiece)
                    * PieceName.valueOf(Math.abs(fromPiece)).getPromotedPieceID();
            }
            board.setCell(toSuji, toDan, fromPiece);
            board.setCell(fromSuji, fromDan, 0);
            if (toPiece != 0) {
                toPiece = Math.abs(toPiece);
                if(PieceName.valueOf(toPiece).isPromoted()) {
                    toPiece = PieceName.valueOf(toPiece).getDemotedPieceID();
                }
                if (turn == Turn.WHITE) {
                    piecesWhiteInHand[toPiece]++;
                } else {
                    piecesBlackInHand[toPiece]++;
                }
            }
        }
        turn = turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE;
        return true;
    }

    void moveTryNextBoard(Move move) {
        int fromSuji = move.getFromSuji();
        int fromDan = move.getFromDan();
        int toSuji = move.getToSuji();
        int toDan = move.getToDan();
        boolean promoting = move.isPromoting();
        if(fromSuji == 0) {
            if (turn == Turn.WHITE) {
                piecesWhiteInHand[fromDan]--;
                board.setCell(toSuji, toDan, fromDan);
            } else {
                piecesBlackInHand[fromDan]--;
                board.setCell(toSuji, toDan, -fromDan);
            }
        } else {
            int toPiece = board.getCell(toSuji, toDan);
            if(Math.abs(toPiece) == PieceName.OU.getId()) {
                return;
            }
            int fromPiece = board.getCell(fromSuji, fromDan);
            if (promoting) {
                fromPiece = (int)Math.signum(fromPiece)
                    * PieceName.valueOf(Math.abs(fromPiece)).getPromotedPieceID();
            }
            board.setCell(toSuji, toDan, fromPiece);
            board.setCell(fromSuji, fromDan, 0);
            if (toPiece != 0) {
                toPiece = Math.abs(toPiece);
                if(PieceName.valueOf(toPiece).isPromoted()) {
                    toPiece = PieceName.valueOf(toPiece).getDemotedPieceID();
                }
                if (turn == Turn.WHITE) {
                    piecesWhiteInHand[toPiece]++;
                } else {
                    piecesBlackInHand[toPiece]++;
                }
            }
        }
        setTurn(turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE);
    }

    public boolean isValidMove(Move move) {
        ArrayList<Move> moves = getMoves();
        return moves.contains(move);
    }

    public ArrayList<Move> getMoves() {
        ArrayList<Move> moves = MoveUtils.getMovesPreCheckFilter(this);
        MoveUtils.filterCheck(this, moves);
        return moves;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public int[] getWhitePiecesInHand() {
        return piecesWhiteInHand;
    }

    public int[] getBlackPiecesInHand() {
        return piecesBlackInHand;
    }

    @Override
    public Position clone() {
        Position result = null;
        try {
            result = (Position)super.clone();
            result.board = this.board.clone();
            result.piecesWhiteInHand = Arrays.copyOf(this.piecesWhiteInHand, piecesWhiteInHand.length);
            result.piecesBlackInHand = Arrays.copyOf(this.piecesBlackInHand, piecesBlackInHand.length);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Position))
            return false;
        Position p = (Position) o;

        return this.turn == p.turn
            && this.board.equals(p.board)
            && Arrays.equals(this.piecesWhiteInHand, p.piecesWhiteInHand)
            && Arrays.equals(this.piecesBlackInHand, p.piecesBlackInHand);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("TEBAN: ").append(turn).append("\n");
        out.append("GOTE MOCHIGOMA: ");
        for(int i = 1; i <=7; i++) {
            out.append(PieceName.valueOf(i)).append(":" + piecesBlackInHand[i] + " ");
        }
        out.append("\n");
        out.append(board.toString());
        out.append("SENTE MOCHIGOMA: ");
        for(int i = 1; i <=7; i++) {
            out.append(PieceName.valueOf(i)).append(":" + piecesWhiteInHand[i] + " ");
        }
        out.append("\n");
        out.append(getMoves().toString());
        return out.toString();
    }
}