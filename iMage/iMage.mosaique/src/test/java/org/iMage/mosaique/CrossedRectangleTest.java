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
        File resultSquareFile = new File("src/test/resources/Image/resultSquare.png");
        if (resultSquareFile.exists())
            resultSquareFile.delete();
        File resultNotSquareFile = new File("src/test/resources/Image/resultNotSquare.png");
        if (resultNotSquareFile.exists())
            resultNotSquareFile.delete();
    }

    /**
     * test mosaique-"crossedRectangles" with square shape
     * @throws IOException if files couldn't be opened
     */
    @Test
    public void createMosaiqueSquareShape() throws IOException {
        ArrayList<BufferedArtImage> images = new ArrayList<>();
        File folder = new File("src/test/resources/pics");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                images.add(new BufferedArtImage(ImageIO.read(fileEntry)));
            }
        }
        artist = new CrossedRectangleArtist(images, 10, 10);
        File tichyPic = new File("src/test/resources/Image/tichy_in.jpg");
        BufferedImage result = mosaiqueEasel.createMosaique(ImageIO.read(tichyPic), artist);
        File file = new File("src/test/resources/Image/resultSquare.png");
        file.createNewFile();
        ImageIO.write(result, "png", file);
    }

    /**
     * test mosaique-"Crossed Rectangles" with not-square shape
     * @throws IOException if files couldn't be opened
     */
    @Test
    public void createMosaiqueNotSquareShape() throws IOException {
        ArrayList<BufferedArtImage> images = new ArrayList<>();
        File folder = new File("src/test/resources/pics");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                images.add(new BufferedArtImage(ImageIO.read(fileEntry)));
            }
        }
        artist = new CrossedRectangleArtist(images, 50, 10);
        File tichyPic = new File("src/test/resources/Image/tichy_in.jpg");
        BufferedImage result = mosaiqueEasel.createMosaique(ImageIO.read(tichyPic), artist);
        File file = new File("src/test/resources/Image/resultNotSquare.png");
        file.createNewFile();
        ImageIO.write(result, "png", file);
    }

    @After
    public void tearDown() {
        artist = null;
        mosaiqueEasel = null;
    }

}
