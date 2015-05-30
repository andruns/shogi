package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asanu0829 on 5/23/15.
 */
public class Search {
  private final int INFINITY = 999999;
  private final int MATE = 99999;

  public Search() {
  }

  private Result searchMinMax(Position position, EvaluateFunction ef, int depth, int maxDepth) {
    if (depth == 0)
      return new Result(ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
    ArrayList<Move> moves = position.getMoves();
    Position nextPosition;
    Result res;
    Result bestRes = null;
    int max = -INFINITY;
    int min = INFINITY;
    if (moves.size() == 0) {
      int mate_value = position.getTurn() == Turn.WHITE ? -MATE : MATE;
      bestRes = new Result(mate_value, new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
      return bestRes;
    }
    if (position.getTurn() == Turn.WHITE) {
      for (Move move : moves) {
//        position.moveNextBoard(move);
//        res = searchMinMax(position, ef, depth - 1, maxDepth);
//        position.moveBackBoard(move);
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        res = searchMinMax(nextPosition, ef, depth - 1, maxDepth);
        if (res.getValue() > max) {
          max = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
      }
    } else {
      for (Move move : moves) {
//        position.moveNextBoard(move);
//        res = searchMinMax(position, ef, depth - 1, maxDepth);
//        position.moveBackBoard(move);
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        res = searchMinMax(nextPosition, ef, depth - 1, maxDepth);
        if (res.getValue() < min) {
          min = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
      }
    }
    return bestRes;
  }

  public Result searchMinMax(Position position, EvaluateFunction ef, int depth) {
    return searchMinMax(position, ef, depth, depth);
  }

  private Result searchAlphaBeta(Position position, EvaluateFunction ef, int alpha, int beta, int depth, int maxDepth) {
    if (depth == 0)
      return new Result(ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
    ArrayList<Move> moves = position.getMoves();
    Position nextPosition;
    Result res;
    Result bestRes = null;
    if(moves.size() == 0) {
      int mate_value = position.getTurn() == Turn.WHITE ? -MATE : MATE;
      bestRes = new Result(mate_value, new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
      return bestRes;
    }
    if (position.getTurn() == Turn.WHITE) {
      for (Move move : moves) {
//        position.moveNextBoard(move);
//        res = searchAlphaBeta(position, ef, alpha, beta, depth - 1, maxDepth);
//        position.moveBackBoard(move);
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        res = searchAlphaBeta(nextPosition, ef, alpha, beta, depth - 1, maxDepth);
        if(res.getValue() > alpha) {
          alpha = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
        if (alpha >= beta) {
          return bestRes;
        }
      }
      if (bestRes == null) {
        bestRes = new Result(alpha, new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
      }
    } else {
      for (Move move : moves) {
//        position.moveNextBoard(move);
//        res = searchAlphaBeta(position, ef, alpha, beta, depth - 1, maxDepth);
//        position.moveBackBoard(move);
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        res = searchAlphaBeta(nextPosition, ef, alpha, beta, depth - 1, maxDepth);
        if(res.getValue() < beta) {
          beta = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
        if (alpha >= beta) {
          return bestRes;
        }
      }
      if (bestRes == null) {
        bestRes = new Result(beta, new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
      }
    }
    return bestRes;
  }

  public Result searchAlphaBeta(Position position, EvaluateFunction ef, int depth) {
    return searchAlphaBeta(position, ef, -INFINITY, INFINITY, depth, depth);
  }

  public class Result {
    private int value;
    private ArrayList<Move> bestMoves;

    Result(int value, ArrayList<Move> bestMoves) {
      this.value = value;
      this.bestMoves = bestMoves;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public void setBestMoves(int index, Move move) {
      this.bestMoves.set(index, move);
    }

    public ArrayList<Move> getBestMoves() {
      return bestMoves;
    }
  }
}
