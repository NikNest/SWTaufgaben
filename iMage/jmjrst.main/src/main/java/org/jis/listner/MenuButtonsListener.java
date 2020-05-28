package org.jis.listner;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;



public class MenuButtonsListener implements MenuListener {
    JFrame pluginsFrame = new JFrame("Load plug-ins");

    public MenuButtonsListener() {
        pluginsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pluginsFrame.setSize(600, 400);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        pluginsFrame.setVisible(true);
        System.out.println("menu selected");
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("menu deselected");
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("menu canceled");
    }
}
