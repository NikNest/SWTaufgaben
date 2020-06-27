package org.iMage.mosaique;

import java.util.Iterator;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractCalculator {


    public abstract Iterator<Integer> heightIterator(BufferedImage region, int x);

    public final int averageColor(BufferedImage region) {
        long r = 0;
        long g = 0;
        long b = 0;
        long a = 0;
        int ctr = 0;

        for (int x = 0; x < region.getWidth(); x++) {
                Iterator<Integer> iterator = this.heightIterator(region, x);
                while(iterator.hasNext()) {
                    int y = iterator.next();
                    int col = region.getRGB(x, y);
                    Color c = new Color(col, true);
                    r += c.getRed();
                    g += c.getGreen();
                    b += c.getBlue();
                    a += c.getAlpha();
                    ctr++;
                }
        }

        return new Color((int) (r / ctr), (int) (g / ctr), (int) (b / ctr), (int) (a / ctr)).getRGB();
    }
}
