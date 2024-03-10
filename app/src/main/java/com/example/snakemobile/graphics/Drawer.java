package com.example.snakemobile.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import com.example.snakemobile.R;
import com.example.snakemobile.objects.Fruit;
import com.example.snakemobile.objects.Snake;
import com.example.snakemobile.utils.CustomProperties;

import static com.example.snakemobile.utils.Constants.*;

public class Drawer {
  private final Snake snake;
  private Fruit fruit;
  private final Context context;
  private static final CustomProperties customProperties = CustomProperties.get();
  private static final int SCREEN_WIDTH = customProperties.getScreenWidth();
  private static final int SCREEN_HEIGHT = customProperties.getScreenHeight();
  private static final int CELL_WIDTH = customProperties.getCellWidth();
  private static final int CELL_HEIGHT = customProperties.getCellHeight();

  public Drawer(Snake snake, Context context, Fruit fruit) {
    this.fruit = fruit;
    this.context = context;
    this.snake = snake;
  }

  public void setFruit(Fruit fruit) {
    this.fruit = fruit;
  }

  public void drawUPS(Canvas canvas, double ups) {
    String averageUPS = Double.toString(ups);
    Paint paint = new Paint();
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    canvas.drawText("UPS: " + averageUPS, 100, 60, paint);
  }

  public void drawFPS(Canvas canvas, double fps) {
    String averageFPS = Double.toString(fps);
    Paint paint = new Paint();
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    canvas.drawText("FPS: " + averageFPS, 100, 115, paint);
  }

  public void drawScore(Canvas canvas, int score) {
    Paint paint = new Paint();
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    int x = 1;
    int y = customProperties.getScreenHeight();

    canvas.drawText("SCORE: " + score, x, y, paint);
  }

  public void drawGameOver(Canvas canvas) {
    System.out.println("Draw Game Over");
    Paint paint = new Paint();
    int color = ContextCompat.getColor(context, R.color.red);
    paint.setColor(color);
    paint.setTextSize(70);
    int x = 200;
    int y = 222;
    System.out.println(String.format("x=%d, y=%d", x, y));
    canvas.drawText("GAME OVER", x, y, paint);
  }

  public void drawGrid(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.white));
    paint.setTextSize(50);

    // drawing horizontal lines
    for (int i = 0; i <= NUM_HORIZONTAL_LINES; i++) {
      int lineHeight;
      lineHeight = i * CELL_HEIGHT;
      canvas.drawLine(0, lineHeight, SCREEN_WIDTH, lineHeight, paint);
    }

    // drawing vertical lines
    for (int i = 0; i <= NUM_VERTICAL_LINES; i++) {
      int lineWidth;
      if (i == NUM_VERTICAL_LINES) {
        lineWidth = i * CELL_WIDTH - 1;
      }
      else {
        lineWidth = i * CELL_WIDTH;
      }
      canvas.drawLine(lineWidth, 0, lineWidth, SCREEN_HEIGHT - BOTTOM_PANEL_HEIGHT, paint);
    }
  }

  public void drawSnake(Canvas canvas) {
    drawSnakeTail(canvas);
    drawSnakeHead(canvas);
  }

  private void drawSnakeHead(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.orange));
    drawRectangleInCell(canvas, snake.getxHead(), snake.getyHead(), paint);
  }

  private void drawSnakeTail(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.green));
    int[][] tail = snake.getTail();
    int length = snake.getLength();
    for (int i = length - 1; i >= 0; i--) {
      drawRectangleInCell(canvas, tail[i][0], tail[i][1], paint);
    }
  }

  public void drawFruit(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.yellow));
    drawOvalInCell(canvas, fruit.getX(), fruit.getY(), paint);
  }

  private void drawRectangleInCell(Canvas canvas, float x, float y, Paint paint) {
    float left = x * CELL_WIDTH;
    float top = y * CELL_HEIGHT;
    float right = left + CELL_WIDTH;
    float bottom = top + CELL_HEIGHT;
    canvas.drawRect(left, top, right, bottom, paint);
  }

  private void drawOvalInCell(Canvas canvas, float x, float y, Paint paint) {
    float centerX = x * CELL_WIDTH + CELL_WIDTH / 2.0f;
    float centerY = y * CELL_HEIGHT + CELL_HEIGHT / 2.0f;
    float radius = Math.min(CELL_WIDTH, CELL_HEIGHT) / 2.0f;
    canvas.drawCircle(centerX, centerY, radius, paint);
  }

}
