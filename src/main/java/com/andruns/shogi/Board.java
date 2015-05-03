package com.andruns.shogi;

import com.andruns.shogi.Constant.PieceName;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Board {
  private short board[][];

  Board(){
    board = new short[][]{
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   -3,  -6,  -1,  0,  0,  0,  1,  7,   3, 99},
        {99,   -4,   0,  -1,  0,  0,  0,  1,  0,   4, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,  -14,   0,  -1,  0,  0,  0,  1,  0,  14, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,   -4,   0,  -1,  0,  0,  0,  1,  0,   4, 99},
        {99,   -3,  -7,  -1,  0,  0,  0,  1,  6,   3, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99}
    };
  }

  public int getCell(int suji, int dan) {
    return board[suji][dan];
  }

  public void setCell(int suji, int dan, int piece) {
    board[suji][dan] = (short)piece;
  }

  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("==============================================\n");
    for(int dan = 1; dan <=9; dan++) {
      for(int suji = 9; suji >= 1; suji--) {
        out.append("|");
        if(Math.signum(board[suji][dan]) < 0) {
          out.append(String.format("%1$4s", "-" + PieceName.valueOf(Math.abs(board[suji][dan]))));
        } else if (Math.signum(board[suji][dan]) == 0) {
          out.append("    ");
        } else {
          out.append(String.format("%1$4s", PieceName.valueOf(board[suji][dan])));
        }
      }
      out.append("|\n");
    }
    out.append("==============================================\n");
    return out.toString();
  }
}
