package org.iMage.mosaique.rectangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Helper class for the {@link RectangleArtist} and {@link RectangleShape}.
 *
 * @author Dominik Fuchss
 *
 */
public final class RectangleCalculator extends AbstractCalculator {

  private static final RectangleCalculator RECTANGLE_CALCULATOR = new RectangleCalculator();

  private RectangleCalculator() {
    throw new IllegalAccessError();
  }

  public static RectangleCalculator getInstance() {
    return RECTANGLE_CALCULATOR;
  }

  @Override
  public float getYFromBound(BufferedImage region, int x) {
    return 0;
  }

  @Override
  public float getYToBound(BufferedImage region, int x) {
    return region.getHeight();
  }
}
