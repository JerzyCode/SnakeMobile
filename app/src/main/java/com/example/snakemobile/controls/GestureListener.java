package com.example.snakemobile.controls;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {

  private static final int SWIPE_THRESHOLD = 100;
  private static final int SWIPE_VELOCITY_THRESHOLD = 100;

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    boolean result = false;
    try {
      float diffY = e2.getY() - e1.getY();
      float diffX = e2.getX() - e1.getX();
      if (Math.abs(diffX) > Math.abs(diffY)) {
        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
          if (diffX <= 0) {
            onSwipeLeft();
          }
          else {
            onSwipeRight();
          }
          result = true;
        }
      }
      else if (Math.abs(diffY) > Math.abs(diffX)) {
        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
          if (diffY <= 0) {
            onSwipeUp();
          }
          else {
            onSwipeDown();
          }
          result = true;
        }
      }
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
    return result;
  }

  private void onSwipeUp() {
    System.out.println("swipeUp");
  }

  private void onSwipeDown() {
    System.out.println("swipeDown");

  }

  public void onSwipeRight() {
    System.out.println("swipeRight");
  }

  public void onSwipeLeft() {
    System.out.println("swipeLeft");
  }
}
