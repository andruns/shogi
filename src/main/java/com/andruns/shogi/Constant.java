package com.andruns.shogi;

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
}
