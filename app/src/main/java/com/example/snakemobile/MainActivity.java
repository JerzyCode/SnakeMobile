package com.example.snakemobile;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.snakemobile.controls.GestureListener;
import com.example.snakemobile.game.Game;

public class MainActivity extends AppCompatActivity {

  private GestureDetector gestureDetector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    gestureDetector = new GestureDetector(this, new GestureListener());

    Window window = getWindow();
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(new Game(this));
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    gestureDetector.onTouchEvent(event);
    return super.onTouchEvent(event);
  }
}