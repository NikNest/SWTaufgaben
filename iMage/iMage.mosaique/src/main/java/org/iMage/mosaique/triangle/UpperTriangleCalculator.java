package org.iMage.mosaique.triangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class UpperTriangleCalculator extends AbstractCalculator {
    private static final UpperTriangleCalculator UPPER_TRIANGLE_CALCULATOR = new UpperTriangleCalculator();

    private UpperTriangleCalculator() {
        throw new IllegalAccessError();
    }

    public static UpperTriangleCalculator getInstance() {
        return UPPER_TRIANGLE_CALCULATOR;
    }

    @Override
    public Iterator<Integer> heightIterator(BufferedImage region, int x) {
        float m = (1F * region.getHeight()) / region.getWidth();
        float yBound = Math.min((x + 1) * m, region.getHeight());

        int fromY = (int)yBound;
        int toY = region.getHeight();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromY; i < toY; i++) {
            list.add(i);
        }
        return list.iterator();
    }
}
