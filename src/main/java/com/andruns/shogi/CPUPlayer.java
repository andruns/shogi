package com.andruns.shogi;

/**
 * Created by asanu0829 on 5/7/15.
 */
public class CPUPlayer extends Player {
  public CPUPlayer(String name, EvaluateFunction ef, int depth) {
    super(name, ef, depth);
  }

  public Move generateNextMove(Position position) {
    Search ab = new SearchAlfaBeta();
    Search.Result res = ab.search(position, ef, depth);
    System.out.println("this value is: " + res.getValue());
    System.out.println("Best moves:");
    for(Move move: res.getBestMoves()) {
      if (move == null) break;
      System.out.println(move.toString());
    }
    System.out.println();
    return res.getBestMoves().get(0);
  }
}
