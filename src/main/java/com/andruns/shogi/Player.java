package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
public abstract class Player {
  String name;
  EvaluateFunction ef;
  int depth;

  public Player(String name) {
    this.name = name;
  }

  public Player(String name, EvaluateFunction ef, int depth) {
    this.name = name;
    this.ef = ef;
    this.depth = depth;
  }

  public abstract Move generateNextMove(Position position);

  @Override
  public String toString() {
    return name;
  }
}