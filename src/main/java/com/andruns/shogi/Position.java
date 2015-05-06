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

    // TODO: start from suspended position
    Position(Board b) {
        this(Turn.WHITE, b, new int[8], new int[8]);
    }

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

    public ArrayList<Move> getMoves() {
        ArrayList<Move> moves = getMovesPreCheckFilter();
        filterCheck(moves);
        return moves;
    }

    public ArrayList<Move> getMovesPreCheckFilter() {
        ArrayList<Move> moves = new ArrayList<Move>();
        int pieceID;

        // from board
        for(int dan = 1; dan <=9; dan++) {
            for(int suji = 1; suji <= 9; suji++) {
                if((turn == Turn.WHITE && board.getCell(suji, dan) > 0) ||
                    (turn == Turn.BLACK && board.getCell(suji, dan) < 0)) {
                    pieceID = Math.abs(board.getCell(suji, dan));
                    for(int[] movement: MoveUtils.getMovesPerPieceOnBoard(this, pieceID, suji, dan)) {
                        moves.add(new Move(suji, dan, movement[0], movement[1], movement[2]));
                    }
                }
            }
        }

        // from pieces in hand
        if (turn == Turn.WHITE) {
            for (int i = 1; i <= 7; i++) {
                if (piecesWhiteInHand[i] > 0) {
                    for (int dan = 1; dan <= 9; dan++) {
                        for (int suji = 1; suji <= 9; suji++) {
                            if (board.getCell(suji, dan) == 0) {
                                if(PieceName.valueOf(i) != PieceName.KE || dan > 2) {
                                    if ((PieceName.valueOf(i) != PieceName.FU && PieceName.valueOf(i) != PieceName.KY)
                                        || dan != 1) {
                                        moves.add(new Move(0, i, suji, dan));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 1; i <= 7; i++) {
                if (piecesBlackInHand[i] > 0) {
                    for (int dan = 1; dan <= 9; dan++) {
                        for (int suji = 1; suji <= 9; suji++) {
                            if (board.getCell(suji, dan) == 0) {
                                if(PieceName.valueOf(i) != PieceName.KE || dan < 8) {
                                    if ((PieceName.valueOf(i) != PieceName.FU && PieceName.valueOf(i) != PieceName.KY)
                                        || dan != 9) {
                                        moves.add(new Move(0, i, suji, dan));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    boolean isValidMove(Move move) {
        ArrayList<Move> moves = getMoves();
        return moves.contains(move);
    }

    // TODO
    void filterCheck(ArrayList<Move> moves){
        Iterator<Move> iMoves = moves.iterator();
        Position position;
        while(iMoves.hasNext()) {
            Move move = iMoves.next();
            position = this.clone();
            position.moveTryNextBoard(move);
//            MoveUtils.moveTryNextBoard(position, move);
            for (int dan = 1; dan <= 9; dan++) {
                for (int suji = 1; suji <= 9; suji++) {
                    if((position.turn == Turn.BLACK && position.board.getCell(suji, dan) == PieceName.OU.getId()) ||
                        (position.turn == Turn.WHITE && position.board.getCell(suji, dan) == -PieceName.OU.getId())) {
                        for(Move m: position.getMovesPreCheckFilter()) {
                            if(m.getToSuji() == suji && m.getToDan() == dan) {
                                iMoves.remove();
                                break;
                            }
                        }
                    }
                }
            }
        }
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
        turn = turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
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