package com.andruns.shogi;

/**
 * Created by asanu0829 on 5/2/15.
 */
public class Main {
  public static void main(String[] args) {
    Player player1 = new HumanPlayer("Player1");
    Player player2 = new HumanPlayer("Player2");
    Position position = new Position();

    Game game = new Game(player1, player2, position);
    game.doGame();
  }
}
