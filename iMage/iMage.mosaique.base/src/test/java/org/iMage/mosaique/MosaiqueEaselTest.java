package org.iMage.mosaique;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.rectangle.RectangleArtist;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MosaiqueEaselTest {
    private RectangleArtist artist;
    private MosaiqueEasel mosaique;

    @Before
    public void setUp() {
        mosaique = new MosaiqueEasel();
        File file = new File("src/test/resources/picToRem/result.png");
        if (file.exists())
            file.delete();
    }

    @Ignore
    @Test
    public void createMosaiqueTest() throws IOException {
        ArrayList<BufferedArtImage> images = new ArrayList<>();
        File folder = new File("src/test/resources/picsToRem");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                images.add(new BufferedArtImage(ImageIO.read(fileEntry)));
            }
        }

        artist = new RectangleArtist(images, 6, 6);
        File tichyPic = new File("src/test/resources/picToRem/tichy_in.jpg");
        BufferedImage result = mosaique.createMosaique(ImageIO.read(tichyPic), artist);
        File file = new File("src/test/resources/picToRem/result.png");
        file.createNewFile();
        ImageIO.write(result, "png", file);


    }

    @After
    public void tearDown() {
        artist = null;
        mosaique = null;
    }
}
