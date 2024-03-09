package com.example.snakemobile.objects;

import com.example.snakemobile.controls.Direction;

import static com.example.snakemobile.utils.Constants.*;

public class Snake {

  private int xHead;
  private int yHead;
  private Direction direction;

  public Snake() {
    direction = Direction.RIGHT;
    this.xHead = 0;
    this.yHead = 0;
  }

  public int getxHead() {
    return xHead;
  }

  public int getyHead() {
    return yHead;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void move(){
    switch (direction) {
      case UP -> yHead -= 1;
      case DOWN -> yHead += 1;
      case LEFT -> xHead -= 1;
      case RIGHT -> xHead += 1;
    }
    if (yHead > NUM_VERTICAL_LINES)
      yHead = 0;
    if (yHead < 0)
      yHead = (int)(NUM_HORIZONTAL_LINES);
    if (xHead > NUM_HORIZONTAL_LINES )
      xHead = 0;
    if (xHead < 0)
      xHead = (int)(NUM_VERTICAL_LINES);
  }
}
