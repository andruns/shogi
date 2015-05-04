package com.andruns.shogi;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Constant {
    enum Turn {
        WHITE,
        BLACK
    }

    enum GameState {
        PREP,
        STARTING,
        WIN_BLACK,
        WIN_WHITE,
        DROW,
        SUSPEND
    }

    enum PieceName {
        TMP(0, null, false),
        FU(1, new int[][]{{0, 1}}, false),
        KY(2, new int[][]{{0, 9}}, true),
        KE(3, new int[][]{{-1, 2}, {1, 2}}, false),
        GI(4, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, -1}, {1, -1}}, false),
        KI(5, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false),
        KA(6, new int[][]{{-9, 9}, {9, 9}, {-9, -9}, {9, -9}}, true),
        HI(7, new int[][]{{0, 9}, {-9, 0}, {9, 0}, {0, -9}}, true),
        TO(8, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false),
        NKY(9, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false),
        NKE(10, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false),
        NG(11, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false),
        UM(12, new int[][]{{-9, 9}, {0, 1}, {9, 9}, {-1, 0}, {1, 0}, {-9, -9}, {0, -1}, {9, -9}}, true),
        RY(13, new int[][]{{-1, 1}, {0, 9}, {1, 1}, {-9, 0}, {9, 0}, {-1, -1}, {0, -9}, {1, -1}}, true),
        OU(14, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}}, false);

        private int id;
        private int[][] movement;
        boolean tobi;

        private PieceName(int id, int[][] movement, boolean tobi) {
            this.id = id;
            this.movement = movement;
            this.tobi = tobi;
        }

        public static PieceName valueOf(final int value) {
            for(PieceName p : EnumSet.allOf(PieceName.class)) {
                if(p.id == value) {
                    return p;
                }
            }
            return null;
        }

        public ArrayList<int[]> getMovement(int fromSuji, int fromDan, Turn turn) {
            ArrayList<int[]> movement = new ArrayList<int[]>();
            if(turn == Turn.WHITE) {
                if (this.tobi == true) {
                    // TODO
                    for (int[] cell : this.movement) {
                        int toSuji, toDan;
                        if (cell[0] == -9 && cell[1] == 9) {
                            for (toSuji = fromSuji + 1, toDan = fromDan - 1;
                                 toSuji <= 9 && toDan >= 1;
                                 toSuji++, toDan--) {
                                movement.add(new int[]{toSuji, toDan});
                            }
                        }
                        if (cell[0] == 0 && cell[1] == 9) {
                            toSuji = fromSuji;
                            for (toDan = fromDan - 1; toDan >= 1; toDan--) {
                                movement.add(new int[]{toSuji, toDan});
                            }
                        }
                        if (cell[0] == 9 && cell[1] == 9) {
                            for (toSuji = fromSuji - 1, toDan = fromDan - 1;
                                 toSuji >= 1 && toDan >= 1;
                                 toSuji--, toDan--) {
                                movement.add(new int[]{toSuji, toDan});
                            }
                        }
                    }
                } else {
                    int toSuji, toDan;
                    for (int[] cell : this.movement) {
                        toSuji = fromSuji - cell[0];
                        toDan = fromDan - cell[1];
                        if(1 <= toSuji && toSuji <= 9 && 1 <= toDan && toDan <= 9) {
                            movement.add(new int[]{toSuji, toDan});
                        }
                    }
                }
            } else {
                if (this.tobi == true) {
                    // TODO
                } else {
                    int toSuji, toDan;
                    for (int[] cell : this.movement) {
                        toSuji = fromSuji + cell[0];
                        toDan = fromDan + cell[1];
                        if(1 <= toSuji && toSuji <= 9 && 1 <= toDan && toDan <= 9) {
                            movement.add(new int[]{toSuji, toDan});
                        }
                    }
                }
            }
            return movement;
        }
    }
}
