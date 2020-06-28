package org.iMage.mosaique.crossedRectangle;

import org.iMage.mosaique.AbstractArtist;
import org.iMage.mosaique.AbstractShape;
import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueArtist;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CrossedRectangleArtist extends AbstractArtist implements IMosaiqueArtist<BufferedArtImage> {
    private List<AbstractShape> up;
    private List<AbstractShape> down;
    private List<AbstractShape> left;
    private List<AbstractShape> right;

    /**
     * Create an artist who works with tiles of specified width and height
     *
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException iff tileWidth or tileHeight &lt;= 0, or images is empty.
     */
    public CrossedRectangleArtist(Collection<BufferedArtImage> images, int tileWidth, int tileHeight) {
        super(tileWidth, tileHeight);

        this.up = new ArrayList<>();
        this.down = new ArrayList<>();
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();

        for (var image : images) {
            up.add(new UpCrosRectShape(image, tileWidth, tileHeight));
            down.add(new DownCrosRectShape(image, tileWidth, tileHeight));
            left.add(new LeftCrosRectShape(image, tileWidth, tileHeight));
            right.add(new RightCrosRectShape(image, tileWidth, tileHeight));
        }
    }

    @Override
    protected void drawTileForRegion(BufferedImage region, BufferedArtImage target) {
        int averageDown = DownCrosRectCalculator.getInstance().averageColor(region);
        int averageUp = UpCrosRectCalculator.getInstance().averageColor(region);
        int averageLeft = LeftCrosRectCalculator.getInstance().averageColor(region);
        int averageRight = RightCrosRectCalculator.getInstance().averageColor(region);
        AbstractShape upImage = findNearest(averageUp, up);
        AbstractShape downImage = findNearest(averageDown, down);
        AbstractShape leftImage = findNearest(averageLeft, left);
        AbstractShape rightImage = findNearest(averageRight, right);
        upImage.drawMe(target);
        downImage.drawMe(target);
        leftImage.drawMe(target);
        rightImage.drawMe(target);
    }

    @Override
    public List<BufferedImage> getThumbnails() {
        List<AbstractShape> shapes = new ArrayList<>();
        shapes.addAll(up);
        shapes.addAll(down);
        shapes.addAll(right);
        shapes.addAll(left);
        return shapes.stream().map(AbstractShape::getThumbnail).collect(Collectors.toList());
    }
}
