package org.iMage.mosaique;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueArtist;
import org.iMage.mosaique.base.IMosaiqueEasel;
import org.iMage.mosaique.rectangle.RectangleShape;

import javax.swing.*;

/**
 * This class defines an {@link IMosaiqueEasel} which operates on {@link BufferedArtImage
 * BufferedArtImages}.
 *
 * @author Dominik Fuchss
 *
 */
public class MosaiqueEasel implements IMosaiqueEasel<BufferedArtImage> {

    /**
     * create mosaique from artist for the input image
     * @param input image
     * @param artist artist with tiles and tile's shape
     * @return image from tiles that looks similar to input image
     */
  @Override
  public BufferedImage createMosaique(BufferedImage input,
      IMosaiqueArtist<BufferedArtImage> artist) {
      BufferedArtImage artImage = new BufferedArtImage(input);
      int tileWidth = artist.getTileWidth();
      int tileHeight = artist.getTileHeight();
      int inputWidth = input.getWidth();
      int inputHeight = input.getHeight();
      int blankWidth = tileWidth * (int) Math.ceil(inputWidth/(double)tileWidth);
      int blankHeight = tileHeight * (int) Math.ceil(inputHeight/(double)tileHeight);
      BufferedArtImage extendedImg = new BufferedArtImage(blankWidth, blankHeight);
      RectangleShape rectangleShape = new RectangleShape(artImage, input.getWidth(), input.getHeight());
      rectangleShape.drawMe(extendedImg);

      for (int wi = 0; wi < blankWidth/tileWidth; wi++) {
        for (int hi = 0; hi < blankHeight/tileHeight; hi++) {
          BufferedArtImage roi = extendedImg.getSubimage(wi*tileWidth, hi*tileHeight, tileWidth, tileHeight);
          BufferedArtImage tile = artist.getTileForRegion(roi);
          extendedImg.setSubimage(wi*tileWidth, hi*tileHeight, tile);
        }
      }
      artImage = extendedImg.getSubimage(0,0, inputWidth, inputHeight);
      return artImage.toBufferedImage();
  }

}
