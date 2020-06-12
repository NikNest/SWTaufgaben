package actionListeners;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class LoadTilesActionListener implements ActionListener {
    private ArrayList<ImageIcon> images;

    public ArrayList<ImageIcon> getImages() {
        return images;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        images = new ArrayList<>();
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        file.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            for(File imgFile : file.getSelectedFiles()) {
                System.out.println(imgFile.toPath());
                ImageIcon image = new ImageIcon((imgFile.toPath()).toString());
                Image img = image.getImage();
                Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_FAST);
                image = new ImageIcon(scaledImage);
                images.add(image);
            }
        } else {
            System.out.println("File not approaved");
        }
    }
}
