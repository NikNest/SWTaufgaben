package org.iMage.mosaique.crossedRectangle;

import org.iMage.mosaique.AbstractShape;
import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueShape;

import java.util.Iterator;

public class LeftCrosRectShape extends AbstractShape {
    /**
     * Create a new {@link IMosaiqueShape} by image.
     *
     * @param image the image to use
     * @param w     the width
     * @param h
     */
    protected LeftCrosRectShape(BufferedArtImage image, int w, int h) {
        super(image, w, h);
    }

    @Override
    protected int calcAverage() {
        return LeftCrosRectCalculator.getInstance().averageColor(image);
    }

    @Override
    protected void drawShape(BufferedArtImage targetRect, int w, int h) {
        Iterator<Integer> iterX = LeftCrosRectCalculator.getInstance().iteratorX(image);
        int x = iterX.next();
        while(true) {
            if (x < w) {
                Iterator<Integer> iterY = LeftCrosRectCalculator.getInstance().iteratorY(image, x);
                int y = iterY.next();
                while (true) {
                    if (y < h) {
                        targetRect.setRGB(x, y, image.getRGB(x, y));
                    }
                    if (iterY.hasNext())
                        y = iterY.next();
                    else
                        break;
                }
            }
            if (iterX.hasNext())
                x = iterX.next();
            else
                break;
        }
    }
}
