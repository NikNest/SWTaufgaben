package panelPatterns;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ImagePanelPattern extends JPanel {
    private JLabel tileLabel;
    private ImageIcon tile;
    public ImagePanelPattern(ImageIcon tile) {
        this.setSize(new Dimension(70, 70));
        this.setBackground(new Color(200, 200, 250));
        this.setLayout(new GridBagLayout());
        this.tile = tile;
        tileLabel = new JLabel(this.tile);
        this.add(tileLabel);
    }
}
