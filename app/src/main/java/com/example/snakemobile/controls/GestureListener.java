package com.example.snakemobile.controls;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.example.snakemobile.objects.Snake;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {

  private static final int SWIPE_THRESHOLD = 100;
  private static final int SWIPE_VELOCITY_THRESHOLD = 100;
  private Snake snake;

  public void setSnake(Snake snake) {
    this.snake = snake;
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    boolean result = false;
    if (!snake.isMoved()) {
      return true;
    }
    try {
      float diffY = e2.getY() - e1.getY();
      float diffX = e2.getX() - e1.getX();
      if (Math.abs(diffX) > Math.abs(diffY)) {
        result = onFlingHorizontal(diffX, velocityX);
      }
      else if (Math.abs(diffY) > Math.abs(diffX)) {
        result = onFlingVertical(diffY, velocityY);
      }
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
    return result;
  }

  public boolean onFlingHorizontal(float diffX, float velocityX) {
    Direction currentDirection = snake.getDirection();
    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
      if (diffX <= 0) {
        onSwipeLeft(currentDirection);
      }
      else {
        onSwipeRight(currentDirection);
      }
      snake.setMoved(false);
      return true;
    }
    return false;
  }

  public boolean onFlingVertical(float diffY, float velocityY) {
    Direction currentDirection = snake.getDirection();
    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
      if (diffY <= 0) {
        onSwipeUp(currentDirection);
      }
      else {
        onSwipeDown(currentDirection);
      }
      snake.setMoved(false);
      return true;
    }
    return false;
  }

  private void onSwipeUp(Direction currentDirection) {
    if (currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) {
      snake.setDirection(Direction.UP);
    }
  }

  private void onSwipeDown(Direction currentDirection) {
    if (currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) {
      snake.setDirection(Direction.DOWN);
    }

  }

  public void onSwipeRight(Direction currentDirection) {
    if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
      snake.setDirection(Direction.RIGHT);
    }
  }

  public void onSwipeLeft(Direction currentDirection) {
    if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
      snake.setDirection(Direction.LEFT);
    }
  }
}
