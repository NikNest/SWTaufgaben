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
    private BufferedImage resultImage;
    private ImageIcon resultImageIcon;
    private MosaiqueEasel mosaique;
    private boolean useRectangle = true;


    public RunActionListener(JPanel outputPanel) {
        this.outputPanel = outputPanel;
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
    }

    private void runMosaique() {
//        ImageIcon inputImage = loadInputActionListener.getImageIcon();
//        BufferedImage inputBImage = new BufferedImage(
//                inputImage.getIconWidth(),
//                inputImage.getIconHeight(),
//                BufferedImage.TYPE_INT_ARGB);
//        Graphics g = inputBImage.createGraphics();
//        inputImage.paintIcon(null, g, 0, 0);
//        g.dispose();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        runMosaique();
        setResultImage();
    }
}
