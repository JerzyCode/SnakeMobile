package com.example.snakemobile.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.snakemobile.R;
import com.example.snakemobile.utils.CustomProperties;

import static com.example.snakemobile.utils.Constants.BOTTOM_PANEL_HEIGHT;

public class BitMapFactory {
  private final Bitmap backgroundImage;
  private final CustomProperties customProperties = CustomProperties.get();

  public BitMapFactory(Context context) {
    this.backgroundImage = createScaledImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.grass));
  }

  private Bitmap createScaledImage(Bitmap bitmap) {
    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, customProperties.getScreenWidth(), customProperties.getScreenHeight() - BOTTOM_PANEL_HEIGHT, true);
    bitmap.recycle();
    return scaled;
  }

  public Bitmap getBackgroundImage() {
    return backgroundImage;
  }

}
