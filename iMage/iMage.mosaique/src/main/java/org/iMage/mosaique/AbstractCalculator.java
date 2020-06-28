package org.iMage.mosaique;

import java.util.Iterator;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractCalculator {

    public abstract Iterator<Integer> iteratorX(BufferedImage region);
    public abstract Iterator<Integer> iteratorY(BufferedImage region, int x);


    public final int averageColor(BufferedImage region) {
        long r = 0;
        long g = 0;
        long b = 0;
        long a = 0;
        int ctr = 0;

        Iterator<Integer> iteratorX = this.iteratorX(region);
        while(iteratorX.hasNext()) {
            int x = iteratorX.next();
            Iterator<Integer> iteratorY = this.iteratorY(region, x);
            while(iteratorY.hasNext()) {
                int y = iteratorY.next();
//                System.out.println("-------");
//                System.out.println("region " + region.getWidth() + " " + region.getHeight());
//                System.out.println("!!! " + x + " " + y);
                int col = region.getRGB(x, y);
//                System.out.println("***");
                Color c = new Color(col, true);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
                a += c.getAlpha();
                ctr++;
            }
//            System.out.println("============");
        }

        return new Color((int) (r / ctr), (int) (g / ctr), (int) (b / ctr), (int) (a / ctr)).getRGB();
    }
}
