package com.example.snakemobile.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.example.snakemobile.controls.GestureListener;
import com.example.snakemobile.graphics.Drawer;
import com.example.snakemobile.objects.Fruit;
import com.example.snakemobile.objects.Snake;
import com.example.snakemobile.utils.CustomProperties;

@SuppressLint("ViewConstructor")
public class Game extends SurfaceView implements SurfaceHolder.Callback {

  private final GameLoop gameLoop;
  private final Context context;
  private final Snake snake;
  private Fruit fruit;
  private int score;
  private final Drawer drawer;

  public Game(Context context, GestureListener gestureListener) {
    super(context);

    SurfaceHolder surfaceHolder = getHolder();
    surfaceHolder.addCallback(this);
    this.context = context;
    calculateScreenSize();

    this.score = 0;
    this.snake = new Snake();
    this.fruit = new Fruit(this.snake.getTail());

    gestureListener.setSnake(this.snake);
    this.drawer = new Drawer(this.snake, this.context, this.fruit);
    gameLoop = new GameLoop(this, surfaceHolder);
    setFocusable(true);
  }

  public Drawer getDrawer() {
    return drawer;
  }

  private void calculateScreenSize() {
    WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics metrics = new DisplayMetrics();
    windowManager.getDefaultDisplay().getMetrics(metrics);
    CustomProperties customProperties = CustomProperties.get();
    customProperties.setScreenHeight(metrics.heightPixels);
    customProperties.setScreenWidth(metrics.widthPixels);
  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
    gameLoop.startLoop();

  }

  @Override
  public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    // no implemented yet
  }

  @Override
  public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    // no implemented yet
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    //    drawer.drawGrid(canvas);
    drawer.drawFPS(canvas, gameLoop.getAverageFPS());
    drawer.drawUPS(canvas, gameLoop.getAverageUPS());
    drawer.drawSnake(canvas);
    drawer.drawFruit(canvas);
    drawer.drawScore(canvas, score);
//    drawer.drawGameOver(canvas);
  }

  public void update() {
    snake.move();
    gameLogic();
  }

  private void gameLogic() {
    snakeEatFruit();
    snakeEatItself();
  }

  private void snakeEatFruit() {
    if (snake.getxHead() == fruit.getX() && snake.getyHead() == fruit.getY()) {
      snake.eatFruit();
      fruit = new Fruit(snake.getTail());
      drawer.setFruit(fruit);
      score += 1;
    }
  }

  private void snakeEatItself() {
    if (snake.isSnakeEatItself()) {
      gameLoop.setRunning(false);
    }
  }

  public boolean isGameOver() {
    return !gameLoop.isRunning();
  }

}
