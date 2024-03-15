package com.example.snakemobile.entities;

import java.util.Random;

import static com.example.snakemobile.utils.Constants.NUM_HORIZONTAL_LINES;
import static com.example.snakemobile.utils.Constants.NUM_VERTICAL_LINES;

public class Fruit {

  private final int x;
  private final int y;
  private static final Random random = new Random();
  private final float[][] snakeTail;

  public Fruit(float[][] snakeTail) {
    this.snakeTail = snakeTail;
    int randX = random.nextInt(NUM_VERTICAL_LINES - 1);
    int randY = random.nextInt(NUM_HORIZONTAL_LINES - 1);
    while (!areProperCoordinates(randX, randY)) {
      randX = random.nextInt(NUM_VERTICAL_LINES - 1);
      randY = random.nextInt(NUM_HORIZONTAL_LINES - 1);
    }
    this.x = randX;
    this.y = randY;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean areProperCoordinates(int x, int y) {
    for (float[] tab : snakeTail) {
      if (tab[0] == x && tab[1] == y) {
        return false;
      }
    }
    return true;
  }
}
