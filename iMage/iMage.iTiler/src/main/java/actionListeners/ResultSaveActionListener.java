package actionListeners;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultSaveActionListener implements ActionListener {
    private RunActionListener runActionListener;

    public void setRunActionListener(RunActionListener runActionListener) {
        this.runActionListener = runActionListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            File outputFile = chooser.getSelectedFile();
            BufferedImage bi = runActionListener.getResultImage();
            try {
                ImageIO.write(bi, "png", outputFile);
            } catch (IOException ioException) {
                System.out.println("Failed to write result image to file!");
            }
        }
    }
}
