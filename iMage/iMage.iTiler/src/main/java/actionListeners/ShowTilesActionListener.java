package actionListeners;


import frames.ShowTilesFrame;
import panelPatterns.ImagePanelPattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ShowTilesActionListener implements ActionListener {
    private JFrame mainFrame;
    private ShowTilesFrame showTilesFrame = null;


    private LoadTilesActionListener loadTilesActionListener;

    public ShowTilesActionListener(JFrame mainFrame, LoadTilesActionListener loadTilesActionListener) {
        this.loadTilesActionListener = loadTilesActionListener;
        this.mainFrame = mainFrame;
    }

    private void drawImages() {
        if (showTilesFrame != null) {
            showTilesFrame.dispose();
        }
        showTilesFrame = new ShowTilesFrame("Thumbnail", mainFrame, loadTilesActionListener.getImages());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawImages();
        showTilesFrame.setVisible(true);
    }
}
