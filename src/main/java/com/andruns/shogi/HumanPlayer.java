package com.andruns.shogi;

import java.util.Scanner;

/**
 * Created by asanu0829 on 5/2/15.
 */
public class HumanPlayer extends Player {
  HumanPlayer(String name) {
    super(name);
  }

  public Move generateNextMove(Position position) {
    Scanner scanner = new Scanner(System.in);
    String output = scanner.next();
    return Move.stringToMove(output);
  }
}
