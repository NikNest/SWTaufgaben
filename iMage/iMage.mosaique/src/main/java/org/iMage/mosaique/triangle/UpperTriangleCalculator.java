package org.iMage.mosaique.triangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;

public class UpperTriangleCalculator extends AbstractCalculator {
    private static final UpperTriangleCalculator UPPER_TRIANGLE_CALCULATOR = new UpperTriangleCalculator();

    private UpperTriangleCalculator() {
        throw new IllegalAccessError();
    }

    public static UpperTriangleCalculator getInstance() {
        return UPPER_TRIANGLE_CALCULATOR;
    }

    @Override
    public float getYFromBound(BufferedImage region, int x) {
        return 0;
    }

    @Override
    public float getYToBound(BufferedImage region, int x) {
        float m = (1F * region.getHeight()) / region.getWidth();
        float yBound = Math.min((x + 1) * m, region.getHeight());
        return yBound;
    }
}
