package actionListeners;

import org.iMage.mosaique.MosaiqueEasel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class RunActionListener implements ActionListener {
    private LoadInputActionListener loadInputActionListener;
    private LoadTilesActionListener loadTilesActionListener;
    private JPanel outputPanel;
    private JButton saveBtn;
    private BufferedImage resultImage;
    private ImageIcon resultImageIcon;
    private MosaiqueEasel mosaique;
    private boolean useRectangle = true;


    public RunActionListener(JPanel outputPanel, JButton saveBtn) {
        this.outputPanel = outputPanel;
        this.saveBtn = saveBtn;
        mosaique = new MosaiqueEasel();
    }

    public void setLoadTilesActionListener(LoadTilesActionListener loadTilesActionListener) {
        this.loadTilesActionListener = loadTilesActionListener;
    }

    public void setLoadInputActionListener(LoadInputActionListener loadInputActionListener) {
        this.loadInputActionListener = loadInputActionListener;
    }

    private void setResultImage() {
        outputPanel.removeAll();
        outputPanel.add(new JLabel(resultImageIcon));
        outputPanel.revalidate();
        outputPanel.repaint();
        saveBtn.setEnabled(true);
    }

    private void runMosaique() {
        BufferedImage inputBImage = loadInputActionListener.getBufferedImage();
        resultImage = useRectangle ?
                mosaique.createMosaique(inputBImage, loadTilesActionListener.getRectangleArtist()) :
                mosaique.createMosaique(inputBImage, loadTilesActionListener.getTriangleArtist());
        Image scaledImage = resultImage.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        resultImageIcon = new ImageIcon(scaledImage);
    }

    public void setUseRectangle(boolean useRectangle) {
        this.useRectangle = useRectangle;
    }

    public BufferedImage getResultImage() {
        return resultImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runMosaique();
        setResultImage();
    }
}
