package com.andruns.shogi;

import com.andruns.shogi.piece.Piece;

import java.util.Scanner;

/**
 * Created by asanu0829 on 5/2/15.
 */
public class Main {
  public static void main(String[] args) {
    Player player1 = new HumanPlayer();
    Player player2 = new HumanPlayer();
    Position position = new Position(player1, player2);

    Game game = new Game(player1, player2, position);
//    game.doGame();
  }
}
