package org.iMage.mosaique.crossedRectangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class UpCrosRectCalculator extends AbstractCalculator {
    private static final UpCrosRectCalculator UP_CROS_RECT_CALCULATOR = new UpCrosRectCalculator();

    private UpCrosRectCalculator() {}

    public static UpCrosRectCalculator getInstance() {
        return UP_CROS_RECT_CALCULATOR;
    }

    @Override
    public Iterator<Integer> iteratorX(BufferedImage region) {
        int fromX = 0;
        int toX = region.getWidth();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromX; i < toX; i++) {
            list.add(i);
        }
        return list.iterator();
    }

    @Override
    public Iterator<Integer> iteratorY(BufferedImage region, int x) {
        int fromY = 0;
        int toY = (int) Math.ceil((double) region.getHeight() / (double)region.getWidth() * (double)(x + 1)) - 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromY; i <= toY; i++) {
            list.add(i);
        }
        return list.iterator();
    }
}
