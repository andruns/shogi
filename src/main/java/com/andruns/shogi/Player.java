package com.andruns.shogi;

/**
 * Created by asanu0829 on 3/15/15.
 */
abstract class Player {
  abstract Move generateNextMove(Position position);
}