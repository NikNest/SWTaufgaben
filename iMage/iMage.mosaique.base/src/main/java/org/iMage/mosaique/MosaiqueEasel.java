package org.iMage.mosaique;

import java.awt.image.BufferedImage;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueArtist;
import org.iMage.mosaique.base.IMosaiqueEasel;
import org.iMage.mosaique.rectangle.RectangleShape;

/**
 * This class defines an {@link IMosaiqueEasel} which operates on {@link BufferedArtImage
 * BufferedArtImages}.
 *
 * @author Dominik Fuchss
 *
 */
public class MosaiqueEasel implements IMosaiqueEasel<BufferedArtImage> {

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
      for (int wi = 0; wi < inputWidth/tileWidth; wi++) {
        for (int hi = 0; hi < inputHeight/tileHeight; hi++) {
          BufferedArtImage roi = extendedImg.getSubimage(wi*tileWidth, hi*tileHeight, tileWidth, tileHeight);
          BufferedArtImage tile = artist.getTileForRegion(roi);
          extendedImg.setSubimage(wi*tileWidth, hi*tileHeight, tile);
        }
      }
      artImage = extendedImg.getSubimage(0,0, inputWidth, inputHeight);
      return artImage.toBufferedImage();
//    throw new RuntimeException("not implemented");
  }

}
