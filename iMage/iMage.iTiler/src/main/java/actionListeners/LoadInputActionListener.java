package actionListeners;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class LoadInputActionListener implements ActionListener {
    private ImageIcon image;
    private JFrame mainFrame;
    private JPanel inputPanel;

    public LoadInputActionListener(JFrame mainFrame, JPanel inputPanel) {
        this.mainFrame = mainFrame;
        this.inputPanel = inputPanel;
    }

    public ImageIcon getImage() {
        return image;
    }

    private void setInputImage() {
        inputPanel.removeAll();
        inputPanel.add(new JLabel(image));
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
                File imgFile = file.getSelectedFile();
                System.out.println(imgFile.toPath());
                image = new ImageIcon((imgFile.toPath()).toString());
                Image img = image.getImage();
                Image scaledImage = img.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
                image = new ImageIcon(scaledImage);
        } else {
            System.out.println("File not approaved");
        }
        setInputImage();
    }
}
