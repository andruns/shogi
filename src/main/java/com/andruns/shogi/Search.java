package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;

import java.util.ArrayList;

/**
 * Created by asanu0829 on 5/23/15.
 */
public class Search {
  private Move bestMove;
  private ArrayList<Move> bestMoves = new ArrayList<Move>();
  private ArrayList<ArrayList<Move>> bestMovess = new ArrayList<ArrayList<Move>>();
  private final int INFINITY = 99999;

  public Search() {
  }

  Move getBestMove() {
    return bestMove;
  }

  public int searchMinMax(Position position, EvaluateFunction ef, int depth, boolean isRoot) {
    if (depth == 0) return ef.eval(position);
    ArrayList<Move> moves = position.getMoves();
    Position nextPosition;
    int value;
    int max = -INFINITY;
    int min = INFINITY;
    for(Move move: moves) {
      nextPosition = position.clone();
      nextPosition.moveNextBoard(move);
      value = searchMinMax(nextPosition, ef, depth-1, false);
      if(position.getTurn() == Turn.WHITE) {
        if(value > max){
          max = value;
          if(isRoot) {
            bestMove = move;
          }
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
    Position nextPosition;
    int value;
    int max = -INFINITY;
    for(Move move: moves) {
      nextPosition = position.clone();
      nextPosition.moveNextBoard(move);
      value = -searchNegaMax(nextPosition, ef, depth-1, false);
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
    Position nextPosition;
    int value;
    if (position.getTurn() == Turn.WHITE) {
      for (Move move : moves) {
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        value = searchAlfaBeta(nextPosition, ef, alfa, beta, depth - 1, false);
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
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        value = searchAlfaBeta(nextPosition, ef, alfa, beta, depth - 1, false);
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
    Position nextPosition;
    int value;
    for (Move move : moves) {
      nextPosition = position.clone();
      nextPosition.moveNextBoard(move);
      value = -searchNegaAlfa(nextPosition, ef, -beta, -alfa, depth - 1, false);
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
}
