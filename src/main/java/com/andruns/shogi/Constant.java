package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
public class Constant {
    enum Turn {
        WHITE,
        BLACK;
    }

    enum GameState {
        PREP,
        RUNNING,
        SUSOEND,
        WIN_BLACK,
        WIN_WHITE,
        SENNICHITE,
        JISHOGI,
        CHUDAN;
    }

    enum Piece {
        TMP(0),
        FU(1),
        KY(2),
        KE(3),
        GI(4),
        KI(5),
        KA(6),
        HI(7),
        TO(8),
        NKY(9),
        NKE(10),
        NG(11),
        UM(12),
        RY(13),
        OU(14);

        private int id;

        private Piece(int id) {
            this.id = id;
        }
    }
}
