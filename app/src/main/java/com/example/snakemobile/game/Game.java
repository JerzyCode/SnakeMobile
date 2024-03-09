package com.example.snakemobile.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import com.example.snakemobile.controls.GestureListener;
import com.example.snakemobile.graphics.Drawer;
import com.example.snakemobile.objects.Fruit;
import com.example.snakemobile.objects.Snake;

import static com.example.snakemobile.utils.Constants.*;

@SuppressLint("ViewConstructor")
public class Game extends SurfaceView implements SurfaceHolder.Callback {

  private final GameLoop gameLoop;
  private final Context context;
  private final Snake snake;
  private Fruit fruit;
  private final Drawer drawer;

  public Game(Context context, GestureListener gestureListener) {
    super(context);

    SurfaceHolder surfaceHolder = getHolder();
    surfaceHolder.addCallback(this);

    this.snake = new Snake();
    this.fruit = new Fruit(this.snake.getTail());
    gestureListener.setSnake(this.snake);
    this.context = context;
    this.drawer = new Drawer(this.snake, this.context, this.fruit);
    gameLoop = new GameLoop(this, surfaceHolder);

    setFocusable(true);
  }

  private synchronized void calculateDimensions() {
    CELL_WIDTH = (int)(getWidth() / NUM_VERTICAL_LINES);
    CELL_HEIGHT = (int)(getHeight() / NUM_HORIZONTAL_LINES);
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
    drawer.drawGrid(canvas, getHeight(), getWidth());
    drawer.drawFPS(canvas, gameLoop.getAverageFPS());
    drawer.drawUPS(canvas, gameLoop.getAverageUPS());
    drawer.drawSnake(canvas);
    drawer.drawFruit(canvas);
  }

  public void update() {
    snake.move();
  }

}
