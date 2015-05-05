package com.andruns.shogi;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Constant {
    enum Turn implements Cloneable {
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

        private final int id;
        private final int[][] movement;
        private final boolean tobi;

        private PieceName(int id, int[][] movement, boolean tobi) {
            this.id = id;
            this.movement = movement;
            this.tobi = tobi;
        }

        public static PieceName valueOf(final int value) {
            for (PieceName p : EnumSet.allOf(PieceName.class)) {
                if (p.id == value) {
                    return p;
                }
            }
            return null;
        }

        public int[][] getMovement() {
            return movement;
        }

        public boolean isTobi() {
            return tobi;
        }
    }
}
