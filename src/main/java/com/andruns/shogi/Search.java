package com.andruns.shogi;

import java.util.ArrayList;

/**
 * Created by asanu0829 on 5/23/15.
 */
abstract class Search {
  final int INFINITY = 999999;
  final int MATE = 99999;

  abstract Result search(Position position, EvaluateFunction ef, int depth);

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
