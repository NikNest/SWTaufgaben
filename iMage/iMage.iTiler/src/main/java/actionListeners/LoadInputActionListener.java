package actionListeners;

import org.iMage.mosaique.base.BufferedArtImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadInputActionListener implements ActionListener {
    private ImageIcon imageIcon;
    private BufferedImage bufferedImage;
    private JPanel inputPanel;
    private LoadTilesActionListener loadTilesActionListener;
    private TextFieldDocumentListener textFieldDocumentListener;
    private JButton runBtn;
    private boolean inputImageLoaded = false;

    public LoadInputActionListener(JPanel inputPanel,
                                   JButton runBtn) {
        this.inputPanel = inputPanel;
        this.runBtn = runBtn;
    }

    public void setLoadTilesActionListener(LoadTilesActionListener loadTilesActionListener) {
        this.loadTilesActionListener = loadTilesActionListener;
    }

    public void setTextFieldDocumentListener(TextFieldDocumentListener textFieldDocumentListener) {
        this.textFieldDocumentListener = textFieldDocumentListener;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    private void setInputImage() {
        inputPanel.removeAll();
        if(imageIcon != null)
            inputPanel.add(new JLabel(imageIcon));
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public boolean isInputImageLoaded() {
        return inputImageLoaded;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputImageLoaded = false;
        runBtn.setEnabled(false);
        imageIcon = null;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
                try {
                    File imgFile = file.getSelectedFile();
                    bufferedImage = ImageIO.read(imgFile);
                    imageIcon = new ImageIcon((imgFile.toPath()).toString());
                    Image img = imageIcon.getImage();
                    Image scaledImage = img.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
                    imageIcon = new ImageIcon(scaledImage);
                    inputImageLoaded = true;
                    if (loadTilesActionListener.isTilesLoaded()) {
                        runBtn.setEnabled(true);
                    }
                } catch (IOException ioException) {
                    runBtn.setEnabled(true);
                }
        } else {
            System.out.println("File not approaved");
        }
        textFieldDocumentListener.checkHeightWidthWithNewInputImage();
        setInputImage();
    }
}
