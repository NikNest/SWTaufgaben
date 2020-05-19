package org.iMage.mosaique.rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.*;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueArtist;




/**
 * This class represents an {@link IMosaiqueArtist} who uses rectangles as tiles.
 *
 * @author Dominik Fuchss
 *
 */
public class RectangleArtist implements IMosaiqueArtist<BufferedArtImage> {
  private final ArrayList<RectangleShape> images;
  private final int tileWidth;
  private final int tileHeight;
  /**
   * Create an artist who works with {@link RectangleShape RectangleShapes}
   *
   * @param images
   *          the images for the tiles
   * @param tileWidth
   *          the desired width of the tiles
   * @param tileHeight
   *          the desired height of the tiles
   * @throws IllegalArgumentException
   *           iff tileWidth or tileHeight &lt;= 0, or images is empty.
   */
  public RectangleArtist(Collection<BufferedArtImage> images, int tileWidth, int tileHeight) {
    ArrayList<RectangleShape> temp = new ArrayList<>();
    images.stream().forEach(image -> {
      temp.add(new RectangleShape(image, tileWidth, tileHeight));
    });
    this.images = temp;
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
  }

  /**
   * getter for all tiles
   * @return list of all tiles
   */
  @Override
  public List<BufferedImage> getThumbnails() {
    ArrayList<BufferedImage> temp = new ArrayList<>();
    images.stream().forEach(img -> {
      temp.add(img.getThumbnail());
    });
    return temp;
  }

  /**
   * get the tile for the region with the best average color distance
   * @param region image as region
   * @return best tile for the region
   */
  @Override
  public BufferedArtImage getTileForRegion(BufferedArtImage region) {
    Color regionColor = new Color(new RectangleShape(region, region.getWidth(), region.getHeight()).getAverageColor(), true);
    int bestDist = 10000;
    Iterator<RectangleShape> iter = images.iterator();
    RectangleShape bestPic = null;
    while (iter.hasNext()) {
      RectangleShape temp = iter.next();
      Color tempC = new Color(temp.getAverageColor(), true);
      int distR = (int) Math.pow(regionColor.getRed() - tempC.getRed(), 2);
      int distG = (int) Math.pow(regionColor.getGreen() - tempC.getGreen(), 2);
      int distB = (int) Math.pow(regionColor.getBlue() - tempC.getBlue(), 2);
      int distA = (int) Math.pow(regionColor.getAlpha() - tempC.getAlpha(), 2);
      int dist = (int) Math.sqrt(distA + distB + distG + distR);
      if (dist < bestDist) {
        bestPic = temp;
        bestDist = dist;
      }
    }
    return new BufferedArtImage(bestPic.getThumbnail());
  }

  /**
   * getter for tile width
   * @return width in px
   */
  @Override
  public int getTileWidth() {
    return this.tileWidth;
//    throw new RuntimeException("not implemented");
  }

  /**
   * getter for teil height
   * @return height in px
   */
  @Override
  public int getTileHeight() {
    return this.tileHeight;
//    throw new RuntimeException("not implemented");
  }
}
