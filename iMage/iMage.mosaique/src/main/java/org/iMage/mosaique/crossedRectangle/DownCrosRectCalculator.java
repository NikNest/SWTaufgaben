package org.iMage.mosaique.crossedRectangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class DownCrosRectCalculator extends AbstractCalculator {
    private static final DownCrosRectCalculator DOWN_CROS_REC_CALCULATOR = new DownCrosRectCalculator();

    private DownCrosRectCalculator() {

    }

    public static DownCrosRectCalculator getInstance() {
        return DOWN_CROS_REC_CALCULATOR;
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
        int fromY = region.getHeight() - 1;
        int toY;
        if (x < (double)region.getWidth()/2) {
            toY = region.getHeight() - 1 - (int) Math.ceil((double)region.getHeight() / region.getWidth() * x);
        } else {
            toY = (int) Math.ceil((double)region.getHeight() / region.getWidth() * (x + 1)) - 1;
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = fromY; i >= toY; i--) {
            list.add(i);
        }

        return list.iterator();
    }
}
