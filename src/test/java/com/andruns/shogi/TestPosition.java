package com.andruns.shogi;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class TestPosition extends TestCase {

  @Test
    public void testMoveNextBoard() throws Exception {
    Position position = new Position();
    position.moveNextBoard("7776");
    position.moveNextBoard("3334");
    position.moveNextBoard("88221");

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

    assertTrue(moves.contains(MoveUtils.stringToMove("1716", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2726", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("3736", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("4746", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("5756", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("6766", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("7776", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("8786", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("9796", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2838", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2848", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2858", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2868", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2878", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("2818", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("1918", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("3948", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("3938", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("4958", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("4948", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("4938", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("5968", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("5958", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("5948", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("6978", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("6968", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("6958", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("7978", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("7968", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("9998", position)));
  }

  @Test
  public void testGetMovesPreCheckFilter() throws Exception {
    Position position = new Position();
    position.moveNextBoard(MoveUtils.stringToMove("7776", position));
    position.moveNextBoard(MoveUtils.stringToMove("3334", position));
    position.moveNextBoard(MoveUtils.stringToMove("88331", position));

    ArrayList<Move> moves = position.getMoves();
    assertEquals(7, moves.size());

    ArrayList<Move> preMoves = MoveUtils.getMovesPreCheckFilter(position);
    assertEquals(32, preMoves.size());
  }

  @Test
  public void testIsValidMove() throws Exception {
    Position position = new Position();
    assertTrue(position.isValidMove(MoveUtils.stringToMove("7776", position)));
    assertFalse(position.isValidMove(MoveUtils.stringToMove("7751", position)));
  }

  @Test
  public void testFilterCheck() throws Exception {
    Position position = new Position();
    position.moveNextBoard(MoveUtils.stringToMove("7776", position));
    position.moveNextBoard(MoveUtils.stringToMove("3334", position));
    position.moveNextBoard(MoveUtils.stringToMove("88331", position));

    ArrayList<Move> moves = MoveUtils.getMovesPreCheckFilter(position);
    assertEquals(32, moves.size());
    MoveUtils.filterInvalidMove(position, moves);
    assertEquals(7, moves.size());
  }

  @Test
  public void testMoveTryNextBoard() throws Exception {
    Position position = new Position();
    position.moveNextBoard(MoveUtils.stringToMove("7776", position));
    position.moveNextBoard(MoveUtils.stringToMove("3334", position));
    position.moveNextBoard(MoveUtils.stringToMove("88331", position));
    position.moveTryNextBoard(MoveUtils.stringToMove("3132", position));

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

  @Test
  public void testKEIMove() throws Exception {
    Board board = new Board( new short[][]{
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   3,  0,  0,  0, -3,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99}
    });
    int[] whitePiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 0, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);
    ArrayList<Move> moves = position.getMoves();
    assertTrue(moves.contains(MoveUtils.stringToMove("53411", position)));
    assertTrue(moves.contains(MoveUtils.stringToMove("53611", position)));

    Position position2 = new Position(Constant.Turn.BLACK, board, whitePiecesInHand, blackPiecesInHand);
    ArrayList<Move> moves2 = position2.getMoves();
    assertTrue(moves2.contains(MoveUtils.stringToMove("57491", position)));
    assertTrue(moves2.contains(MoveUtils.stringToMove("57691", position)));
  }

  @Test
  public void testBackMove() throws Exception {
    Position position = new Position();
    position.moveNextBoard(MoveUtils.stringToMove("7776", position));
    position.moveNextBoard(MoveUtils.stringToMove("3334", position));
    position.moveNextBoard(MoveUtils.stringToMove("88221", position));
    Position position0 = position.clone();
    Move move0 = MoveUtils.stringToMove("3122", position);
    position.moveNextBoard(move0);
    Position position1 = position.clone();
    Move move1 = MoveUtils.stringToMove("0645", position);
    position.moveNextBoard(move1);
    Position position2 = position.clone();
    Move move2 = MoveUtils.stringToMove("2233", position);
    position.moveNextBoard(move2);
    Position position3 = position.clone();
    Move move3 = MoveUtils.stringToMove("45631", position);
    position.moveNextBoard(move3);
    Position position4 = position.clone();
    Move move4 = MoveUtils.stringToMove("0665", position);
    position.moveNextBoard(move4);
    Position position5 = position.clone();
    Move move5 = MoveUtils.stringToMove("6381", position);
    position.moveNextBoard(move5);
    Position position6 = position.clone();
    Move move6 = MoveUtils.stringToMove("65471", position);
    position.moveNextBoard(move6);
    Position position7 = position.clone();
    Move move7 = MoveUtils.stringToMove("8136", position);
    position.moveNextBoard(move7);
    Position position8 = position.clone();
    Move move8 = MoveUtils.stringToMove("4736", position);
    position.moveNextBoard(move8);
    Position position9 = position.clone();
    Move move9 = MoveUtils.stringToMove("3736", position);
    position.moveNextBoard(move9);

    position.moveBackBoard(move9);
    assertEquals(position9, position);
    position.moveBackBoard(move8);
    assertEquals(position8, position);
    position.moveBackBoard(move7);
    assertEquals(position7, position);
    position.moveBackBoard(move6);
    assertEquals(position6, position);
    position.moveBackBoard(move5);
    assertEquals(position5, position);
    position.moveBackBoard(move4);
    assertEquals(position4, position);
    position.moveBackBoard(move3);
    assertEquals(position3, position);
    position.moveBackBoard(move2);
    assertEquals(position2, position);
    position.moveBackBoard(move1);
    assertEquals(position1, position);
    position.moveBackBoard(move0);
    assertEquals(position0, position);
  }

  @Test
  public void testNifu() throws Exception {
    Board board = new Board( new short[][]{
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,  -1,  0,  0,  0,  1,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,    0,   0,   0,  0,  0,  0,  0,  0,   0, 99},
        {99,   99,  99,  99, 99, 99, 99, 99, 99,  99, 99}
    });
    int[] whitePiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    int[] blackPiecesInHand = {0, 1, 0, 0, 0, 0, 0, 0};
    Position position = new Position(Constant.Turn.WHITE, board, whitePiecesInHand, blackPiecesInHand);

    Move move0 = MoveUtils.stringToMove("0156", position);
    Move move1 = MoveUtils.stringToMove("0146", position);
    assertFalse(position.moveNextBoard(move0));
    assertTrue(position.moveNextBoard(move1));

    Move move2 = MoveUtils.stringToMove("0154", position);
    Move move3 = MoveUtils.stringToMove("0144", position);
    assertFalse(position.moveNextBoard(move2));
    assertTrue(position.moveNextBoard(move3));
  }
}