package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asanu0829 on 5/23/15.
 */
public class Search {
  private Move bestMove;
  private final int INFINITY = 99999;

  public Search() {
  }

  Move getBestMove() {
    return bestMove;
  }

  public Result searchMinMax(Position position, EvaluateFunction ef, int depth, int maxDepth) {
    if (depth == 0)
      return new Result(ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
    ArrayList<Move> moves = position.getMoves();
    Result res;
    Result bestRes = null;
    int max = -INFINITY;
    int min = INFINITY;
    for(Move move: moves) {
      position.moveNextBoard(move);
      res = searchMinMax(position, ef, depth - 1, maxDepth);
      position.moveBackBoard(move);
      if(position.getTurn() == Turn.WHITE) {
        if(res.getValue() > max){
          max = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
      } else {
        if(res.getValue() < min) {
          min = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
      }
    }
    return bestRes;
  }

  public Result searchNegaMax(Position position, EvaluateFunction ef, int depth, int maxDepth) {
    if (depth == 0){
      return position.getTurn() == Turn.BLACK && depth != maxDepth
          ? new Result(-ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])))
          : new Result(ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
    }
    ArrayList<Move> moves = position.getMoves();
    Result res;
    Result bestRes = null;
    int max = -INFINITY;
    for(Move move: moves) {
      position.moveNextBoard(move);
      res = searchNegaMax(position, ef, depth-1, maxDepth);
      res.setValue(-res.getValue());
      position.moveBackBoard(move);
      if(res.getValue() > max) {
        max = res.getValue();
        res.setBestMoves(maxDepth - depth, move);
        bestRes = res;
      }
    }
    if(depth == maxDepth && position.getTurn() == Turn.BLACK) bestRes.setValue(-bestRes.getValue());
    return bestRes;
  }

  public Result searchAlfaBeta(Position position, EvaluateFunction ef, int alfa, int beta, int depth, int maxDepth) {
    if (depth == 0)
      return new Result(ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
    ArrayList<Move> moves = position.getMoves();
    Result res;
    Result bestRes = null;
    if (position.getTurn() == Turn.WHITE) {
      for (Move move : moves) {
        position.moveNextBoard(move);
        res = searchAlfaBeta(position, ef, alfa, beta, depth - 1, maxDepth);
        position.moveBackBoard(move);
        if(res.getValue() > alfa) {
          alfa = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
        if (alfa > beta) {
          bestRes.setValue(beta);
          return bestRes;
        }
      }
    } else {
      for (Move move : moves) {
        position.moveNextBoard(move);
        res = searchAlfaBeta(position, ef, alfa, beta, depth - 1, maxDepth);
        position.moveBackBoard(move);
        if(res.getValue() < beta) {
          beta = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
        if (alfa > beta) {
          bestRes.setValue(alfa);
          return bestRes;
        }
      }
    }
    return bestRes;
  }

  public Result searchAlfaBeta(Position position, EvaluateFunction ef, int depth, int maxDepth) {
    return searchAlfaBeta(position, ef, -INFINITY, INFINITY, depth, maxDepth);
  }

  public int searchNegaAlfa(Position position, EvaluateFunction ef, int alfa, int beta, int depth, boolean isRoot) {
    if (depth == 0){
      return position.getTurn() == Turn.BLACK && isRoot != true ? -ef.eval(position) : ef.eval(position);
    }
    ArrayList<Move> moves = position.getMoves();
    int value;
    for (Move move : moves) {
      position.moveNextBoard(move);
      value = -searchNegaAlfa(position, ef, -beta, -alfa, depth - 1, false);
      position.moveBackBoard(move);
      if(value > alfa) {
        alfa = value;
        if(isRoot) {
          bestMove = move;
        }
      }
      if (alfa > beta) {
        return isRoot == true && position.getTurn() == Turn.BLACK ? -alfa : alfa;
      }
    }
    return isRoot == true && position.getTurn() == Turn.BLACK ? -alfa : alfa;
  }

  public int searchNegaAlfa(Position position, EvaluateFunction ef, int depth, boolean isRoot) {
    return searchNegaAlfa(position, ef, -INFINITY, INFINITY, depth, true);
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
