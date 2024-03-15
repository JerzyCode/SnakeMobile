package com.example.snakemobile.entities;

import com.example.snakemobile.controls.Direction;
import com.example.snakemobile.utils.CustomProperties;

public class Snake {

  private final CustomProperties properties = CustomProperties.get();
  private float xHead;
  private float yHead;
  private Direction direction;
  private final float[][] tail;
  private boolean hitWall;
  private int length;
  private boolean moved;

  public Snake() {
    direction = Direction.RIGHT;
    tail = new float[properties.getVerticalLines() * properties.getHorizontalLines()][2];
    System.out.println("verical = " + properties.getVerticalLines());
    this.xHead = 0;
    this.yHead = 0;
    this.hitWall = false;
    tail[0][0] = xHead;
    tail[0][1] = yHead;
    length = 5;
  }

  public float getxHead() {
    return xHead;
  }

  public float getyHead() {
    return yHead;
  }

  public float[][] getTail() {
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

  public boolean isMoved() {
    return moved;
  }

  public void setMoved(boolean moved) {
    this.moved = moved;
  }

  public boolean isHitWall() {
    return hitWall;
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
    if (yHead > properties.getHorizontalLines() - 1 || yHead < 0 ||
        xHead < 0 || xHead > properties.getVerticalLines() - 1)
      hitWall = true;
    //    if (yHead < 0)
    //      yHead = NUM_HORIZONTAL_LINES - 1.0f;
    //    if (xHead > NUM_VERTICAL_LINES - 1)
    //      xHead = 0;
    //    if (xHead < 0)
    //      xHead = NUM_VERTICAL_LINES - 1.0f;

    tail[0][0] = xHead;
    tail[0][1] = yHead;

    moved = true;
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
