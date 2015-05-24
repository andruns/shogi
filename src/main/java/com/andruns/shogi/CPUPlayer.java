package com.andruns.shogi;

import java.util.ArrayList;

/**
 * Created by asanu0829 on 5/7/15.
 */
public class CPUPlayer extends Player {
  public CPUPlayer(String name) {
    super(name);
  }

  public Move generateNextMove(Position position) {
    EvaluateFunction ef = new KomadokuEvaluate();
    Search search = new Search();
    int value = search.searchNegaMax(position, ef, 2, true);
    System.out.println("this value is: " + value);
    return search.getBestMove();
  }
}
