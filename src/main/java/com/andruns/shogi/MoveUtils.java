package com.andruns.shogi;

import com.andruns.shogi.Constant.Turn;
import com.andruns.shogi.Constant.PieceName;
import java.util.ArrayList;

/**
 * Created by asanu0829 on 5/6/15.
 */
public class MoveUtils {
  static public ArrayList<int[]> getMovesPerPieceOnBoard(
      Position position, int pieceID, final int fromSuji, final int fromDan) {
    Turn turn = position.getTurn();
    Board board = position.getBoard();
    PieceName piece = Constant.PieceName.valueOf(pieceID);
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
          if(piece != Constant.PieceName.KE || toDan > 2) {
            if((piece != Constant.PieceName.FU && piece != Constant.PieceName.KY) || toDan != 1) {
              if(board.getCell(toSuji, toDan) <= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan <= 3 || fromDan <= 3) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
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
          if(piece != Constant.PieceName.KE || toDan < 8) {
            if((piece != Constant.PieceName.FU && piece != Constant.PieceName.KY) || toDan != 9) {
              if (board.getCell(toSuji, toDan) >= 0) {
                movements.add(new int[]{toSuji, toDan, 0});
                if (piece.isPromotable()) {
                  if (toDan >= 7 || fromDan >= 7) {
                    movements.add(new int[]{toSuji, toDan, 1});
                  }
                }
              }
            }
          }
        }
      }
    }
    return movements;
  }

  static void moveTryNextBoard(Position position, Move move) {
    Turn turn = position.getTurn();
    Board board = position.getBoard();
    int[] piecesWhiteInHand = position.getWhitePiecesInHand();
    int[] piecesBlackInHand = position.getBlackPiecesInHand();
    int fromSuji = move.getFromSuji();
    int fromDan = move.getFromDan();
    int toSuji = move.getToSuji();
    int toDan = move.getToDan();
    boolean promoting = move.isPromoting();
    if(fromSuji == 0) {
      if (turn == Turn.WHITE) {
        piecesWhiteInHand[fromDan]--;
        board.setCell(toSuji, toDan, fromDan);
      } else {
        piecesBlackInHand[fromDan]--;
        board.setCell(toSuji, toDan, -fromDan);
      }
    } else {
      int toPiece = board.getCell(toSuji, toDan);
      if(Math.abs(toPiece) == PieceName.OU.getId()) {
        return;
      }
      int fromPiece = board.getCell(fromSuji, fromDan);
      if (promoting) {
        fromPiece = (int)Math.signum(fromPiece)
            * PieceName.valueOf(Math.abs(fromPiece)).getPromotedPieceID();
      }
      board.setCell(toSuji, toDan, fromPiece);
      board.setCell(fromSuji, fromDan, 0);
      if (toPiece != 0) {
        toPiece = Math.abs(toPiece);
        if(PieceName.valueOf(toPiece).isPromoted()) {
          toPiece = PieceName.valueOf(toPiece).getDemotedPieceID();
        }
        if (turn == Turn.WHITE) {
          piecesWhiteInHand[toPiece]++;
        } else {
          piecesBlackInHand[toPiece]++;
        }
      }
    }
    turn = turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE;
  }
}