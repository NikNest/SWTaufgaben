package actionListeners;


import panelPatterns.ImagePanelPattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowTilesActionListener implements ActionListener {
    private JFrame mainFrame;
    private JFrame showTilesFrame;
    private JPanel tilesContent;
    private ArrayList<ImagePanelPattern> images;


    public ShowTilesActionListener(JFrame mainFrame, JFrame showTilesFrame) {
        this.mainFrame = mainFrame;
        this.showTilesFrame = showTilesFrame;
        images = new ArrayList<>();

        showTilesFrame.setPreferredSize(new Dimension(530, 530));
        showTilesFrame.setResizable(false);
        showTilesFrame.setLocationRelativeTo(mainFrame);
        showTilesFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        tilesContent = new JPanel();
        tilesContent.setLayout(new GridLayout());

        for(int i = 0; i < 9; i++) {
            ImageIcon tile = new ImageIcon();
            images.add(new ImagePanelPattern(null));
        }


        showTilesFrame.getContentPane().add(tilesContent);
        showTilesFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showTilesFrame.setVisible(true);
    }
}
