package com.example.snakemobile.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.example.snakemobile.R;

import static com.example.snakemobile.utils.Constants.NUM_HORIZONTAL_LINES;
import static com.example.snakemobile.utils.Constants.NUM_VERTICAL_LINES;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

  private final GameLoop gameLoop;
  private final Context context;
  private float cellWidth;
  private float cellHeight;

  public Game(Context context) {
    super(context);

    SurfaceHolder surfaceHolder = getHolder();
    surfaceHolder.addCallback(this);

    this.context = context;
    gameLoop = new GameLoop(this, surfaceHolder);

    setFocusable(true);
  }

  private void calculateDimensions() {
    cellWidth = getWidth() / NUM_HORIZONTAL_LINES;
    cellHeight = getHeight() / NUM_VERTICAL_LINES;
    invalidate();
  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
    //    calculateDimensions();
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
        lineHeight = i * cellHeight;
      }
      else {
        lineHeight = i * cellHeight - 0.25f;
      }
      canvas.drawLine(0, lineHeight, getWidth(), lineHeight, paint);
    }

    // drawing horizontal lines
    for (int i = 0; i <= NUM_HORIZONTAL_LINES; i++) {
      float lineWidth;
      if (i == 0) {
        lineWidth = i * cellHeight;
      }
      else {
        lineWidth = i * cellWidth - 0.25f;
      }
      canvas.drawLine(lineWidth, 0, lineWidth, getHeight(), paint);
    }
  }

  public void update() {

  }
}
