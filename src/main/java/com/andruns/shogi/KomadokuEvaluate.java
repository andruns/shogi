package com.andruns.shogi;

/**
 * Created by asanu0829 on 5/18/15.
 */
public class KomadokuEvaluate extends EvaluateFunction{

  int eval(Position position) {
    int value = 0;
    Board board = position.getBoard();
    for(int dan = 1; dan <= 9; dan++) {
      for (int suji = 1; suji <= 9; suji++) {
        value += board.getCell(suji, dan);
      }
    }
    return value;
  }
}
