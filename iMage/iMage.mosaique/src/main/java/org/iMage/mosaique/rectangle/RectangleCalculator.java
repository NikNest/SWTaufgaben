package org.iMage.mosaique.rectangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

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
  public Iterator<Integer> heightIterator(BufferedImage region, int x) {
    int fromY = 0;
    int toY = region.getHeight();
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = fromY; i < toY; i++) {
      list.add(i);
    }
    return list.iterator();
  }
}
