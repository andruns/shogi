package com.andruns.shogi;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class PositionTest extends TestCase {

  @Test
  public void testMoveNextBoard() throws Exception {
    Position position = new Position();
    position.moveNextBoard(new Move(7, 7, 7, 6, 0));
    position.moveNextBoard(new Move(3, 3, 3, 4, 0));
    position.moveNextBoard(new Move(8, 8, 2, 2, 1));

    Board board = new Board( new short[][]{
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   -3,  12,  -1,  0,  0,  0,  1,  7,   3, 99},
        {99,   -4,   0,   0, -1,  0,  0,  1,  0,   4, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,  -14,   0,  -1,  0,  0,  0,  1,  0,  14, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,   -4,   0,  -1,  0,  0,  1,  0,  0,   4, 99},
        {99,   -3,  -7,  -1,  0,  0,  0,  1,  0,   3, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99}
    });
    int[] whitePiecesInHand = {0, 0, 0, 0, 0, 0, 1, 0};
    int[] blackPiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    Position ePosition = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    assertEquals(ePosition, position);
  }

  @Test
  public void testGetMoves() throws Exception {
    Position position = new Position();
    ArrayList<Move> moves = position.getMoves();
    assertEquals(30, moves.size());

    assertTrue(moves.contains(new Move(1, 7, 1, 6, 0)));
    assertTrue(moves.contains(new Move(2, 7, 2, 6, 0)));
    assertTrue(moves.contains(new Move(3, 7, 3, 6, 0)));
    assertTrue(moves.contains(new Move(4, 7, 4, 6, 0)));
    assertTrue(moves.contains(new Move(5, 7, 5, 6, 0)));
    assertTrue(moves.contains(new Move(6, 7, 6, 6, 0)));
    assertTrue(moves.contains(new Move(7, 7, 7, 6, 0)));
    assertTrue(moves.contains(new Move(8, 7, 8, 6, 0)));
    assertTrue(moves.contains(new Move(9, 7, 9, 6, 0)));
    assertTrue(moves.contains(new Move(2, 8, 3, 8, 0)));
    assertTrue(moves.contains(new Move(2, 8, 4, 8, 0)));
    assertTrue(moves.contains(new Move(2, 8, 5, 8, 0)));
    assertTrue(moves.contains(new Move(2, 8, 6, 8, 0)));
    assertTrue(moves.contains(new Move(2, 8, 7, 8, 0)));
    assertTrue(moves.contains(new Move(2, 8, 1, 8, 0)));
    assertTrue(moves.contains(new Move(1, 9, 1, 8, 0)));
    assertTrue(moves.contains(new Move(3, 9, 4, 8, 0)));
    assertTrue(moves.contains(new Move(3, 9, 3, 8, 0)));
    assertTrue(moves.contains(new Move(4, 9, 5, 8, 0)));
    assertTrue(moves.contains(new Move(4, 9, 4, 8, 0)));
    assertTrue(moves.contains(new Move(4, 9, 3, 8, 0)));
    assertTrue(moves.contains(new Move(5, 9, 6, 8, 0)));
    assertTrue(moves.contains(new Move(5, 9, 5, 8, 0)));
    assertTrue(moves.contains(new Move(5, 9, 4, 8, 0)));
    assertTrue(moves.contains(new Move(6, 9, 7, 8, 0)));
    assertTrue(moves.contains(new Move(6, 9, 6, 8, 0)));
    assertTrue(moves.contains(new Move(6, 9, 5, 8, 0)));
    assertTrue(moves.contains(new Move(7, 9, 7, 8, 0)));
    assertTrue(moves.contains(new Move(7, 9, 6, 8, 0)));
    assertTrue(moves.contains(new Move(9, 9, 9, 8, 0)));
  }

  @Test
  public void testGetMovesPreCheckFilter() throws Exception {
    Position position = new Position();
    position.moveNextBoard(new Move(7, 7, 7, 6, 0));
    position.moveNextBoard(new Move(3, 3, 3, 4, 0));
    position.moveNextBoard(new Move(8, 8, 3, 3, 1));

    ArrayList<Move> moves = position.getMoves();
    assertEquals(7, moves.size());

    ArrayList<Move> preMoves = MoveUtils.getMovesPreCheckFilter(position);
    assertEquals(32, preMoves.size());
  }

  @Test
  public void testIsValidMove() throws Exception {
    Position position = new Position();
    assertTrue(position.isValidMove(new Move(7, 7, 7, 6, 0)));
    assertFalse(position.isValidMove(new Move(7, 7, 5, 1, 0)));
  }

  @Test
  public void testFilterCheck() throws Exception {
    Position position = new Position();
    position.moveNextBoard(new Move(7, 7, 7, 6, 0));
    position.moveNextBoard(new Move(3, 3, 3, 4, 0));
    position.moveNextBoard(new Move(8, 8, 3, 3, 1));

    ArrayList<Move> moves = MoveUtils.getMovesPreCheckFilter(position);
    assertEquals(32, moves.size());
    MoveUtils.filterCheck(position, moves);
    assertEquals(7, moves.size());
  }

  @Test
  public void testMoveTryNextBoard() throws Exception {
    Position position = new Position();
    position.moveNextBoard(new Move(7, 7, 7, 6, 0));
    position.moveNextBoard(new Move(3, 3, 3, 4, 0));
    position.moveNextBoard(new Move(8, 8, 3, 3, 1));
    position.moveTryNextBoard(new Move(3, 1, 3, 2, 0));

    Board board = new Board( new short[][]{
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   -3,  -6,  -1,  0,  0,  0,  1,  7,   3, 99},
        {99,    0,  -4,  12, -1,  0,  0,  1,  0,   4, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,  -14,   0,  -1,  0,  0,  0,  1,  0,  14, 99},
        {99,   -5,   0,  -1,  0,  0,  0,  1,  0,   5, 99},
        {99,   -4,   0,  -1,  0,  0,  1,  0,  0,   4, 99},
        {99,   -3,  -7,  -1,  0,  0,  0,  1,  0,   3, 99},
        {99,   -2,   0,  -1,  0,  0,  0,  1,  0,   2, 99},
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99}
    });
    int[] whitePiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    Position ePosition = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    assertEquals(ePosition, position);
  }
}