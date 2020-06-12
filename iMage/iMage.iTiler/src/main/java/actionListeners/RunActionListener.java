package actionListeners;

import org.iMage.mosaique.MosaiqueEasel;
import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueArtist;
import org.iMage.mosaique.rectangle.RectangleArtist;
import org.iMage.mosaique.triangle.TriangleArtist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class RunActionListener implements ActionListener {
    private LoadInputActionListener loadInputActionListener;
    private LoadTilesActionListener loadTilesActionListener;
    private TilesAmountInputActionListener tilesAmountInputActionListener;
    private JFrame mainFrame;
    private JPanel outputPanel;
    private ImageIcon resultImage;
//    private RectangleArtist rectangleArtist;
//    private TriangleArtist triangleArtist;
    private IMosaiqueArtist<BufferedArtImage> artist;
    private MosaiqueEasel mosaique;
    private boolean useRectangle = true;

    public RunActionListener(JFrame mainFrame, JPanel outputPanel,
                             LoadInputActionListener loadInputActionListener,
                             LoadTilesActionListener loadTilesActionListener,
                             TilesAmountInputActionListener tilesAmountInputActionListener) {
        this.loadInputActionListener = loadInputActionListener;
        this.mainFrame = mainFrame;
        this.outputPanel = outputPanel;
        this.loadInputActionListener = loadInputActionListener;
        this.loadTilesActionListener = loadTilesActionListener;
        this.tilesAmountInputActionListener = tilesAmountInputActionListener;
    }

    private boolean areConditionsObserved(int tileAmountWidth, int tileAmountHeight, int tilesAmount) {
        return true;
    }

    private void setResultImage() {
        outputPanel.removeAll();
        outputPanel.add(new JLabel(resultImage));
        outputPanel.revalidate();
        outputPanel.repaint();
    }

    private void runMosaique(ArrayList<BufferedImage> tiles, int tileAmountWidth, int tileAmountHeight, BufferedImage inputBImage) {
        mosaique = new MosaiqueEasel();
        ArrayList<BufferedArtImage> images = new ArrayList<>();
        tiles.stream().forEach((tile) -> {
            images.add(new BufferedArtImage(tile));
        });
        System.out.println(useRectangle);
        artist = useRectangle ? new RectangleArtist(images, tileAmountWidth, tileAmountHeight) :
            new TriangleArtist(images, tileAmountWidth, tileAmountHeight);
        BufferedImage result = mosaique.createMosaique(inputBImage, artist);
        resultImage = new ImageIcon(result);
    }

    public void setUseRectangle(boolean useRectangle) {
        this.useRectangle = useRectangle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<BufferedImage> tiles = new ArrayList<>();
        loadTilesActionListener.getImages().stream().forEach((tile) -> {
            BufferedImage bi = new BufferedImage(
                    tile.getIconWidth(),
                    tile.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            tile.paintIcon(null, g, 0, 0);
            g.dispose();
            tiles.add(bi);
        });
        ImageIcon inputImage = loadInputActionListener.getImage();
        BufferedImage inputBImage = new BufferedImage(
                inputImage.getIconWidth(),
                inputImage.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = inputBImage.createGraphics();
        inputImage.paintIcon(null, g, 0, 0);
        g.dispose();

        int tileAmountWidth = tilesAmountInputActionListener.getWidth();
        int tileAmountHeight = tilesAmountInputActionListener.getHeight();
        int tilesAmount = tiles.size();
        if(areConditionsObserved(tileAmountWidth, tileAmountHeight, tilesAmount)) {
            runMosaique(tiles, tileAmountWidth, tileAmountHeight, inputBImage);
            setResultImage();
        }
    }
}
