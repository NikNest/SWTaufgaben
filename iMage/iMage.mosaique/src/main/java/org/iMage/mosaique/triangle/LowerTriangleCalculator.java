package org.iMage.mosaique.triangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class LowerTriangleCalculator extends AbstractCalculator {
    private static final LowerTriangleCalculator LOWER_TRIANGLE_CALCULATOR = new LowerTriangleCalculator();

    private LowerTriangleCalculator() {
        throw new IllegalAccessError();
    }

    public static LowerTriangleCalculator getInstance() {
        return LOWER_TRIANGLE_CALCULATOR;
    }

    @Override
    public Iterator<Integer> heightIterator(BufferedImage region, int x) {
        float m = (1F * region.getHeight()) / region.getWidth();
        float yBound = Math.max((x + 1) * m, 0);

        int fromY = (int)yBound;
        int toY = region.getHeight();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = fromY; i < toY; i++) {
            list.add(i);
        }
        return list.iterator();
    }
}
