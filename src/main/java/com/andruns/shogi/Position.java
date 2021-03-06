package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Position implements Cloneable {
    private Turn turn;
    private Board board;
    int[] piecesWhiteInHand;
    int[] piecesBlackInHand;
    Move lastMove = null;

    Position() {
        this(Turn.WHITE, new Board(), new int[8], new int[8]);
    }

    Position(Turn t, Board b, int[] pW, int[] pB) {
        this.turn = t;
        this.board = b;
        this.piecesWhiteInHand = pW;
        this.piecesBlackInHand = pB;
    }

    public Boolean moveNextBoard(Move move) {
        if(!isValidMove(move)) {
            return false;
        }
        moveTryNextBoard(move);
        lastMove = move;
        return true;
    }

    public Boolean moveNextBoard(String strMove) {
        Move move = MoveUtils.stringToMove(strMove, this);
        return moveNextBoard(move);
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
            if(Math.abs(toPiece) == Piece.OU.getId()) {
                return;
            }
            int fromPiece = board.getCell(fromSuji, fromDan);
            if (promoting) {
                fromPiece = (int)Math.signum(fromPiece)
                    * Piece.valueOf(Math.abs(fromPiece)).getPromotedPieceID();
            }
            board.setCell(toSuji, toDan, fromPiece);
            board.setCell(fromSuji, fromDan, 0);
            if (toPiece != 0) {
                toPiece = Math.abs(toPiece);
                if(Piece.valueOf(toPiece).isPromoted()) {
                    toPiece = Piece.valueOf(toPiece).getDemotedPieceID();
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

    public Boolean moveBackBoard(Move move) {
        int fromSuji = move.getFromSuji();
        int fromDan = move.getFromDan();
        int toSuji = move.getToSuji();
        int toDan = move.getToDan();
        int toPieceID = move.getToPieceID();
        boolean promoting = move.isPromoting();
        if(fromSuji == 0) {
            if (turn == Turn.WHITE) {
                piecesBlackInHand[fromDan]++;
            } else {
                piecesWhiteInHand[fromDan]++;
            }
        } else {
            int newToPieceID = board.getCell(toSuji, toDan);
            if (promoting) {
                newToPieceID = (int)Math.signum(newToPieceID)
                    * Piece.valueOf(Math.abs(newToPieceID)).getDemotedPieceID();
            }
            board.setCell(fromSuji, fromDan, newToPieceID);
        }
        if (turn == Turn.WHITE) {
            board.setCell(toSuji, toDan, toPieceID);
        } else {
            board.setCell(toSuji, toDan, -toPieceID);
        }
        if(Piece.valueOf(toPieceID).isPromoted()) {
            toPieceID = Piece.valueOf(toPieceID).getDemotedPieceID();
        }
        if(toPieceID != 0) {
            if (turn == Turn.WHITE) {
                piecesBlackInHand[toPieceID]--;
            } else {
                piecesWhiteInHand[toPieceID]--;
            }
        }
        setTurn(turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE);
        return true;
    }

    public boolean isValidMove(Move move) {
        ArrayList<Move> moves = getMoves();
        return moves.contains(move);
    }

    public ArrayList<Move> getMoves() {
        ArrayList<Move> moves = MoveUtils.getMovesPreCheckFilter(this);
        MoveUtils.filterInvalidMove(this, moves);
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

    Move getLastMove() {
        return lastMove;
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
            out.append(Piece.valueOf(i)).append(":" + piecesBlackInHand[i] + " ");
        }
        out.append("\n");
        out.append(board.toString());
        out.append("SENTE MOCHIGOMA: ");
        for(int i = 1; i <=7; i++) {
            out.append(Piece.valueOf(i)).append(":" + piecesWhiteInHand[i] + " ");
        }
        out.append("\n");
        return out.toString();
    }
}