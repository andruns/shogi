package com.andruns.shogi;

import com.andruns.shogi.Search.Result;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by asanu0829 on 5/23/15.
 */
public class TestSearch extends TestCase {
  @Test
  public void testMiniMaxSearch() throws Exception {
    Board board = new Board(new short[][]{
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, -2, -2, 0, 0, 0, 0, 2, 2, 2, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99}
    });
    int[] whitePiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());
    EvaluateFunction ef = new EvaluateTestFunction();
    Search search = new Search();

    Result res = search.searchMinMax(position, ef, 0);
    assertEquals(2, res.getValue());

    res = search.searchMinMax(position, ef, 1);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 1, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 3);
    assertEquals(13, res.getValue());

    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    res = search.searchMinMax(position, ef, 0);
    assertEquals(2, res.getValue());

    res = search.searchMinMax(position, ef, 1);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 1, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 3);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));
  }

  @Test
  public void testAlphaBetaSearch() throws Exception {
    Board board = new Board(new short[][]{
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, -2, -2, 0, 0, 0, 0, 2, 2, 2, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99}
    });
    int[] whitePiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());
    EvaluateFunction ef = new EvaluateTestFunction();
    Search search = new Search();

    Result res = search.searchAlphaBeta(position, ef, 0);
    assertEquals(2, res.getValue());

    res = search.searchAlphaBeta(position, ef, 1);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 1, 2), res.getBestMoves().get(0));

    res = search.searchAlphaBeta(position, ef, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    res = search.searchAlphaBeta(position, ef, 3);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    res = search.searchAlphaBeta(position, ef, 0);
    assertEquals(2, res.getValue());

    res = search.searchAlphaBeta(position, ef, 1);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 1, 2), res.getBestMoves().get(0));

    res = search.searchAlphaBeta(position, ef, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));

    res = search.searchAlphaBeta(position, ef, 3);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));
  }

  @Test
  public void testCompareSearch() throws Exception {
    Board board = new Board(new short[][]{
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 1, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 4, 0, 4, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, -2, -2, 0, -4, 0, 0, 2, 2, 2, 99},
        {99, 0, 0, 7, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, -3, 0, 0, 5, -5, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 6, 0, 0, 0, 0, 0, 0, 99},
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99}
    });
    int[] whitePiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());
    EvaluateFunction ef = new EvaluateTestFunction();
    Search minMax = new Search();
    Search alphaBeta = new Search();
    Result resMinMax;
    Result resAlphaBeta;
    long start, stop;

    start = System.currentTimeMillis();
    resMinMax = minMax.searchMinMax(position, ef, 2);
    stop = System.currentTimeMillis();
    System.out.println("minmax elapsed time: " + (stop - start));
    System.out.println(resMinMax.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resAlphaBeta = alphaBeta.searchAlphaBeta(position, ef, 2);
    stop = System.currentTimeMillis();
    System.out.println("alphabeta elapsed time: " + (stop - start));
    System.out.println(resAlphaBeta.getBestMoves().get(0).toString());

    assertTrue(resMinMax.getBestMoves().equals(resAlphaBeta.getBestMoves()));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    start = System.currentTimeMillis();
    resMinMax = minMax.searchMinMax(position, ef, 2);
    stop = System.currentTimeMillis();
    System.out.println("minmax elapsed time: " + (stop - start));
    System.out.println(resMinMax.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resAlphaBeta = alphaBeta.searchAlphaBeta(position, ef, 2);
    stop = System.currentTimeMillis();
    System.out.println("alphabeta elapsed time: " + (stop - start));
    System.out.println(resAlphaBeta.getBestMoves().get(0).toString());

    assertTrue(resMinMax.getBestMoves().equals(resAlphaBeta.getBestMoves()));
  }

  @Test
  public void testEasyMate() throws Exception {
    Board board = new Board(new short[][]{
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99,-14,0, 4, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99}
    });
    int[] whitePiecesInHand = {0, 0, 0, 0, 0, 1, 0, 0};
    int[] blackPiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());
    EvaluateFunction ef = new EvaluateTestFunction();
    Search search = new Search();
    Result res = search.searchAlphaBeta(position, ef, 2);
    assertEquals(MoveUtils.stringToMove("0552", position), res.getBestMoves().get(0));
  }

  @Test
  public void testLastPosition() throws Exception {
    Board board = new Board(new short[][]{
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99},
        {99,    0,  -2,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,    0,  -6,   0,  0,  0,  0,  0,  0,   3, 99},
        {99,    0,  13,  -1,  0,  0,  0,  1,  0,   4, 99},
        {99,    0,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,    0, -14,  -1,  0,  0,  0,  1,  0,  14, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,   -4,   0,  -1,  0,  0,  1,  0,  0,   4, 99},
        {99,   -3,  -7,  -1,  0,  0,  0,  1,  6,   3, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99}
    });
    int[] whitePiecesInHand = {0, 1, 0, 1, 1, 1, 0, 0};
    int[] blackPiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());
    EvaluateFunction ef = new EvaluateTestFunction();
    Search search = new Search();
    Result res = search.searchAlphaBeta(position, ef, 3);
    System.out.println("this value is: " + res.getValue());
    System.out.println("Best moves:");
    for(Move move: res.getBestMoves()) {
      if (move == null) break;
      System.out.println(move.toString());
    }
  }
}