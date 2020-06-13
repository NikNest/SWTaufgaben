package actionListeners;


import frames.ShowTilesFrame;
import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.base.IMosaiqueArtist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShowTilesActionListener implements ActionListener {
    private JFrame mainFrame;
    private ShowTilesFrame showTilesFrame = null;
    private LoadTilesActionListener loadTilesActionListener;

    public ShowTilesActionListener(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void drawImages() {
        if (showTilesFrame != null) {
            showTilesFrame.dispose();
        }

        showTilesFrame = new ShowTilesFrame("Thumbnail", mainFrame,
                (ArrayList<BufferedImage>) loadTilesActionListener.getRectangleArtist().getThumbnails());
    }

    public void setLoadTilesActionListener(LoadTilesActionListener loadTilesActionListener) {
        this.loadTilesActionListener = loadTilesActionListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawImages();
        showTilesFrame.setVisible(true);
    }
}
