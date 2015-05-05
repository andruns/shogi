package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Move {
  private final int move[];

  Move(int fromSuji, int fromDan, int toSuji, int toDan) {
    this(fromSuji, fromDan, toSuji, toDan, 0);
  }

  Move(int fromSuji, int fromDan, int toSuji, int toDan, int isPromoting) {
    move = new int[]{fromSuji, fromDan, toSuji, toDan, isPromoting};
  }

  static Move stringToMove(String strMove) {
    // "1234" -> Move(1, 2, 3, 4, 0)
    // "12340" -> Move(1, 2, 3, 4, 0)
    // "12341" -> Move(1, 2, 3, 4, 1)
    int isPromoting = 0;
    if(strMove.length() == 5 && Integer.parseInt(strMove.substring(4, 5)) == 1) {
      isPromoting = 1;
    }
    Move move = new Move(
        Integer.parseInt(strMove.substring(0,1)),
        Integer.parseInt(strMove.substring(1,2)),
        Integer.parseInt(strMove.substring(2,3)),
        Integer.parseInt(strMove.substring(3,4)),
        isPromoting
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

  public boolean isPromoting() {
    return move[4] == 1;
  }

  @Override public boolean equals(Object o){
    if (o == this)
      return true;
    if (!(o instanceof Move))
      return false;
    Move move = (Move) o;

    return (move.getFromSuji() == this.getFromSuji()
        && move.getFromDan() == this.getFromDan()
        && move.getToSuji() == this.getToSuji()
        && move.getToDan() == this.getToDan())
        && move.isPromoting() == this.isPromoting();
  }

  @Override public int hashCode(){
    int result = 17 + this.getFromSuji();
    result = 31 * result + this.getFromDan();
    result = 31 * result + this.getToSuji();
    result = 31 * result + this.getToDan();
    result = 31 * result + (this.isPromoting() ? 0 : 1);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("Move: ");
    out.append(move[0]).append(", ");
    out.append(move[1]).append(", ");
    out.append(move[2]).append(", ");
    out.append(move[3]).append(", ");
    out.append(move[4]);
    return out.toString();
  }
}