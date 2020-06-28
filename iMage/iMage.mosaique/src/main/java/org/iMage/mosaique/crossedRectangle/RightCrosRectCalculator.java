package org.iMage.mosaique.crossedRectangle;

import org.iMage.mosaique.AbstractCalculator;
import org.iMage.mosaique.rectangle.RectangleCalculator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class RightCrosRectCalculator extends AbstractCalculator {
    private static final RightCrosRectCalculator RIGHT_CROS_RECT_CALCULATOR = new RightCrosRectCalculator();

    private RightCrosRectCalculator() {

    }

    public static RightCrosRectCalculator getInstance() {
        return RIGHT_CROS_RECT_CALCULATOR;
    }

    @Override
    public Iterator<Integer> iteratorX(BufferedImage region) {
        int fromX = (int) Math.ceil((float)region.getWidth() / 2);
        int toX = region.getWidth();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromX; i < toX; i++) {
            list.add(i);
        }
        return list.iterator();
    }

    @Override
    public Iterator<Integer> iteratorY(BufferedImage region, int x) {
        int fromY = (int) (region.getHeight() - 1 - x * ((double)region.getHeight() / region.getWidth()));
        int toY = region.getHeight() - fromY;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromY; i < toY; i++) {
            list.add(i);
        }
        return list.iterator();
    }
}
