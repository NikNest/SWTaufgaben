package org.iMage.mosaique;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.crossedRectangle.*;
import org.iMage.mosaique.rectangle.RectangleArtist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CrossedRectangleTest {
    private CrossedRectangleArtist artist;
    private MosaiqueEasel mosaiqueEasel;

    @Before
    public void setUp() {
        mosaiqueEasel = new MosaiqueEasel();
        File resultFile = new File("src/test/resources/Image/result.png");
        if (resultFile.exists())
            resultFile.delete();
    }

    @Test
    public void createMosaiqueSquareShape() throws IOException {
        ArrayList<BufferedArtImage> images = new ArrayList<>();
        File folder = new File("src/test/resources/pics");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                images.add(new BufferedArtImage(ImageIO.read(fileEntry)));
            }
        }
        System.out.println("pics choosen: " + images.size());
        artist = new CrossedRectangleArtist(images, 15, 15);
        System.out.println("cross rect artist created");
        File tichyPic = new File("src/test/resources/Image/tichy_in.jpg");
        BufferedImage result = mosaiqueEasel.createMosaique(ImageIO.read(tichyPic), artist);
        System.out.println("result mosaique created");
        File file = new File("src/test/resources/Image/result.png");
        file.createNewFile();
        ImageIO.write(result, "png", file);

    }

    @After
    public void tearDown() {
        artist = null;
        mosaiqueEasel = null;
    }

}
