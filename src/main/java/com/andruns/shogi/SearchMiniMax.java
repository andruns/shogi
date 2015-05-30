package com.andruns.shogi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asanu0829 on 5/30/15.
 */
public class SearchMiniMax extends Search {
  public Result search(Position position, EvaluateFunction ef, int depth) {
    return search(position, ef, depth, depth);
  }

  private Result search(Position position, EvaluateFunction ef, int depth, int maxDepth) {
    if (depth == 0)
      return new Result(ef.eval(position), new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
    ArrayList<Move> moves = position.getMoves();
    Position nextPosition;
    Result res;
    Result bestRes = null;
    int max = -INFINITY;
    int min = INFINITY;
    if (moves.size() == 0) {
      int mate_value = position.getTurn() == Constant.Turn.WHITE ? -MATE : MATE;
      bestRes = new Result(mate_value, new ArrayList<Move>(Arrays.asList(new Move[maxDepth])));
      return bestRes;
    }
    if (position.getTurn() == Constant.Turn.WHITE) {
      for (Move move : moves) {
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        res = search(nextPosition, ef, depth - 1, maxDepth);
        if (res.getValue() > max) {
          max = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
      }
    } else {
      for (Move move : moves) {
        nextPosition = position.clone();
        nextPosition.moveNextBoard(move);
        res = search(nextPosition, ef, depth - 1, maxDepth);
        if (res.getValue() < min) {
          min = res.getValue();
          res.setBestMoves(maxDepth - depth, move);
          bestRes = res;
        }
      }
    }
    return bestRes;
  }
}
