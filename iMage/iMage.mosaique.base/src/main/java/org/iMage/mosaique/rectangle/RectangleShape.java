package org.iMage.mosaique.rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueShape;
import org.iMage.mosaique.base.ImageUtils;

/**
 * This class represents a rectangle as {@link IMosaiqueShape} based on an {@link BufferedArtImage}.
 *
 * @author Dominik Fuchss
 *
 */
public class RectangleShape implements IMosaiqueShape<BufferedArtImage> {
  private final BufferedArtImage image;
  /**
   * Create a new {@link IMosaiqueShape}.
   *
   * @param image
   *          the image to use
   * @param w
   *          the width
   * @param h
   *          the height
   */
  public RectangleShape(BufferedArtImage image, int w, int h) {
    image = new BufferedArtImage(ImageUtils.scaleWidth(image.toBufferedImage(), w));
    image = new BufferedArtImage(ImageUtils.scaleHeight(image.toBufferedImage(), h));
    this.image = image;
    //    throw new RuntimeException("not implemented");
  }

  @Override
  public int getAverageColor() {
      int r = 0;
      int g = 0;
      int b = 0;
      int a = 0;
      int w = image.getWidth();
      int h = image.getHeight();
      for (int i = 0; i < w; i++) {
        for (int q = 0; q < h; q++) {
          Color c = new Color(image.toBufferedImage().getRGB(i, q), true);
          r += c.getRed();
          g += c.getGreen();
          b += c.getBlue();
          a += c.getAlpha();
        }
      }
      int pixAmount = w * h;
      r /= pixAmount;
      g /= pixAmount;
      b /= pixAmount;
      a /= pixAmount;
      return (new Color(a, r, g, b)).getRGB();
    //    throw new RuntimeException("not implemented");
  }

  @Override
  public BufferedImage getThumbnail() {
      return this.image.toBufferedImage();
//      throw new RuntimeException("not implemented");
  }

  @Override
  public void drawMe(BufferedArtImage targetRect) {
    int w = Math.min(targetRect.getWidth(), image.getWidth());
    int h = Math.min(targetRect.getHeight(), image.getHeight());
    image.setSubimage(w, h, targetRect);
//    throw new RuntimeException("not implemented");
  }

  @Override
  public int getHeight() {
    return image.getHeight();
//    throw new RuntimeException("not implemented");
  }

  @Override
  public int getWidth() {
    return image.getWidth();
//    throw new RuntimeException("not implemented");
  }
}