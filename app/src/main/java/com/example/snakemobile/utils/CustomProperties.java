package com.example.snakemobile.utils;

import static com.example.snakemobile.utils.Constants.*;

public class CustomProperties {
  private int screenHeight;
  private int screenWidth;
  private int cellHeight;
  private int cellWidth;

  private static CustomProperties customProperties;

  private CustomProperties() {
  }

  public static CustomProperties get() {
    if (customProperties == null) {
      customProperties = new CustomProperties();
    }
    return customProperties;
  }

  public void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
    this.cellHeight = (this.screenHeight - BOTTOM_PANEL_HEIGHT) / NUM_HORIZONTAL_LINES;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
    this.cellWidth = (this.screenWidth) / NUM_VERTICAL_LINES;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getCellHeight() {
    return cellHeight;
  }

  public int getCellWidth() {
    return cellWidth;
  }
}
