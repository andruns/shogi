package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by asanu0829 on 5/6/15.
 */
public class MoveUtils {
  static ArrayList<int[]> getMovesPerPieceOnBoard(
      Position position, int pieceID, final int fromSuji, final int fromDan) {
    Turn turn = position.getTurn();
    Board board = position.getBoard();
    Piece piece = Piece.valueOf(pieceID);
    ArrayList<int[]> movements = new ArrayList<int[]>();
    if(turn == Constant.Turn.WHITE) {
      int toSuji, toDan;
      if (piece.isTobi()) {
        for (int[] cell : piece.getMovement()) {
          if (cell[0] == -9 && cell[1] == 9) {
            for (toSuji = fromSuji + 1, toDan = fromDan - 1;
                 toSuji <= 9 && toDan >= 1;
                 toSuji++, toDan--) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 0 && cell[1] == 9) {
            toSuji = fromSuji;
            for (toDan = fromDan - 1; toDan >= 1; toDan--) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 9 && cell[1] == 9) {
            for (toSuji = fromSuji - 1, toDan = fromDan - 1;
                 toSuji >= 1 && toDan >= 1;
                 toSuji--, toDan--) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == -9 && cell[1] == 0) {
            toDan = fromDan;
            for (toSuji = fromSuji + 1; toSuji <= 9; toSuji++) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 9 && cell[1] == 0) {
            toDan = fromDan;
            for (toSuji = fromSuji - 1; toSuji >= 1; toSuji--) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == -9 && cell[1] == -9) {
            for (toSuji = fromSuji + 1, toDan = fromDan + 1;
                 toSuji <= 9 && toDan <= 9;
                 toSuji++, toDan++) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 0 && cell[1] == -9) {
            toSuji = fromSuji;
            for (toDan = fromDan + 1; toDan <= 9; toDan++) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 9 && cell[1] == -9) {
            for (toSuji = fromSuji - 1, toDan = fromDan + 1;
                 toSuji >= 1 && toDan <= 9;
                 toSuji--, toDan++) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
        }
      }
      for (int[] cell : piece.getMovement()) {
        toSuji = fromSuji - cell[0];
        toDan = fromDan - cell[1];
        if(1 <= toSuji && toSuji <= 9 && 1 <= toDan && toDan <= 9) {
          if(board.getCell(toSuji, toDan) <= 0) {
            if(piece != Piece.KE || toDan > 2) {
              if((piece != Piece.FU && piece != Piece.KY) || toDan != 1) {
                movements.add(new int[]{toSuji, toDan, 0});
              }
            }
            if (piece.isPromotable()) {
              if (toDan <= 3 || fromDan <= 3) {
                movements.add(new int[]{toSuji, toDan, 1});
              }
            }
          }
        }
      }
    } else {
      int toSuji, toDan;
      if (piece.isTobi()) {
        for (int[] cell : piece.getMovement()) {
          if (cell[0] == -9 && cell[1] == 9) {
            for (toSuji = fromSuji - 1, toDan = fromDan + 1;
                 toSuji >= 1 && toDan <= 9;
                 toSuji--, toDan++) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 0 && cell[1] == 9) {
            toSuji = fromSuji;
            for (toDan = fromDan + 1; toDan <= 9; toDan++) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 9 && cell[1] == 9) {
            for (toSuji = fromSuji + 1, toDan = fromDan + 1;
                 toSuji <= 9 && toDan <= 9;
                 toSuji++, toDan++) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == -9 && cell[1] == 0) {
            toDan = fromDan;
            for (toSuji = fromSuji - 1; toSuji >= 1; toSuji--) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 9 && cell[1] == 0) {
            toDan = fromDan;
            for (toSuji = fromSuji + 1; toSuji <= 9; toSuji++) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == -9 && cell[1] == -9) {
            for (toSuji = fromSuji - 1, toDan = fromDan - 1;
                 toSuji >= 1 && toDan >= 1;
                 toSuji--, toDan--) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 0 && cell[1] == -9) {
            toSuji = fromSuji;
            for (toDan = fromDan - 1; toDan >= 1; toDan--) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
          if (cell[0] == 9 && cell[1] == -9) {
            for (toSuji = fromSuji + 1, toDan = fromDan - 1;
                 toSuji <= 9 && toDan >= 1;
                 toSuji++, toDan--) {
              if(board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (toDan >= 7 || fromDan >= 7) {
                  movements.add(new int[]{toSuji, toDan, 1});
                }
              }
              if(board.getCell(toSuji, toDan) != 0) {
                break;
              }
            }
          }
        }
      }
      for (int[] cell : piece.getMovement()) {
        toSuji = fromSuji + cell[0];
        toDan = fromDan + cell[1];
        if(1 <= toSuji && toSuji <= 9 && 1 <= toDan && toDan <= 9) {
          if (board.getCell(toSuji, toDan) >= 0) {
            if(piece != Piece.KE || toDan < 8) {
              if((piece != Piece.FU && piece != Piece.KY) || toDan != 9) {
                movements.add(new int[]{toSuji, toDan, 0});
              }
            }
            if (piece.isPromotable()) {
              if (toDan >= 7 || fromDan >= 7) {
                movements.add(new int[]{toSuji, toDan, 1});
              }
            }
          }
        }
      }
    }
    return movements;
  }

  static ArrayList<Move> getMovesPreCheckFilter(Position position) {
    Turn turn = position.getTurn();
    Board board = position.getBoard();
    int[] piecesWhiteInHand = position.getWhitePiecesInHand();
    int[] piecesBlackInHand = position.getBlackPiecesInHand();
    ArrayList<Move> moves = new ArrayList<Move>();
    int pieceID;

    // from board
    for(int dan = 1; dan <=9; dan++) {
      for(int suji = 1; suji <= 9; suji++) {
        if((turn == Turn.WHITE && board.getCell(suji, dan) > 0) ||
            (turn == Turn.BLACK && board.getCell(suji, dan) < 0)) {
          pieceID = Math.abs(board.getCell(suji, dan));
          for(int[] movement: MoveUtils.getMovesPerPieceOnBoard(position, pieceID, suji, dan)) {
            moves.add(new Move(suji, dan, movement[0], movement[1], movement[2]));
          }
        }
      }
    }

    // from pieces in hand
    if (turn == Turn.WHITE) {
      for (int i = 1; i <= 7; i++) {
        if (piecesWhiteInHand[i] > 0) {
          for (int dan = 1; dan <= 9; dan++) {
            for (int suji = 1; suji <= 9; suji++) {
              if (board.getCell(suji, dan) == 0) {
                if(Piece.valueOf(i) != Piece.KE || dan > 2) {
                  if ((Piece.valueOf(i) != Piece.FU && Piece.valueOf(i) != Piece.KY)
                      || dan != 1) {
                    moves.add(new Move(0, i, suji, dan));
                  }
                }
              }
            }
          }
        }
      }
    } else {
      for (int i = 1; i <= 7; i++) {
        if (piecesBlackInHand[i] > 0) {
          for (int dan = 1; dan <= 9; dan++) {
            for (int suji = 1; suji <= 9; suji++) {
              if (board.getCell(suji, dan) == 0) {
                if(Piece.valueOf(i) != Piece.KE || dan < 8) {
                  if ((Piece.valueOf(i) != Piece.FU && Piece.valueOf(i) != Piece.KY)
                      || dan != 9) {
                    moves.add(new Move(0, i, suji, dan));
                  }
                }
              }
            }
          }
        }
      }
    }
    return moves;
  }

  static void filterCheck(Position position, ArrayList<Move> moves){
    Iterator<Move> iMoves = moves.iterator();
    Position cPosition;
    while(iMoves.hasNext()) {
      Move move = iMoves.next();
      cPosition = position.clone();
      cPosition.moveTryNextBoard(move);
      for (int dan = 1; dan <= 9; dan++) {
        for (int suji = 1; suji <= 9; suji++) {
          if((cPosition.getTurn() == Turn.BLACK && cPosition.getBoard().getCell(suji, dan) == Piece.OU.getId()) ||
              (cPosition.getTurn() == Turn.WHITE && cPosition.getBoard().getCell(suji, dan) == -Piece.OU.getId())) {
            for(Move m: MoveUtils.getMovesPreCheckFilter(cPosition)) {
              if(m.getToSuji() == suji && m.getToDan() == dan) {
                iMoves.remove();
                break;
              }
            }
          }
        }
      }
    }
  }
}