package com.example.snakemobile.objects;

import java.util.Random;

import static com.example.snakemobile.utils.Constants.NUM_HORIZONTAL_LINES;
import static com.example.snakemobile.utils.Constants.NUM_VERTICAL_LINES;

public class Fruit {

  private int x;
  private int y;
  private static final Random random = new Random();
  private final int[][] snakeTail;

  public Fruit(int[][] snakeTail) {
    this.snakeTail = snakeTail;
    int x = random.nextInt(NUM_VERTICAL_LINES - 1);
    int y = random.nextInt(NUM_HORIZONTAL_LINES - 1);
    while (!areProperCoordinates(x, y)) {
      x = random.nextInt(NUM_VERTICAL_LINES - 1);
      y = random.nextInt(NUM_HORIZONTAL_LINES - 1);
    }
    this.x = x;
    this.y = y;
    System.out.println("Fruit x=" + this.x);
    System.out.println("Fruiy y=" + this.y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean areProperCoordinates(int x, int y) {
    for (int[] tab : snakeTail) {
      if (tab[0] == x && tab[1] == y) {
        return false;
      }
    }
    return true;
  }
}
