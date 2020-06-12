package actionListeners;


import panelPatterns.ImagePanelPattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        tilesContent.setLayout(new GridLayout(15, 7, 1,1));

        for (int i = 0; i < 100; i++) {
            ImageIcon tile = new ImageIcon("iMage.iTiler/src/main/resources/tiles/likeGreen" + Integer.toString(i) + ".jpg");
            images.add(new ImagePanelPattern(tile));
        }

        for (int i = 0; i < 100; i++) {
            tilesContent.add(images.get(i));
        }
        JScrollPane scrollPane = new JScrollPane(tilesContent);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        showTilesFrame.getContentPane().add(scrollPane);
        showTilesFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showTilesFrame.setVisible(true);
    }
}
