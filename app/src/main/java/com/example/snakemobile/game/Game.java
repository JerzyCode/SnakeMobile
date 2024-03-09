package com.example.snakemobile.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.example.snakemobile.R;
import com.example.snakemobile.controls.GestureListener;
import com.example.snakemobile.objects.Snake;

import static com.example.snakemobile.utils.Constants.*;

@SuppressLint("ViewConstructor")
public class Game extends SurfaceView implements SurfaceHolder.Callback {

  private final GameLoop gameLoop;
  private final Context context;
  private final Snake snake;

  public Game(Context context, GestureListener gestureListener) {
    super(context);

    SurfaceHolder surfaceHolder = getHolder();
    surfaceHolder.addCallback(this);

    this.snake = new Snake();
    gestureListener.setSnake(this.snake);
    this.context = context;
    gameLoop = new GameLoop(this, surfaceHolder);

    setFocusable(true);
  }

  private void calculateDimensions() {
    CELL_WIDTH = (int)(getWidth() / NUM_HORIZONTAL_LINES);
    CELL_HEIGHT = (int)(getHeight() / NUM_VERTICAL_LINES);
    invalidate();
  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
    gameLoop.startLoop();

  }

  @Override
  public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    calculateDimensions();

  }

  @Override
  public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    drawGrid(canvas);
    drawUPS(canvas);
    drawFPS(canvas);
    drawSnake(canvas);
  }

  public void drawUPS(Canvas canvas) {
    String averageUPS = Double.toString(gameLoop.getAverageUPS());
    Paint paint = new Paint();
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    canvas.drawText("UPS: " + averageUPS, 100, 60, paint);
  }

  public void drawFPS(Canvas canvas) {
    String averageFPS = Double.toString(gameLoop.getAverageFPS());
    Paint paint = new Paint();
    int color = ContextCompat.getColor(context, R.color.magenta);
    paint.setColor(color);
    paint.setTextSize(50);
    canvas.drawText("FPS: " + averageFPS, 100, 115, paint);
  }

  private void drawGrid(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.white));
    paint.setTextSize(50);

    // drawing vertical lines
    for (int i = 0; i <= NUM_VERTICAL_LINES; i++) {
      float lineHeight;
      if (i == 0) {
        lineHeight = 0;
      }
      else {
        lineHeight = i * CELL_HEIGHT - 0.25f;
      }
      canvas.drawLine(0, lineHeight, getWidth(), lineHeight, paint);
    }

    // drawing horizontal lines
    for (int i = 0; i <= NUM_HORIZONTAL_LINES; i++) {
      float lineWidth;
      if (i == 0) {
        lineWidth = 0;
      }
      else {
        lineWidth = i * CELL_WIDTH - 0.25f;
      }
      canvas.drawLine(lineWidth, 0, lineWidth, getHeight(), paint);
    }
  }

  private void drawSnake(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(ContextCompat.getColor(context, R.color.green));
    drawRectangleInCell(canvas, snake.getxHead(), snake.getyHead(), paint);
  }

  public void update() {
    snake.move();
  }

  private void drawRectangleInCell(Canvas canvas, int x, int y, Paint paint) {
    float left = x * CELL_WIDTH;
    float top = y * CELL_HEIGHT;
    float right = left + CELL_WIDTH;
    float bottom = top + CELL_HEIGHT;
    canvas.drawRect(left, top, right, bottom, paint);
  }
}
