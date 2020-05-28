package org.iMage.mosaique.rectangle;

import org.iMage.mosaique.base.BufferedArtImage;
import org.junit.After;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RectangleArtistTest {
    private RectangleArtist artist;

    /**
     * tests getTileForRegion with grayscaled images
     * @throws IOException if test files couldn't be properly opened
     */
    @Test
    public void getTileForRegionTestWithGrayscale() throws IOException {
        File gray0 = new File("src/test/resources/shadesOfGray/black.jpg");
        File gray100 = new File("src/test/resources/shadesOfGray/darkGray.jpg");
        File gray150 = new File("src/test/resources/shadesOfGray/gray.jpg");
        File gray200 = new File("src/test/resources/shadesOfGray/lightGray.jpg");
        File gray255 = new File("src/test/resources/shadesOfGray/white.jpg");

        BufferedArtImage gray0BAI = new BufferedArtImage(ImageIO.read(gray0));
        BufferedArtImage gray100BAI = new BufferedArtImage(ImageIO.read(gray100));
        BufferedArtImage gray150BAI = new BufferedArtImage(ImageIO.read(gray150));
        BufferedArtImage gray200BAI = new BufferedArtImage(ImageIO.read(gray200));
        BufferedArtImage gray255BAI = new BufferedArtImage(ImageIO.read(gray255));

        artist = new RectangleArtist(Arrays.asList(gray0BAI, gray150BAI, gray200BAI, gray255BAI), gray100BAI.getWidth(), gray100BAI.getHeight());

        BufferedArtImage gottenTile = artist.getTileForRegion(gray100BAI);

        RectangleShape shapeGotten = new RectangleShape(gottenTile, gottenTile.getWidth(), gottenTile.getHeight());
        RectangleShape shapeExpected = new RectangleShape(gray150BAI, gray150BAI.getWidth(), gray150BAI.getHeight());

        assertEquals(shapeExpected.getAverageColor(), shapeGotten.getAverageColor());
    }

    /**
     * tests getTileForRegion with color images
     * @throws IOException if test files couldn't be properly opened
     */
    @Test
    public void getTileForRegionTestWithRGBpics() throws IOException {
        File likeGreen = new File("src/test/resources/rgbColors/likeGreen.jpg");
        File green = new File("src/test/resources/rgbColors/green.jpg");
        File red = new File("src/test/resources/rgbColors/red.jpg");
        File blue = new File("src/test/resources/rgbColors/blue.jpg");

        BufferedImage likeGreenBI = ImageIO.read(likeGreen);
        BufferedArtImage likeGreenBAI = new BufferedArtImage(likeGreenBI);
        BufferedImage greenBI = ImageIO.read(green);
        BufferedArtImage greenBAI = new BufferedArtImage(greenBI);
        BufferedImage redBI = ImageIO.read(red);
        BufferedArtImage redBAI = new BufferedArtImage(redBI);
        BufferedImage blueBI = ImageIO.read(blue);
        BufferedArtImage blueBAI = new BufferedArtImage(blueBI);

        artist = new RectangleArtist(Arrays.asList(greenBAI, redBAI, blueBAI), greenBAI.getWidth(), greenBAI.getHeight());
        BufferedArtImage gottenTile = artist.getTileForRegion(likeGreenBAI);

        RectangleShape shapeGotten = new RectangleShape(gottenTile, gottenTile.getWidth(), gottenTile.getHeight());
        RectangleShape shapeExpected = new RectangleShape(greenBAI, greenBAI.getWidth(), greenBAI.getHeight());

        assertEquals(shapeExpected.getAverageColor(), shapeGotten.getAverageColor());
    }

    @After
    public void tearDown() {
        artist = null;
    }
}
