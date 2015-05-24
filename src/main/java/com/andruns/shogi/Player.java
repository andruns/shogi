package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
public abstract class Player {
  private String name;

  public Player(String name) {
    this.name = name;
  }

  public abstract Move generateNextMove(Position position);

  @Override
  public String toString() {
    return name;
  }
}