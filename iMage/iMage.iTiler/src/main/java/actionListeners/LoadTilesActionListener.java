package actionListeners;

import org.iMage.mosaique.base.BufferedArtImage;
import org.iMage.mosaique.rectangle.RectangleArtist;
import org.iMage.mosaique.triangle.TriangleArtist;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoadTilesActionListener implements ActionListener {
    private JFrame mainFrame;
    private TriangleArtist triangleArtist;
    private RectangleArtist rectangleArtist;
    private TextFieldDocumentListener textFieldDocumentListener;
    private LoadInputActionListener loadInputActionListener;
    private JButton runBtn;
    private JButton showBtn;
    private boolean tilesLoaded = false;
    private ArrayList<BufferedArtImage> images;

    public LoadTilesActionListener(JButton runBtn,
                                   JButton showBtn, JFrame mainFrame) {
        this.runBtn = runBtn;
        this.showBtn = showBtn;
        this.mainFrame = mainFrame;
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


    private class Slave extends SwingWorker<ArrayList<BufferedArtImage>, Integer> {
        private File[] files;
        private ArrayList<BufferedArtImage> images;
        private JFrame loadingImages;
        private JProgressBar progressBar;

        Slave(File[] files) {
            this.files = files;
            this.images = new ArrayList<>();

            loadingImages = new JFrame("Loading Images");
            loadingImages.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            loadingImages.setLocationRelativeTo(mainFrame);
            loadingImages.setSize(new Dimension(220, 80));
            JPanel panel = new JPanel();
            progressBar = new JProgressBar(0, files.length);
            progressBar.setBounds(40,40,160,30);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            panel.add(progressBar);
            loadingImages.getContentPane().add(panel);
            loadingImages.setVisible(true);
        }

        @Override
        protected ArrayList<BufferedArtImage> doInBackground() throws Exception {
            int imgsAlreadyLoaded = 0;
            for(File imgFile : files) {
                try {
                    images.add(new BufferedArtImage(ImageIO.read(imgFile)));
                    imgsAlreadyLoaded++;
                    publish(imgsAlreadyLoaded);
//                progressBar.setValue(imgsAlreadyLoaded);
                } catch (IOException ioException) {
                    System.out.println("Image not approaved!");
                    runBtn.setEnabled(false);
                    return null;
                }
            }
            return images;
        }

        @Override
        protected void process(List<Integer> chunks) {
            super.process(chunks);
            progressBar.setValue(chunks.get(chunks.size()-1));
        }

        @Override
        protected void done() {
            super.done();
            loadingImages.dispose();
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
        }
    }

    public void createArtists() {
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
            Slave downloader = new Slave(file.getSelectedFiles());
            downloader.execute();
//            try {
//                images = downloader.get();
//            } catch (InterruptedException | ExecutionException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//            while(!downloader.isDone()){}
//            createArtists();

        } else {
            System.out.println("File not approaved!");
        }
    }
}
