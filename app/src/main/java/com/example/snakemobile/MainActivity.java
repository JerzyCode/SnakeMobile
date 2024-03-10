package com.example.snakemobile;

import android.os.Bundle;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.snakemobile.controls.GestureListener;
import com.example.snakemobile.game.Game;

public class MainActivity extends AppCompatActivity {

  private GestureDetector gestureDetector;
  private boolean isTouchable;
  private Game game;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Window window = getWindow();
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    isTouchable = false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (!isTouchable) {
      return true;
    }
    if (game != null && game.isGameOver()) {
      setContentView(R.layout.activity_main);
      return true;
    }
    gestureDetector.onTouchEvent(event);
    return super.onTouchEvent(event);
  }

  public void startGame(View view) {
    isTouchable = true;
    System.out.println("StartGame");
    GestureListener gestureListener = new GestureListener();
    gestureDetector = new GestureDetector(this, gestureListener);
    game = new Game(this, gestureListener);
    setContentView(game);
  }
}