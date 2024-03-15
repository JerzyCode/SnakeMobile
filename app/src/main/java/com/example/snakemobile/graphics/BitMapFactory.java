package com.example.snakemobile.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.snakemobile.R;
import com.example.snakemobile.utils.CustomProperties;

import static com.example.snakemobile.utils.Constants.BOTTOM_PANEL_HEIGHT;
import static com.example.snakemobile.utils.Constants.UNIT_SIZE;

public class BitMapFactory {
  private final Bitmap backgroundImage;
  private final Bitmap headLeftImg;
  private final Bitmap headRightImg;
  private final Bitmap headDownImg;
  private final Bitmap headUpImg;
  private final Bitmap bodyBottomLeftImg;
  private final Bitmap bodyBottomRightImg;
  private final Bitmap bodyHorizontalImg;
  private final Bitmap bodyTopLeftImg;
  private final Bitmap bodyTopRightImg;
  private final Bitmap bodyVerticalImg;
  private final Bitmap tailDownImg;
  private final Bitmap tailLeftImg;
  private final Bitmap tailRightImg;
  private final Bitmap tailUpImg;
  private final CustomProperties customProperties = CustomProperties.get();

  public BitMapFactory(Context context) {
    Resources res = context.getResources();
    this.backgroundImage = createScaledImage(BitmapFactory.decodeResource(res, R.drawable.grass), customProperties.getScreenWidth(),
        customProperties.getScreenHeight() - BOTTOM_PANEL_HEIGHT);
    this.headLeftImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_left));
    this.headRightImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_right));
    this.headDownImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_down));
    this.headUpImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.head_up));
    this.bodyBottomLeftImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.body_bottomleft));
    this.bodyBottomRightImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.body_bottomright));
    this.bodyTopLeftImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.body_topleft));
    this.bodyTopRightImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.body_topright));
    this.bodyHorizontalImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.body_horizontal));
    this.bodyVerticalImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.body_vertical));
    this.tailDownImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.tail_down));
    this.tailLeftImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.tail_left));
    this.tailRightImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.tail_right));
    this.tailUpImg = createScaleImageCellSize(BitmapFactory.decodeResource(res, R.drawable.tail_up));

  }

  private Bitmap createScaledImage(Bitmap bitmap, int width, int height) {
    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, width, height, true);
    bitmap.recycle();
    return scaled;
  }

  private Bitmap createScaleImageCellSize(Bitmap bitmap) {
    return createScaledImage(bitmap, UNIT_SIZE, UNIT_SIZE);
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

  public Bitmap getBodyBottomLeftImg() {
    return bodyBottomLeftImg;
  }

  public Bitmap getBodyBottomRightImg() {
    return bodyBottomRightImg;
  }

  public Bitmap getBodyHorizontalImg() {
    return bodyHorizontalImg;
  }

  public Bitmap getBodyTopLeftImg() {
    return bodyTopLeftImg;
  }

  public Bitmap getBodyTopRightImg() {
    return bodyTopRightImg;
  }

  public Bitmap getBodyVerticalImg() {
    return bodyVerticalImg;
  }

  public Bitmap getTailDownImg() {
    return tailDownImg;
  }

  public Bitmap getTailLeftImg() {
    return tailLeftImg;
  }

  public Bitmap getTailRightImg() {
    return tailRightImg;
  }

  public Bitmap getTailUpImg() {
    return tailUpImg;
  }

  public Bitmap getBackgroundImage() {
    return backgroundImage;
  }

}
