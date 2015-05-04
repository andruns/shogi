package com.andruns.shogi;

import com.andruns.shogi.Constant.GameState;

/**
 * Created by asanu0829 on 3/15/15.
 */
class Game {
  private Player player1;
  private Player player2;
  private Position position;
  private GameState state;

  Game(Player p1, Player p2, Position p) {
    this.player1 = p1;
    this.player2 = p2;
    this.position = p;
    this.state = GameState.STARTING;
  }

  public void doGame() {
    while (true) {
      System.out.println(this.toString());
      if (!doTurn(player1, position)) {
        state = GameState.WIN_BLACK;
        break;
      }

      System.out.println(this.toString());
      if (!doTurn(player2, position)) {
        state = GameState.WIN_WHITE;
        break;
      }
    }
    System.out.println(this.toString());
  }

  private Boolean doTurn(Player player, Position position) {
    Move move;
    do {
      move = player.generateNextMove(position);
      System.out.println(move.toString());
      if (move.toString() == "0000") {
        return false;
      }
    } while (!position.moveNextBoard(move));
    return true;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("Status: ").append(state).append("\n");
    out.append("SENTE: ").append(player1.toString()).append("\n");
    out.append("GOTE:  ").append(player2.toString()).append("\n");
    out.append(position.toString());
    return out.toString();
  }
}