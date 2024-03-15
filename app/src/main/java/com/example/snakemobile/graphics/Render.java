package com.example.snakemobile.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
    int x = UNIT_SIZE;
    int y = SCREEN_HEIGHT / 2;
    canvas.drawText("GAME OVER", x, y, paint);
    paint.setTextSize(50);
    canvas.drawText("Press anywhere to back to main menu.", UNIT_SIZE - 5f, y + 80.0f, paint);
  }

  public void drawGrid(Canvas canvas) {
    paint.setColor(ContextCompat.getColor(context, R.color.white));
    paint.setTextSize(50);

    // drawing horizontal lines
    for (int i = 0; i <= customProperties.getHorizontalLines(); i++) {
      int lineHeight;
      lineHeight = i * UNIT_SIZE + MARGIN;
      canvas.drawLine(0, lineHeight, SCREEN_WIDTH, lineHeight, paint);
    }

    // drawing vertical lines
    for (int i = 0; i <= customProperties.getVerticalLines(); i++) {
      int lineWidth;
      lineWidth = i * UNIT_SIZE + MARGIN;
      canvas.drawLine(lineWidth, 0, lineWidth, (float)SCREEN_HEIGHT - BOTTOM_PANEL_HEIGHT, paint);
    }
  }

  public void renderBackground(Canvas canvas) {
    int bgWidth = customProperties.getScreenWidth() - MARGIN;
    int bgHeight = customProperties.getScreenHeight() - BOTTOM_PANEL_HEIGHT - 5;
    Rect rect = new Rect(MARGIN, MARGIN, bgWidth, bgHeight);
    canvas.drawBitmap(bitMapFactory.getBackgroundImage(), null, rect, null);
  }

  public void renderSnake(Canvas canvas) {
    renderSnakeHead(canvas);
    renderSnakeBody(canvas);
    renderSnakeTail(canvas);
  }

  private void renderSnakeHead(Canvas canvas) {
    float x = snake.getxHead() * UNIT_SIZE + MARGIN;
    float y = snake.getyHead() * UNIT_SIZE + MARGIN;
    switch (snake.getDirection()) {
      case DOWN -> canvas.drawBitmap(bitMapFactory.getHeadDownImg(), x, y, null);
      case UP -> canvas.drawBitmap(bitMapFactory.getHeadUpImg(), x, y, null);
      case LEFT -> canvas.drawBitmap(bitMapFactory.getHeadLeftImg(), x, y, null);
      case RIGHT -> canvas.drawBitmap(bitMapFactory.getHeadRightImg(), x, y, null);
    }
  }

  @SuppressLint("ResourceAsColor")
  public void renderSnakeHit(Canvas canvas) {
    paint.setColor(R.color.magenta);
    renderOvalInCell(canvas, snake.getxHead(), snake.getyHead(), paint);
  }

  public void renderFruit(Canvas canvas) {
    paint.setColor(ContextCompat.getColor(context, R.color.yellow));
    renderOvalInCell(canvas, fruit.getX(), fruit.getY(), paint);
  }

  private void renderRectangleInCell(Canvas canvas, float x, float y, Paint paint) {
    float left = x * UNIT_SIZE;
    float top = y * UNIT_SIZE;
    float right = left + UNIT_SIZE;
    float bottom = top + UNIT_SIZE;
    canvas.drawRect(left, top, right, bottom, paint);
  }

  private void renderOvalInCell(Canvas canvas, float x, float y, Paint paint) {
    float centerX = x * UNIT_SIZE + UNIT_SIZE / 2.0f + MARGIN;
    float centerY = y * UNIT_SIZE + UNIT_SIZE / 2.0f + MARGIN;
    float radius = UNIT_SIZE / 2.0f;
    canvas.drawCircle(centerX, centerY, radius, paint);
  }

  @SuppressLint("ResourceAsColor")
  private void renderSnakeBody(Canvas canvas) {
    float[][] tail = snake.getTail();
    for (int i = snake.getLength() - 3; i >= 0; i--) {

      float x0 = tail[i][0];
      float y0 = tail[i][1];
      float x1 = tail[i + 1][0];
      float y1 = tail[i + 1][1];
      float x2 = tail[i + 2][0];
      float y2 = tail[i + 2][1];
      if (isDrawBottomLeft(x0, x1, x2, y0, y1, y2)) {
        drawBitmapByCell(canvas, bitMapFactory.getBodyBottomLeftImg(), x1, y1);
      }
      else if (isDrawBottomRight(x0, x1, x2, y0, y1, y2)) {
        drawBitmapByCell(canvas, bitMapFactory.getBodyBottomRightImg(), x1, y1);
      }
      else if (isDrawTopRight(x0, x1, x2, y0, y1, y2)) {
        drawBitmapByCell(canvas, bitMapFactory.getBodyTopRightImg(), x1, y1);
      }
      else if (isDrawTopLeft(x0, x1, x2, y0, y1, y2)) {
        drawBitmapByCell(canvas, bitMapFactory.getBodyTopLeftImg(), x1, y1);
      }
      else if (y0 == y1 && y1 == y2) {
        drawBitmapByCell(canvas, bitMapFactory.getBodyHorizontalImg(), x1, y1);
      }
      else if (x0 == x1 && x1 == x2) {
        drawBitmapByCell(canvas, bitMapFactory.getBodyVerticalImg(), x1, y1);
      }

      if (snake.isSnakeEatItself()) {
        paint.setColor(R.color.magenta);
        renderRectangleInCell(canvas, snake.getxHead(), snake.getyHead(), paint);
      }

    }
  }

  private void renderSnakeTail(Canvas canvas) {
    float tailPreLastX = snake.getTail()[snake.getLength() - 2][0];
    float tailPreLastY = snake.getTail()[snake.getLength() - 2][1];
    float tailLastX = snake.getTail()[snake.getLength() - 1][0];
    float tailLastY = snake.getTail()[snake.getLength() - 1][1];

    if (tailPreLastX > tailLastX) {
      drawBitmapByCell(canvas, bitMapFactory.getTailLeftImg(), tailLastX, tailLastY);
    }
    else if (tailPreLastX < tailLastX) {
      drawBitmapByCell(canvas, bitMapFactory.getTailRightImg(), tailLastX, tailLastY);
    }
    else if (tailPreLastY < tailLastY) {
      drawBitmapByCell(canvas, bitMapFactory.getTailDownImg(), tailLastX, tailLastY);
    }
    else if (tailPreLastY > tailLastY) {
      drawBitmapByCell(canvas, bitMapFactory.getTailUpImg(), tailLastX, tailLastY);
    }
  }

  private void drawBitmapByCell(Canvas canvas, Bitmap bitmap, float x, float y) {
    canvas.drawBitmap(bitmap, x * UNIT_SIZE + MARGIN, y * UNIT_SIZE + MARGIN, null);
  }

  private boolean isDrawBottomLeft(float x0, float x1, float x2, float y0, float y1, float y2) {
    return (x0 < x1 && y0 == y1 && x1 == x2 && y1 < y2) || (y0 > y1 && x0 == x1 && y1 == y2 && x1 > x2);
  }

  private boolean isDrawBottomRight(float x0, float x1, float x2, float y0, float y1, float y2) {
    return (x0 > x1 && y0 == y1 && x1 == x2 && y1 < y2) || (y0 > y1 && x0 == x1 && y1 == y2 && x1 < x2);
  }

  private boolean isDrawTopRight(float x0, float x1, float x2, float y0, float y1, float y2) {
    return (y0 < y1 && x0 == x1 && y1 == y2 && x1 < x2) || (x0 > x1 && y1 == y0 && x1 == x2 && y2 < y1);
  }

  private boolean isDrawTopLeft(float x0, float x1, float x2, float y0, float y1, float y2) {
    return (y0 == y1 && x1 == x2 && x0 < x1 && y2 < y1) || (y0 < y1 && x0 == x1 && x1 > x2 && y1 == y2);
  }

}
