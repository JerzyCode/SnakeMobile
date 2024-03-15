package com.example.snakemobile.utils;

import static com.example.snakemobile.utils.Constants.MARGIN;
import static com.example.snakemobile.utils.Constants.UNIT_SIZE;

public class CustomProperties {
  private int screenHeight;
  private int screenWidth;
  private int horizontalLines;
  private int verticalLines;

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
    this.screenHeight = screenHeight - 2 * MARGIN;
    this.horizontalLines = this.screenHeight / UNIT_SIZE;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth - 2 * MARGIN;
    this.verticalLines = this.screenWidth / UNIT_SIZE;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getHorizontalLines() {
    return horizontalLines;
  }

  public int getVerticalLines() {
    return verticalLines;
  }
}
