package actionListeners;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.rectangle.RectangleArtist;
import org.iMage.mosaique.triangle.TriangleArtist;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoadTilesActionListener implements ActionListener {
    private TriangleArtist triangleArtist;
    private RectangleArtist rectangleArtist;
    private TextFieldDocumentListener textFieldDocumentListener;
    private LoadInputActionListener loadInputActionListener;
    private JButton runBtn;
    private JButton showBtn;
    private boolean tilesLoaded = false;

    public LoadTilesActionListener(JButton runBtn,
                                   JButton showBtn) {
        this.runBtn = runBtn;
        this.showBtn = showBtn;
    }

    public void setTextFieldDocumentListener(TextFieldDocumentListener textFieldDocumentListener) {
        this.textFieldDocumentListener = textFieldDocumentListener;
    }

    public void setLoadInputActionListener(LoadInputActionListener loadInputActionListener) {
        this.loadInputActionListener = loadInputActionListener;
    }

    public TriangleArtist getTriangleArtist() {
        return triangleArtist;
    }

    public RectangleArtist getRectangleArtist() {
        return rectangleArtist;
    }

    public boolean isTilesLoaded() {
        return tilesLoaded;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runBtn.setEnabled(false);
        showBtn.setEnabled(false);
        tilesLoaded = false;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        file.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            ArrayList<BufferedArtImage> images = new ArrayList<>();
            for(File imgFile : file.getSelectedFiles()) {
                try {
                    images.add(new BufferedArtImage(ImageIO.read(imgFile)));
                } catch (IOException ioException) {
                    System.out.println("Image not approaved!");
                    runBtn.setEnabled(false);
                }
            }
            if (images.size() >= 10) {
                int tileWidth = textFieldDocumentListener.getTileWidth();
                int tileHeight = textFieldDocumentListener.getTileHeight();
                triangleArtist = new TriangleArtist(images, tileWidth, tileHeight);
                rectangleArtist = new RectangleArtist(images, tileWidth, tileHeight);
                tilesLoaded = true;
                showBtn.setEnabled(true);
                if (loadInputActionListener.isInputImageLoaded()) {
                    runBtn.setEnabled(true);
                }
            } else {
                System.out.println("Not enough tiles choosen!");
            }
        } else {
            System.out.println("File not approaved!");
        }
    }
}
