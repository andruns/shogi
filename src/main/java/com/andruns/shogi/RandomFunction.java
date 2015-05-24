package com.andruns.shogi;

/**
 * Created by asanu0829 on 5/18/15.
 */
public class RandomFunction extends EvaluateFunction {
  int eval(Position position) {
    return (int)(Math.random() * 10);
  }
}
