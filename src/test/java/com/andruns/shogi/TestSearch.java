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

    Result res = search.searchMinMax(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchMinMax(position, ef, 1, 1);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 1, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 3, 3);
    assertEquals(13, res.getValue());

    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    res = search.searchMinMax(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchMinMax(position, ef, 1, 1);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 1, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));

    res = search.searchMinMax(position, ef, 3, 3);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));
  }

  @Test
  public void testNegaMaxSearch() throws Exception {
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

    Result res = search.searchNegaMax(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchNegaMax(position, ef, 1, 1);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 1, 2), res.getBestMoves().get(0));

    res = search.searchNegaMax(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    res = search.searchNegaMax(position, ef, 3, 3);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    res = search.searchNegaMax(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchNegaMax(position, ef, 1, 1);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 1, 2), res.getBestMoves().get(0));

    res = search.searchNegaMax(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));

    res = search.searchNegaMax(position, ef, 3, 3);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));
  }

  @Test
  public void testAlfaBetaSearch() throws Exception {
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

    Result res = search.searchAlfaBeta(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchAlfaBeta(position, ef, 1, 1);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 1, 2), res.getBestMoves().get(0));

    res = search.searchAlfaBeta(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    res = search.searchAlfaBeta(position, ef, 3, 3);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    res = search.searchAlfaBeta(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchAlfaBeta(position, ef, 1, 1);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 1, 2), res.getBestMoves().get(0));

    res = search.searchAlfaBeta(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));

    res = search.searchAlfaBeta(position, ef, 3, 3);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));
  }

  @Test
  public void testNegaAlfaSearch() throws Exception {
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

    Result res = search.searchNegaAlfa(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchNegaAlfa(position, ef, 1, 1);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 1, 2), res.getBestMoves().get(0));

    res = search.searchNegaAlfa(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    res = search.searchNegaAlfa(position, ef, 3, 3);
    assertEquals(13, res.getValue());
    assertEquals(new Move(5, 7, 5, 2, 0, 2), res.getBestMoves().get(0));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    res = search.searchNegaAlfa(position, ef, 0, 0);
    assertEquals(2, res.getValue());

    res = search.searchNegaAlfa(position, ef, 1, 1);
    assertEquals(-9, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 1, 2), res.getBestMoves().get(0));

    res = search.searchNegaAlfa(position, ef, 2, 2);
    assertEquals(2, res.getValue());
    assertEquals(new Move(5, 2, 5, 7, 0, 2), res.getBestMoves().get(0));

    res = search.searchNegaAlfa(position, ef, 3, 3);
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
    Search negaMax = new Search();
    Search alfaBeta = new Search();
    Search negaAlfa = new Search();
    Result resMinMax;
    Result resNegaMax;
    Result resAlfaBeta;
    Result resNegaAlfa;
    long start, stop;

    start = System.currentTimeMillis();
    resMinMax = minMax.searchMinMax(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("minmax elapsed time: " + (stop - start));
    System.out.println(resMinMax.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resNegaMax = negaMax.searchNegaMax(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("negamax elapsed time: " + (stop - start));
    System.out.println(resNegaMax.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resAlfaBeta = alfaBeta.searchAlfaBeta(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("alfabeta elapsed time: " + (stop - start));
    System.out.println(resAlfaBeta.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resNegaAlfa = negaAlfa.searchNegaAlfa(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("negaalfa elapsed time: " + (stop - start));
    System.out.println(resNegaAlfa.getBestMoves().get(0).toString());

    assertTrue(resMinMax.getBestMoves().equals(resNegaMax.getBestMoves()));
    assertTrue(resNegaMax.getBestMoves().equals(resAlfaBeta.getBestMoves()));
    assertTrue(resAlfaBeta.getBestMoves().equals(resNegaAlfa.getBestMoves()));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    start = System.currentTimeMillis();
    resMinMax = minMax.searchMinMax(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("minmax elapsed time: " + (stop - start));
    System.out.println(resMinMax.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resNegaMax = negaMax.searchNegaMax(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("negamax elapsed time: " + (stop - start));
    System.out.println(resNegaMax.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resAlfaBeta = alfaBeta.searchAlfaBeta(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("alfabeta elapsed time: " + (stop - start));
    System.out.println(resAlfaBeta.getBestMoves().get(0).toString());

    start = System.currentTimeMillis();
    resNegaAlfa = negaAlfa.searchNegaAlfa(position, ef, 2, 2);
    stop = System.currentTimeMillis();
    System.out.println("negaalfa elapsed time: " + (stop - start));
    System.out.println(resNegaAlfa.getBestMoves().get(0).toString());

    assertTrue(resMinMax.getBestMoves().equals(resNegaMax.getBestMoves()));
    assertTrue(resNegaMax.getBestMoves().equals(resAlfaBeta.getBestMoves()));
    assertTrue(resAlfaBeta.getBestMoves().equals(resNegaAlfa.getBestMoves()));
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
    Result res = search.searchNegaAlfa(position, ef, 2, 2);
    assertEquals(MoveUtils.stringToMove("0552", position), res.getBestMoves().get(0));
  }
}