import actionListeners.ShowTilesActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class MosaiqueLauncher {
    private static JFrame mainFrame;
    private static JFrame showTilesFrame;
    private static JPanel imgContent;
    private static JPanel imgPanel;
    private static JPanel resultPanel;
    private static JPanel confButtonsContent;
    private static JPanel artistButtonsContent;
    private static ImageIcon img;
    private static ImageIcon result;
    private static JButton loadInputBtn;
    private static JPanel loadInputBtnPanel;
    private static JButton saveResultBtn;
    private static JPanel saveResultBtnPanel;
    private static JButton loadTiles;
    private static JButton showTiles;
    private static JButton runBtn;
    private static JComboBox artists;
    private static ShowTilesActionListener showTilesActionListener;

    public static void main(String[] args) throws IOException {
        mainFrame = new JFrame("iTiler");
        mainFrame.setPreferredSize(new Dimension(800, 450));
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.PAGE_AXIS));

        imgContent = new JPanel();
        imgContent.setBackground(new Color(0, 0, 0));
        imgContent.setBorder(new EmptyBorder(25, 0, 25, 0));
        imgContent.setLayout(new GridLayout());


        confButtonsContent = new JPanel();
        confButtonsContent.setSize(new Dimension(800, 100));
        confButtonsContent.setBackground(new Color(0, 255, 255));
        confButtonsContent.setLayout(new GridLayout());

        artistButtonsContent = new JPanel();
        artistButtonsContent.setSize(new Dimension(800, 100));
        artistButtonsContent.setBackground(new Color(255,0, 255));


        imgPanel = new JPanel();
        imgPanel.setLayout(new GridBagLayout());
        imgPanel.setPreferredSize(new Dimension(350, 250));
        imgPanel.setBackground(new Color(255, 255, 0));

        resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        resultPanel.setPreferredSize(new Dimension(350, 250));
        resultPanel.setBackground(new Color(255, 0, 0));

        img = new ImageIcon("iMage.iTiler/src/main/resources/blue.jpg", "");
        result = new ImageIcon("iMage.iTiler/src/main/resources/green.jpg", "");

        imgPanel.add(new JLabel(img));
        resultPanel.add(new JLabel(result));
        imgContent.add(imgPanel);
        imgContent.add(resultPanel);

        loadInputBtn = new JButton("Load Input");
        saveResultBtn = new JButton("Save Result");
        loadTiles = new JButton("Load Tiles");
        showTiles = new JButton("Show Tiles");
        runBtn = new JButton("Run");


        loadInputBtnPanel = new JPanel();
        loadInputBtnPanel.setLayout(new GridBagLayout());
        loadInputBtnPanel.add(loadInputBtn);

        saveResultBtnPanel = new JPanel();
        saveResultBtnPanel.setLayout(new GridBagLayout());
        saveResultBtnPanel.add(saveResultBtn);

        showTilesFrame = new JFrame("Thumbnails");
        showTilesActionListener = new ShowTilesActionListener(mainFrame, showTilesFrame);
        JLabel tileSizeLbl = new JLabel("Tile Size");
        JTextField tileSizeH = new JTextField("25");
        JLabel xbetween = new JLabel(" X ");
        JTextField tileSizeW = new JTextField("25");
        loadTiles = new JButton("Load Tiles");
        showTiles = new JButton("Show Tiles");
        showTiles.addActionListener(showTilesActionListener);
        JLabel artistLbl = new JLabel("Artist");
        artists = new JComboBox(new String[]{"Rectangle", "Triangle"});
        runBtn = new JButton("Run");

        confButtonsContent.add(loadInputBtnPanel);
        confButtonsContent.add(saveResultBtnPanel);

        artistButtonsContent.add(tileSizeLbl);
        artistButtonsContent.add(tileSizeH);
        artistButtonsContent.add(xbetween);
        artistButtonsContent.add(tileSizeW);
        artistButtonsContent.add(loadTiles);
        artistButtonsContent.add(showTiles);
        artistButtonsContent.add(artistLbl);
        artistButtonsContent.add(artists);
        artistButtonsContent.add(runBtn);

        mainFrame.getContentPane().add(imgContent);
        mainFrame.getContentPane().add(confButtonsContent);
        mainFrame.getContentPane().add(artistButtonsContent);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
