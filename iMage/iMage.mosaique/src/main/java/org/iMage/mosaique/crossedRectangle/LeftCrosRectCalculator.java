package org.iMage.mosaique.crossedRectangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class LeftCrosRectCalculator extends AbstractCalculator {
    private static final LeftCrosRectCalculator LEFT_CROS_RECT_CALCULATOR = new LeftCrosRectCalculator();

    private LeftCrosRectCalculator() {

    }

    public static LeftCrosRectCalculator getInstance() {
        return LEFT_CROS_RECT_CALCULATOR;
    }

    @Override
    public Iterator<Integer> iteratorX(BufferedImage region) {
        int fromX = 0;
        int toX = (int) Math.floor((double) region.getWidth() / 2);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromX; i < toX; i++) {
            list.add(i);
        }
        return list.iterator();
    }

    @Override
    public Iterator<Integer> iteratorY(BufferedImage region, int x) {
        int fromY = (int) (x * ((double)region.getHeight() / region.getWidth()));
        int toY = region.getHeight() - 1 - fromY;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromY; i < toY; i++) {
            list.add(i);
        }
        return list.iterator();
    }
}
