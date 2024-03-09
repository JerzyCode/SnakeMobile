package com.example.snakemobile.objects;

import com.example.snakemobile.controls.Direction;

import static com.example.snakemobile.utils.Constants.NUM_HORIZONTAL_LINES;
import static com.example.snakemobile.utils.Constants.NUM_VERTICAL_LINES;

public class Snake {

  private int xHead;
  private int yHead;
  private Direction direction;
  private final int[][] tail;
  private int length;

  public Snake() {
    direction = Direction.RIGHT;
    tail = new int[NUM_VERTICAL_LINES * NUM_HORIZONTAL_LINES][2];
    this.xHead = 0;
    this.yHead = 0;
    tail[0][0] = xHead;
    tail[0][1] = yHead;
    length = 2;
  }

  public int getxHead() {
    return xHead;
  }

  public int getyHead() {
    return yHead;
  }

  public int[][] getTail() {
    return tail;
  }

  public int getLength() {
    return length;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public Direction getDirection() {
    return direction;
  }

  public void move() {

    for (int i = length - 1; i > 0; i--) {
      tail[i][0] = tail[i - 1][0];
      tail[i][1] = tail[i - 1][1];
    }
    if (length > 0) {
      tail[0][0] = xHead;
      tail[0][1] = yHead;
    }

    switch (direction) {
      case UP -> yHead -= 1;
      case DOWN -> yHead += 1;
      case LEFT -> xHead -= 1;
      case RIGHT -> xHead += 1;
    }
    if (yHead > NUM_HORIZONTAL_LINES - 1)
      yHead = 0;
    if (yHead < 0)
      yHead = NUM_HORIZONTAL_LINES - 1;
    if (xHead > NUM_VERTICAL_LINES - 1)
      xHead = 0;
    if (xHead < 0)
      xHead = NUM_VERTICAL_LINES - 1;

    tail[0][0] = xHead;
    tail[0][1] = yHead;
  }

  public boolean isSnakeEatItself() {
    for (int i = 2; i <= length; i++) {
      if (tail[0][0] == tail[i - 1][0] && tail[0][1] == tail[i - 1][1]) {
        return true;
      }
    }
    return false;
  }

  public void eatFruit() {
    length += 1;
    tail[length - 1][0] = tail[length - 2][0];
    tail[length - 1][1] = tail[length - 2][1];
  }

}
