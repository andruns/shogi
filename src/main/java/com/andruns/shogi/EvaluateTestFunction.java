package com.andruns.shogi;

/**
 * Created by asanu0829 on 5/18/15.
 */
public class EvaluateTestFunction extends EvaluateFunction{

  int eval(Position position) {
    int value = 0;
    Board board = position.getBoard();
    for(int dan = 1; dan <= 9; dan++) {
      for (int suji = 1; suji <= 9; suji++) {
        value += board.getCell(suji, dan);
      }
    }
    int[] wpih = position.getWhitePiecesInHand();
    int[] bpih = position.getBlackPiecesInHand();
    for(int i = 1; i <=7; i++) {
      value += i * wpih[i];
      value -= i * bpih[i];
    }
    return value;
  }
}
