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
    this(fromSuji, fromDan, toSuji, toDan, isPromoting, 0);
  }

  Move(int fromSuji, int fromDan, int toSuji, int toDan, int isPromoting, int toPieceID) {
    move = new int[]{fromSuji, fromDan, toSuji, toDan, isPromoting, toPieceID};
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

  public int getToPieceID() {
    return move[5];
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
        && move.isPromoting() == this.isPromoting()
        && move.getToPieceID() == this.getToPieceID();
  }

  @Override public int hashCode(){
    int result = 17 + this.getFromSuji();
    result = 31 * result + this.getFromDan();
    result = 31 * result + this.getToSuji();
    result = 31 * result + this.getToDan();
    result = 31 * result + (this.isPromoting() ? 0 : 1);
    result = 31 * result + this.getToPieceID();
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
    out.append(move[4]).append(", ");
    out.append(move[5]);
    return out.toString();
  }
}