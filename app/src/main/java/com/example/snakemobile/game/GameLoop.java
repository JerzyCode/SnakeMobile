package com.example.snakemobile.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import static com.example.snakemobile.utils.Constants.FPS;
import static com.example.snakemobile.utils.Constants.UPS;

public class GameLoop extends Thread {

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
    double timePerFrame = 1000000000.0 / FPS;
    double timePerUpdate = 1000000000.0 / UPS;
    long previousTime = System.nanoTime();
    long elapsedTime;
    int frames = 0;
    int updates = 0;
    long lastCheck = System.currentTimeMillis();
    double deltaUpdates = 0;
    double deltaFrames = 0;

    while (isRunning) {
      long currentTime = System.nanoTime();

      deltaUpdates += (currentTime - previousTime) / timePerUpdate;
      deltaFrames += (currentTime - previousTime) / timePerFrame;
      previousTime = currentTime;

      if (deltaUpdates >= 1) {
        game.update();
        updates += 1;
        deltaUpdates -= 1;
      }
      Canvas canvas = null;
      if (deltaFrames >= 1) {
        try {
          canvas = surfaceHolder.lockCanvas();
          synchronized (surfaceHolder) {
            game.draw(canvas);
          }
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        finally {
          if (canvas != null) {
            try {
              surfaceHolder.unlockCanvasAndPost(canvas);
            }
            catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
        frames += 1;
        deltaFrames -= 1;
      }

      elapsedTime = System.currentTimeMillis() - lastCheck;

      if (elapsedTime >= 1000) {
        lastCheck = System.currentTimeMillis();
        averageUPS = updates / (1E-3 * elapsedTime);
        averageFPS = frames / (1E-3 * elapsedTime);
        frames = 0;
        updates = 0;
      }
    }
  }
}

