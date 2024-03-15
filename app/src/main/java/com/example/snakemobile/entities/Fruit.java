package com.example.snakemobile.entities;

import com.example.snakemobile.utils.CustomProperties;

import java.util.Random;

public class Fruit {

  private final int x;
  private final int y;
  private static final Random random = new Random();
  private final float[][] snakeTail;
  private final CustomProperties properties = CustomProperties.get();

  public Fruit(float[][] snakeTail) {
    this.snakeTail = snakeTail;
    int randX = random.nextInt(properties.getVerticalLines() - 2) + 1;
    int randY = random.nextInt(properties.getHorizontalLines() - 2) + 1;
    while (!areProperCoordinates(randX, randY)) {
      randX = random.nextInt(properties.getVerticalLines() - 2) + 1;
      randY = random.nextInt(properties.getHorizontalLines() - 2) + 1;
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
