package com.example.snakemobile.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.snakemobile.R;
import com.example.snakemobile.utils.CustomProperties;

import static com.example.snakemobile.utils.Constants.BOTTOM_PANEL_HEIGHT;

public class BitMapFactory {
  private final Bitmap backgroundImage;
  private final Bitmap headLeftImg;
  private final Bitmap headRightImg;
  private final Bitmap headDownImg;
  private final Bitmap headUpImg;
  private final CustomProperties customProperties = CustomProperties.get();

  public BitMapFactory(Context context) {
    Resources res = context.getResources();
    this.backgroundImage = createScaledImage(BitmapFactory.decodeResource(res, R.drawable.grass),
        customProperties.getScreenWidth(), customProperties.getScreenHeight() - BOTTOM_PANEL_HEIGHT);
    this.headLeftImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_left));
    this.headRightImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_right));
    this.headDownImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_down));
    this.headUpImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_up));
  }

  private Bitmap createScaledImage(Bitmap bitmap, int width, int height) {
    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, width, height, true);
    bitmap.recycle();
    return scaled;
  }

  private Bitmap createScaleImageCellSize(Bitmap bitmap) {
    return createScaledImage(bitmap, customProperties.getCellWidth(), customProperties.getCellHeight());
  }

  public Bitmap getHeadLeftImg() {
    return headLeftImg;
  }

  public Bitmap getHeadRightImg() {
    return headRightImg;
  }

  public Bitmap getHeadDownImg() {
    return headDownImg;
  }

  public Bitmap getHeadUpImg() {
    return headUpImg;
  }

  public Bitmap getBackgroundImage() {
    return backgroundImage;
  }

}
