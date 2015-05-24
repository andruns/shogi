package com.andruns.shogi;

import java.util.ArrayList;

/**
 * Created by asanu0829 on 5/18/15.
 */
public class PNode {
  Position position;
  PNode parent;
  ArrayList<PNode> children;
  int value;

  public PNode(Position position) {
    this(position, null);
  }

  PNode(Position position, PNode parent) {
    this.position = position;
    this.parent = parent;
    this.children = new ArrayList<PNode>();
  }

  int getValue() {
    return value;
  }

  void generateChildren() {
    ArrayList<Move> moves = position.getMoves();
    Position cPosition;
    for(Move move: moves) {
      cPosition = position.clone();
      cPosition.moveNextBoard(move);
      children.add(new PNode(cPosition, this));
    }
  }

  void setLeafeValue(EvaluateFunction ef) {
    this.value = ef.eval(position);
  }

  void setBestChildValue() {
    PNode pn = getBestChild();
    this.value = pn.value;
  }

  PNode getBestChild() {
    if(position.getTurn() == Constant.Turn.WHITE) {
      return null; //max children
    } else {
      return null; //min children
    }
  }

  @Override
  public String toString() {
    return position.getLastMove().toString() + " value: " + value;
  }
}
