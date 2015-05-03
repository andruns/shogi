package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
abstract class Player {
  private String name;

  protected Player(String name) {
    this.name = name;
  }

  abstract Move generateNextMove(Position position);

  @Override
  public String toString() {
    return name;
  }
}