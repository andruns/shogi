package com.andruns.shogi;

/**
 * Created by asanu0829 on 5/7/15.
 */
public class CPUPlayer extends Player {
  public CPUPlayer(String name) {
    super(name);
  }

  public Move generateNextMove(Position position) {
    EvaluateFunction ef = new EvaluateTestFunction();
    Search search = new Search();
    Search.Result res = search.searchAlphaBeta(position, ef, 3);
    System.out.println("this value is: " + res.getValue());
    System.out.println("Best moves:");
    for(Move move: res.getBestMoves()) {
      System.out.println(move.toString());
    }
    System.out.println();
    return res.getBestMoves().get(0);
  }
}
