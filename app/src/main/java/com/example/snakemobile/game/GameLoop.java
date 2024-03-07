package com.example.snakemobile.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
  private static final double MAX_UPS = 30.0;
  private static final double UPS_PERIOD = 1E+3 / MAX_UPS;

  private final SurfaceHolder surfaceHolder;
  private final Game game;

  private boolean isRunning = false;
  private double averageUPS;
  private double averageFPS;

  public GameLoop(Game game, SurfaceHolder surfaceHolder) {
    this.game = game;
    this.surfaceHolder = surfaceHolder;
  }

  public double getAverageUPS() {
    return averageUPS;
  }

  public double getAverageFPS() {
    return averageFPS;
  }

  public void startLoop() {
    System.out.println("startLoop()");
    isRunning = true;
    start();
  }

  @Override
  public void run() {
    super.run();

    int updateCount = 0;
    int frameCount = 0;

    long startTime;
    long elapsedTime;
    long sleepTime;

    Canvas canvas = null;
    startTime = System.currentTimeMillis();

    while (isRunning) {
      try {
        canvas = surfaceHolder.lockCanvas();
        synchronized (surfaceHolder) {
          game.update();
          updateCount++;

          game.draw(canvas);
        }
      }
      catch (IllegalArgumentException ex) {
        ex.printStackTrace();
      }
      finally {
        if (canvas != null) {
          try {
            surfaceHolder.unlockCanvasAndPost(canvas);
            frameCount++;
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }

      elapsedTime = System.currentTimeMillis() - startTime;
      sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
      if (sleepTime > 0) {
        try {
          sleep(sleepTime);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }

      }

      while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
        game.update();
        updateCount++;
        elapsedTime = System.currentTimeMillis() - startTime;
        sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
      }

      elapsedTime = System.currentTimeMillis() - startTime;
      if (elapsedTime >= 1000) {
        averageUPS = updateCount / (1E-3 * elapsedTime);
        averageFPS = frameCount / (1E-3 * elapsedTime);
        updateCount = 0;
        frameCount = 0;
        startTime = System.currentTimeMillis();
      }
    }
  }

}
