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
        TMP(0, null, false, -1, -1),
        FU(1, new int[][]{{0, 1}}, false, 8, -1),
        KY(2, new int[][]{{0, 9}}, true, 9, -1),
        KE(3, new int[][]{{-1, 2}, {1, 2}}, false, 10, -1),
        GI(4, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, -1}, {1, -1}}, false, 11, -1),
        KI(5, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false, -1, -1),
        KA(6, new int[][]{{-9, 9}, {9, 9}, {-9, -9}, {9, -9}}, true, 12, -1),
        HI(7, new int[][]{{0, 9}, {-9, 0}, {9, 0}, {0, -9}}, true, 13, -1),
        TO(8, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false, -1, 1),
        NKY(9, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false, -1, 2),
        NKE(10, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false, -1, 3),
        NG(11, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {0, -1}}, false, -1, 4),
        UM(12, new int[][]{{-9, 9}, {0, 1}, {9, 9}, {-1, 0}, {1, 0}, {-9, -9}, {0, -1}, {9, -9}}, true, -1, 6),
        RY(13, new int[][]{{-1, 1}, {0, 9}, {1, 1}, {-9, 0}, {9, 0}, {-1, -1}, {0, -9}, {1, -1}}, true, -1, 7),
        OU(14, new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}}, false, -1, -1);

        private final int id;
        private final int[][] movement;
        private final boolean tobi;
        private final int promotedPieceID;
        private final int demotedPieceID;

        private PieceName(int id, int[][] movement, boolean tobi, int promotedPieceID, int demotedPieceID) {
            this.id = id;
            this.movement = movement;
            this.tobi = tobi;
            this.promotedPieceID = promotedPieceID;
            this.demotedPieceID = demotedPieceID;
        }

        public static PieceName valueOf(final int value) {
            for (PieceName p : EnumSet.allOf(PieceName.class)) {
                if (p.id == value) {
                    return p;
                }
            }
            return null;
        }

        public int getId() {
            return id;
        }

        public int[][] getMovement() {
            return movement;
        }

        public boolean isTobi() {
            return tobi;
        }

        public boolean isPromotable() {
            return promotedPieceID > 0;
        }

        public int getPromotedPieceID() {
            return promotedPieceID;
        }

        public boolean isPromoted() {
            return demotedPieceID > 0;
        }

        public int getDemotedPieceID() {
            return demotedPieceID;
        }

    }
}
