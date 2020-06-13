import actionListeners.*;
import org.iMage.mosaique.rectangle.RectangleArtist;
import org.iMage.mosaique.triangle.TriangleArtist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class MosaiqueLauncher {
    private static JFrame mainFrame;
    private static JPanel inputContent;
    private static JPanel inputPanel;
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
    private static LoadTilesActionListener loadTilesActionListener;
    private static LoadInputActionListener loadInputActionListener;
    private static RunActionListener runActionListener;
    private static TilesAmountInputActionListener tilesAmountInputActionListener;
    private static RectangleArtist rectangleArtist;
    private static TriangleArtist triangleArtist;


    public static void main(String[] args) throws IOException {
        mainFrame = new JFrame("iTiler");
        mainFrame.setPreferredSize(new Dimension(800, 450));
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.PAGE_AXIS));

        inputContent = new JPanel();
        inputContent.setBackground(new Color(0, 0, 0));
        inputContent.setBorder(new EmptyBorder(25, 0, 25, 0));
        inputContent.setLayout(new GridLayout());

        confButtonsContent = new JPanel();
        confButtonsContent.setSize(new Dimension(800, 100));
        confButtonsContent.setBackground(new Color(0, 255, 255));
        confButtonsContent.setLayout(new GridLayout());

        artistButtonsContent = new JPanel();
        artistButtonsContent.setSize(new Dimension(800, 100));
        artistButtonsContent.setBackground(new Color(255,0, 255));

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setPreferredSize(new Dimension(350, 250));
        inputPanel.setBackground(new Color(255, 255, 0));

        resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        resultPanel.setPreferredSize(new Dimension(350, 250));
        resultPanel.setBackground(new Color(255, 0, 0));

        inputContent.add(inputPanel);
        inputContent.add(resultPanel);

        loadInputBtn = new JButton("Load Input");
        saveResultBtn = new JButton("Save Result");
        saveResultBtn.setEnabled(false);
        loadTiles = new JButton("Load Tiles");
        showTiles = new JButton("Show Tiles");
        showTiles.setEnabled(false);
        runBtn = new JButton("Run");
        runBtn.setEnabled(false);
        JLabel tileSizeLbl = new JLabel("Tile Size");
        JTextField tileSizeH = new JTextField("30", 3);
        tileSizeH.getDocument().putProperty("tileSizeType", "H");
        JLabel xbetween = new JLabel(" X ");
        JTextField tileSizeW = new JTextField("25", 3);
        tileSizeW.getDocument().putProperty("tileSizeType", "W");


        TextFieldDocumentListener textFieldDocumentListener = new TextFieldDocumentListener(tileSizeH, tileSizeW);
//        tilesAmountInputActionListener = new TilesAmountInputActionListener();
        loadTilesActionListener = new LoadTilesActionListener(runBtn, showTiles);
        loadInputActionListener = new LoadInputActionListener(inputPanel, runBtn);
        runActionListener = new RunActionListener(resultPanel);
        showTilesActionListener = new ShowTilesActionListener(mainFrame);

        textFieldDocumentListener.setLoadInputActionListener(loadInputActionListener);
        runActionListener.setLoadTilesActionListener(loadTilesActionListener);
        loadTilesActionListener.setLoadInputActionListener(loadInputActionListener);
        loadTilesActionListener.setTextFieldDocumentListener(textFieldDocumentListener);
        loadInputActionListener.setLoadTilesActionListener(loadTilesActionListener);
        loadInputActionListener.setTextFieldDocumentListener(textFieldDocumentListener);
        runActionListener.setLoadInputActionListener(loadInputActionListener);
        showTilesActionListener.setLoadTilesActionListener(loadTilesActionListener);



        tileSizeH.addActionListener(tilesAmountInputActionListener);
        tileSizeW.addActionListener(tilesAmountInputActionListener);
        tileSizeW.getDocument().addDocumentListener(textFieldDocumentListener);
        tileSizeH.getDocument().addDocumentListener(textFieldDocumentListener);

        runBtn.addActionListener(runActionListener);
        loadInputBtn.addActionListener(loadInputActionListener);

        loadInputBtnPanel = new JPanel();
        loadInputBtnPanel.setLayout(new GridBagLayout());
        loadInputBtnPanel.add(loadInputBtn);

        saveResultBtnPanel = new JPanel();
        saveResultBtnPanel.setLayout(new GridBagLayout());
        saveResultBtnPanel.add(saveResultBtn);




        loadTiles.addActionListener(loadTilesActionListener);
        showTiles.addActionListener(showTilesActionListener);

        JLabel artistLbl = new JLabel("Artist");
        artists = new JComboBox(new String[]{"Rectangle", "Triangle"});
        artists.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String value = (String) artists.getSelectedItem();
                if (value.equals("Rectangle")) {
                    runActionListener.setUseRectangle(true);
                } else {
                    runActionListener.setUseRectangle(false);
                }
            }
        });



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

        mainFrame.getContentPane().add(inputContent);
        mainFrame.getContentPane().add(confButtonsContent);
        mainFrame.getContentPane().add(artistButtonsContent);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
