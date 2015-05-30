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
    Search.Result res = search.searchNegaAlfa(position, ef, 2, 2);
    System.out.println("this value is: " + res.getValue());
    return search.getBestMove();
  }
}
