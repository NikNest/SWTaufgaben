package actionListeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

public class TextFieldDocumentListener implements DocumentListener {
    private JTextField tileSizeH;
    private JTextField tileSizeW;
    private LoadInputActionListener loadInputActionListener;
    private int tileWidth;
    private int tileHeight;
    private boolean tileWidthSetted = false;
    private boolean tileHeightSetted = false;


    public TextFieldDocumentListener(JTextField tileSizeH, JTextField tileSizeW) {
        this.tileSizeH = tileSizeH;
        this.tileSizeW = tileSizeW;
    }

    public void setLoadInputActionListener(LoadInputActionListener loadInputActionListener) {
        this.loadInputActionListener = loadInputActionListener;
    }

    public void checkHeightWidthWithNewInputImage() {
                int height = tileHeight;
                tileSizeH.setForeground(new Color(0, 0, 0));
                if (loadInputActionListener.isInputImageLoaded()) {
                    boolean largerThenImage = loadInputActionListener.getBufferedImage().getHeight() <= height;
                    if (largerThenImage) {
                        tileHeight = (int) (loadInputActionListener.getBufferedImage().getHeight() * 0.1);
                        tileSizeH.setForeground(new Color(255, 0, 0));
                    }
                }
                int width = tileWidth;
                tileHeight = width;
                tileSizeW.setForeground(new Color(0, 0, 0));
                if (loadInputActionListener.isInputImageLoaded()) {
                    boolean largerThenImage = loadInputActionListener.getBufferedImage().getWidth() <= width;
                    if (largerThenImage) {
                        tileWidth = (int) (loadInputActionListener.getBufferedImage().getWidth() * 0.1);
                        tileSizeW.setForeground(new Color(255, 0, 0));
                    }
                }
    }

    private void applyChanges(DocumentEvent e) throws BadLocationException {
        Document document = (Document) e.getDocument();
        if (e.getDocument().getProperty("tileSizeType").equals("H")) {
            String input = document.getText(0, document.getLength());
            if (input.matches("[1-9]\\d{0,1}\\d{0,1}\\d{0,1}")) {
                int height = Integer.parseInt(input);
                tileHeight = height;
                tileSizeH.setForeground(new Color(0, 0, 0));
                if (loadInputActionListener.isInputImageLoaded()) {
                    boolean largerThenImage = loadInputActionListener.getBufferedImage().getHeight() <= height;
                    if (largerThenImage) {
                        tileHeight = (int) (loadInputActionListener.getBufferedImage().getHeight() * 0.1);
                        tileSizeH.setForeground(new Color(255, 0, 0));
                    }
                }
            } else {
                tileSizeH.setForeground(new Color(255, 0, 0));
            }
        } else {
            String input = document.getText(0, document.getLength());
            if (input.matches("[1-9]\\d{0,1}\\d{0,1}\\d{0,1}")) {
                int width = Integer.parseInt(input);
                tileWidth = width;
                tileSizeW.setForeground(new Color(0, 0, 0));
                if (loadInputActionListener.isInputImageLoaded()) {
                    boolean largerThenImage = loadInputActionListener.getBufferedImage().getWidth() <= width;
                    if (largerThenImage) {
                        tileWidth = (int) (loadInputActionListener.getBufferedImage().getWidth() * 0.1);
                        tileSizeW.setForeground(new Color(255, 0, 0));
                    }
                } else {
                    tileSizeW.setForeground(new Color(255, 0, 0));
                }
            }
            System.out.println(document.getText(0, document.getLength()));
        }
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            applyChanges(e);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            applyChanges(e);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            applyChanges(e);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }
}


