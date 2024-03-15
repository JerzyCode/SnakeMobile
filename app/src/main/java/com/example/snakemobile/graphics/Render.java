package com.example.snakemobile.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import com.example.snakemobile.R;
import com.example.snakemobile.entities.Fruit;
import com.example.snakemobile.entities.Snake;
import com.example.snakemobile.utils.CustomProperties;

import static com.example.snakemobile.utils.Constants.*;

public class Render {
  private final Snake snake;
  private Fruit fruit;
  private final Context context;
  private final BitMapFactory bitMapFactory;
  private static final CustomProperties customProperties = CustomProperties.get();
  private static final int SCREEN_WIDTH = customProperties.getScreenWidth();
  private static final int SCREEN_HEIGHT = customProperties.getScreenHeight();
  private static final int CELL_WIDTH = customProperties.getCellWidth();
  private static final int CELL_HEIGHT = customProperties.getCellHeight();
  private final Paint paint;

  public Render(Snake snake, Context context, Fruit fruit) {
    this.fruit = fruit;
    this.context = context;
    this.snake = snake;
    this.paint = new Paint();
    this.bitMapFactory = new BitMapFactory(context);
  }

  public void setFruit(Fruit fruit) {
    this.fruit = fruit;
  }

  public void renderUPS(Canvas canvas, double ups) {
    String averageUPS = Double.toString(ups);
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    canvas.drawText("UPS: " + averageUPS, 100, 60, paint);
  }

  public void renderFPS(Canvas canvas, double fps) {
    String averageFPS = Double.toString(fps);
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    canvas.drawText("FPS: " + averageFPS, 100, 115, paint);
  }

  public void renderScore(Canvas canvas, int score) {
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    int x = 1;
    int y = customProperties.getScreenHeight();

    canvas.drawText("SCORE: " + score, x, y, paint);
  }

  public void renderGameOver(Canvas canvas) {
    int color = ContextCompat.getColor(context, R.color.red);
    paint.setColor(color);
    paint.setTextSize(150);
    int x = CELL_WIDTH;
    int y = SCREEN_HEIGHT / 2;
    canvas.drawText("GAME OVER", x, y, paint);
    paint.setTextSize(50);
    canvas.drawText("Press anywhere to back to main menu.", CELL_WIDTH - 5f, y + 80.0f, paint);
  }

  public void drawGrid(Canvas canvas) {
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
      canvas.drawLine(lineWidth, 0, lineWidth, (float)SCREEN_HEIGHT - BOTTOM_PANEL_HEIGHT, paint);
    }
  }

  public void renderBackground(Canvas canvas) {
    canvas.drawBitmap(bitMapFactory.getBackgroundImage(), 0, 0, null);
  }

  public void renderSnake(Canvas canvas) {
    renderSnakeTail(canvas);
    renderSnakeHead(canvas);
  }

  private void renderSnakeHead(Canvas canvas) {
    renderSnakeHead(canvas, R.color.red);
  }

  private void renderSnakeTail(Canvas canvas) {
    paint.setColor(ContextCompat.getColor(context, R.color.green));
    float[][] tail = snake.getTail();
    int length = snake.getLength();
    for (int i = length - 1; i >= 0; i--) {
      renderRectangleInCell(canvas, tail[i][0], tail[i][1], paint);
    }
  }

  private void renderSnakeHead(Canvas canvas, int color) {
    paint.setColor(ContextCompat.getColor(context, color));
    renderRectangleInCell(canvas, snake.getxHead(), snake.getyHead(), paint);
  }

  public void renderFruit(Canvas canvas) {
    paint.setColor(ContextCompat.getColor(context, R.color.yellow));
    renderOvalInCell(canvas, fruit.getX(), fruit.getY(), paint);
  }

  public void renderSnakeEatItSelf(Canvas canvas) {
    renderSnakeHead(canvas, R.color.magenta);
  }

  private void renderRectangleInCell(Canvas canvas, float x, float y, Paint paint) {
    float left = x * CELL_WIDTH;
    float top = y * CELL_HEIGHT;
    float right = left + CELL_WIDTH;
    float bottom = top + CELL_HEIGHT;
    canvas.drawRect(left, top, right, bottom, paint);
  }

  private void renderOvalInCell(Canvas canvas, float x, float y, Paint paint) {
    float centerX = x * CELL_WIDTH + CELL_WIDTH / 2.0f;
    float centerY = y * CELL_HEIGHT + CELL_HEIGHT / 2.0f;
    float radius = Math.min(CELL_WIDTH, CELL_HEIGHT) / 2.0f;
    canvas.drawCircle(centerX, centerY, radius, paint);
  }

}
