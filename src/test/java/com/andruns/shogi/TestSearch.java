package com.andruns.shogi;

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

    int value = search.searchMinMax(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchMinMax(position, ef, 1, true);
    System.out.println(value);
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 1, 2), search.getBestMove());

    value = search.searchMinMax(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    long start = System.currentTimeMillis();
    value = search.searchMinMax(position, ef, 3, true);
    long stop = System.currentTimeMillis();
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    value = search.searchMinMax(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchMinMax(position, ef, 1, true);
    System.out.println(value);
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 1, 2), search.getBestMove());

    value = search.searchMinMax(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());

    value = search.searchMinMax(position, ef, 3, true);
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());
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

    int value = search.searchNegaMax(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchNegaMax(position, ef, 1, true);
    System.out.println(value);
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 1, 2), search.getBestMove());

    value = search.searchNegaMax(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    long start = System.currentTimeMillis();
    value = search.searchNegaMax(position, ef, 3, true);
    long stop = System.currentTimeMillis();
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    value = search.searchNegaMax(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchNegaMax(position, ef, 1, true);
    System.out.println(value);
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 1, 2), search.getBestMove());

    value = search.searchNegaMax(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());

    value = search.searchNegaMax(position, ef, 3, true);
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());
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

    int value = search.searchAlfaBeta(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchAlfaBeta(position, ef, 1, true);
    System.out.println(value);
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 1, 2), search.getBestMove());

    value = search.searchAlfaBeta(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    long start = System.currentTimeMillis();
    value = search.searchAlfaBeta(position, ef, 3, true);
    long stop = System.currentTimeMillis();
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    value = search.searchAlfaBeta(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchAlfaBeta(position, ef, 1, true);
    System.out.println(value);
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 1, 2), search.getBestMove());

    value = search.searchAlfaBeta(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());

    value = search.searchAlfaBeta(position, ef, 3, true);
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());
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

    int value = search.searchNegaAlfa(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchNegaAlfa(position, ef, 1, true);
    System.out.println(value);
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 1, 2), search.getBestMove());

    value = search.searchNegaAlfa(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    long start = System.currentTimeMillis();
    value = search.searchNegaAlfa(position, ef, 3, true);
    long stop = System.currentTimeMillis();
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(13, value);
    assertEquals(new Move(5, 7, 5, 2, 0, 2), search.getBestMove());

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    value = search.searchNegaAlfa(position, ef, 0, true);
    System.out.println(value);
    assertEquals(2, value);

    value = search.searchNegaAlfa(position, ef, 1, true);
    System.out.println(value);
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 1, 2), search.getBestMove());

    value = search.searchNegaAlfa(position, ef, 2, true);
    System.out.println(value);
    assertEquals(2, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());

    value = search.searchNegaAlfa(position, ef, 3, true);
    System.out.println(value);
    System.out.println("elapsed time: " + (stop - start));
    assertEquals(-9, value);
    assertEquals(new Move(5, 2, 5, 7, 0, 2), search.getBestMove());
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
    long start, stop;

    start = System.currentTimeMillis();
    minMax.searchMinMax(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("minmax elapsed time: " + (stop - start));
    System.out.println(minMax.getBestMove().toString());

    start = System.currentTimeMillis();
    negaMax.searchNegaMax(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("negamax elapsed time: " + (stop - start));
    System.out.println(negaMax.getBestMove().toString());

    start = System.currentTimeMillis();
    alfaBeta.searchAlfaBeta(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("alfabeta elapsed time: " + (stop - start));
    System.out.println(alfaBeta.getBestMove().toString());

    start = System.currentTimeMillis();
    negaAlfa.searchNegaAlfa(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("negaalfa elapsed time: " + (stop - start));
    System.out.println(negaAlfa.getBestMove().toString());

    assertTrue(minMax.getBestMove().equals(negaMax.getBestMove()));
    assertTrue(negaMax.getBestMove().equals(alfaBeta.getBestMove()));
    assertTrue(alfaBeta.getBestMove().equals(negaAlfa.getBestMove()));

    position = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());

    start = System.currentTimeMillis();
    minMax.searchMinMax(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("minmax elapsed time: " + (stop - start));
    System.out.println(minMax.getBestMove().toString());

    start = System.currentTimeMillis();
    negaMax.searchNegaMax(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("negamax elapsed time: " + (stop - start));
    System.out.println(negaMax.getBestMove().toString());

    start = System.currentTimeMillis();
    alfaBeta.searchAlfaBeta(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("alfabeta elapsed time: " + (stop - start));
    System.out.println(alfaBeta.getBestMove().toString());

    start = System.currentTimeMillis();
    negaAlfa.searchNegaAlfa(position, ef, 2, true);
    stop = System.currentTimeMillis();
    System.out.println("negaalfa elapsed time: " + (stop - start));
    System.out.println(negaAlfa.getBestMove().toString());

    assertTrue(minMax.getBestMove().equals(negaMax.getBestMove()));
    assertTrue(negaMax.getBestMove().equals(alfaBeta.getBestMove()));
    assertTrue(alfaBeta.getBestMove().equals(negaAlfa.getBestMove()));
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
    search.searchNegaAlfa(position, ef, 2, true);
    assertEquals(MoveUtils.stringToMove("0552", position), search.getBestMove());
  }

  @Test
  public void testUchiFuMate() throws Exception {
    Board board = new Board(new short[][]{
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99,-2, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99,-14,0, 4, 0, 0, 0, 0, 0, 0, 99},
        {99,-2, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99},
        {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99}
    });
    int[] whitePiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    System.out.println(position.toString());
    EvaluateFunction ef = new EvaluateTestFunction();
    Search search = new Search();
    search.searchNegaAlfa(position, ef, 2, true);
    assertFalse(MoveUtils.stringToMove("0152", position) == search.getBestMove());
  }
}