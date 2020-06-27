package org.iMage.mosaique.triangle;

import org.iMage.mosaique.AbstractCalculator;

import java.awt.image.BufferedImage;

public class LowerTriangleCalculator extends AbstractCalculator {
    private static final LowerTriangleCalculator LOWER_TRIANGLE_CALCULATOR = new LowerTriangleCalculator();

    private LowerTriangleCalculator() {
        throw new IllegalAccessError();
    }

    public static LowerTriangleCalculator getInstance() {
        return LOWER_TRIANGLE_CALCULATOR;
    }

    @Override
    public float getYFromBound(BufferedImage region, int x) {
        float m = (1F * region.getHeight()) / region.getWidth();
        float yBound = Math.max((x + 1) * m, 0);
        return yBound;
    }

    @Override
    public float getYToBound(BufferedImage region, int x) {
        return region.getHeight();
    }

}
