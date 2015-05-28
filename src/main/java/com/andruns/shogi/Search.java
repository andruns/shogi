package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;

import java.util.ArrayList;

/**
 * Created by asanu0829 on 5/23/15.
 */
public class Search {
  private Move bestMove;
  private ArrayList<Move> tryMoves = new ArrayList<Move>();
  private ArrayList<Move> bestMoves = new ArrayList<Move>();
  private final int INFINITY = 99999;

  public Search() {
  }

  Move getBestMove() {
    return bestMove;
  }

  public int searchMinMax(Position position, EvaluateFunction ef, int depth, boolean isRoot) {
    if (depth == 0) return ef.eval(position);
    ArrayList<Move> moves = position.getMoves();
    int value;
    int max = -INFINITY;
    int min = INFINITY;
    for(Move move: moves) {
      position.moveNextBoard(move);
      value = searchMinMax(position, ef, depth-1, false);
      position.moveBackBoard(move);
      if(position.getTurn() == Turn.WHITE) {
        if(value > max){
          max = value;
          if(isRoot) {
            bestMove = move;
          }
          tryMoves.set(depth, bestMove);
        }
      } else {
        if(value < min) {
          min = value;
          if(isRoot) {
            bestMove = move;
          }
        }
      }
    }
    return position.getTurn() == Turn.WHITE ? max : min;
  }

  public int searchNegaMax(Position position, EvaluateFunction ef, int depth, boolean isRoot) {
    if (depth == 0){
      return position.getTurn() == Turn.BLACK && isRoot != true ? -ef.eval(position) : ef.eval(position);
    }
    ArrayList<Move> moves = position.getMoves();
    int value;
    int max = -INFINITY;
    for(Move move: moves) {
      position.moveNextBoard(move);
      value = -searchNegaMax(position, ef, depth-1, false);
      position.moveBackBoard(move);
      if(value > max) {
        max = value;
        if(isRoot) {
          bestMove = move;
        }
      }
    }
    return isRoot == true && position.getTurn() == Turn.BLACK ? -max : max;
  }

  public int searchAlfaBeta(Position position, EvaluateFunction ef, int alfa, int beta, int depth, boolean isRoot) {
    if (depth == 0) return ef.eval(position);
    ArrayList<Move> moves = position.getMoves();
    int value;
    if (position.getTurn() == Turn.WHITE) {
      for (Move move : moves) {
        position.moveNextBoard(move);
        value = searchAlfaBeta(position, ef, alfa, beta, depth - 1, false);
        position.moveBackBoard(move);
        if(value > alfa) {
          alfa = value;
          if(isRoot) {
            bestMove = move;
          }
        }
        if (alfa > beta) {
          return beta;
        }
      }
      return alfa;
    } else {
      for (Move move : moves) {
        position.moveNextBoard(move);
        value = searchAlfaBeta(position, ef, alfa, beta, depth - 1, false);
        position.moveBackBoard(move);
        if(value < beta) {
          beta = value;
          if(isRoot) {
            bestMove = move;
          }
        }
        if (alfa > beta) {
          return alfa;
        }
      }
      return beta;
    }
  }

  public int searchAlfaBeta(Position position, EvaluateFunction ef, int depth, boolean isRoot) {
    return searchAlfaBeta(position, ef, -INFINITY, INFINITY, depth, true);
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

    public int getValue() {
      return value;
    }

    public ArrayList<Move> getBestMoves() {
      return bestMoves;
    }
  }
}
