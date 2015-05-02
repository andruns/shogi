package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Move {
  private final int move[];

  Move(int fromSuji, int fromDan, int toSuji, int toDan) {
    move = new int[]{fromSuji, fromDan, toSuji, toDan};
  }

  static Move stringToMove(String strMove) {
    // TODO: converting like "1234" -> Move(1, 2, 3, 4)
    Move move = new Move(
        Integer.parseInt(strMove.substring(0,1)),
        Integer.parseInt(strMove.substring(1,2)),
        Integer.parseInt(strMove.substring(2,3)),
        Integer.parseInt(strMove.substring(3,4))
    );
    return move;
  }

  public int getFromSuji() {
    return move[0];
  }

  public int getFromDan() {
    return move[1];
  }

  public int getToSuji() {
    return move[2];
  }

  public int getToDan() {
    return move[3];
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("Move: ");
    out.append(move[0]).append(", ");
    out.append(move[1]).append(", ");
    out.append(move[2]).append(", ");
    out.append(move[3]);
    return out.toString();
  }
}