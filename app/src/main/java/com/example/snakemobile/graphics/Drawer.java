package com.example.snakemobile.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import com.example.snakemobile.R;
import com.example.snakemobile.objects.Fruit;
import com.example.snakemobile.objects.Snake;

import static com.example.snakemobile.utils.Constants.*;

public class Drawer {
  private final Snake snake;
  private Fruit fruit;
  private final Context context;

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
    int x = (NUM_VERTICAL_LINES - 2) * CELL_WIDTH - 15;
    int y = (NUM_HORIZONTAL_LINES) * CELL_HEIGHT + 5;

    canvas.drawText("SCORE: " + score, x, y, paint);
  }

  public void drawGrid(Canvas canvas, float height, float width) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.white));
    paint.setTextSize(50);

    // drawing vertical lines
    for (int i = 0; i <= NUM_HORIZONTAL_LINES; i++) {
      float lineHeight;
      if (i == 0) {
        lineHeight = 0;
      }
      else {
        lineHeight = i * CELL_HEIGHT - 0.25f;
      }
      canvas.drawLine(0, lineHeight, width, lineHeight, paint);
    }

    // drawing horizontal lines
    for (int i = 0; i <= NUM_VERTICAL_LINES; i++) {
      float lineWidth;
      if (i == 0) {
        lineWidth = 0;
      }
      else {
        lineWidth = i * CELL_WIDTH - 0.25f;
      }
      canvas.drawLine(lineWidth, 0, lineWidth, height, paint);
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
