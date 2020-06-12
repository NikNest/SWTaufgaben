package frames;

import panelPatterns.ImagePanelPattern;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowTilesFrame extends JFrame {
    private ArrayList<ImagePanelPattern> images;
    private JPanel tilesContent;

    public ShowTilesFrame(String text, JFrame mainFrame, ArrayList<ImageIcon> tiles) {
        super(text);
        setPreferredSize(new Dimension(530, 530));
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        images = new ArrayList<>();
        if (tiles != null)
            for (ImageIcon tile : tiles) {
                images.add(new ImagePanelPattern(tile));
            }
        tilesContent = new JPanel();
        for (ImagePanelPattern tilePanel : images) {
            tilesContent.add(tilePanel);
        }
        int rows = (int) Math.ceil(images.size() / 7.);
        if (images.size() < 49) {
            int sizeKomplement = 49 - images.size();
            rows = 7;
            for (int i = 0; i < sizeKomplement; i++) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setSize(70, 70);

                tilesContent.add(emptyPanel);
            }
        }

        GridLayout gridLayout = new GridLayout(rows, 7, 2,2);
        tilesContent.setLayout(gridLayout);
        JScrollPane scrollPane = new JScrollPane(tilesContent);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        getContentPane().add(tilesContent);
        getContentPane().add(scrollPane);
        pack();
    }


}
