/*
 * Copyright 2007 Johannes Geppert 
 * 
 * Licensed under the GPL, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * 
 * You may obtain a copy of the License at
 * http://www.fsf.org/licensing/licenses/gpl.txt 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on 
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 */
package org.jis.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import org.iMage.plugins.PluginForJmjrst;
import org.iMage.plugins.PluginManagement;
import org.jis.Main;
import org.jis.listner.MenuListner;


/**
 * @author <a href="http://www.jgeppert.com">Johannes Geppert</a>
 * 
 * <p>
 * This is Menu of the GUI
 * </p>
 */
public class Menu extends JMenuBar {
  private static final long serialVersionUID = 1232107393895691717L;

  public JMenuItem          gener;
  public JMenuItem          zippen;
  public JMenuItem          gallerie;
  public JMenuItem          exit;
  public JMenuItem          set_quality;
  public JMenuItem          info;
  public JMenuItem          look_windows;
  public JMenuItem          look_windows_classic;
  public JMenuItem          look_nimbus;
  public JMenuItem          look_metal;
  public JMenuItem          look_motif;
  public JMenuItem          look_gtk;
  public JMenuItem          update_check;
  public JMenu              pluginName;


  /**
   * @param m
   *          a reference to the Main class
   */
  public Menu(Main m) {
    super();
    JMenu datei = new JMenu(m.mes.getString("Menu.0"));
    JMenu option = new JMenu(m.mes.getString("Menu.1"));
    JMenu optionen_look = new JMenu(m.mes.getString("Menu.2"));
    JMenu about = new JMenu(m.mes.getString("Menu.3"));

    //

    JMenu pluginsMenu = new JMenu("Load plug-ins");
    ArrayList<PluginForJmjrst> plugins = (ArrayList<PluginForJmjrst>) PluginManagement.getPlugins();
    if (plugins.size() != 0) {
      for (PluginForJmjrst plug : plugins) {
        pluginName = new JMenu(plug.getName());
        JMenuItem start = new JMenuItem("start");
        JMenuItem configure = new JMenuItem("configure");
        start.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            plug.init(m);
          }
        });
        configure.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            plug.configure();
          }
        });
        pluginName.add(start);
        pluginName.add(configure);
        pluginsMenu.add(pluginName);
        pluginsMenu.addSeparator();
      }
      pluginsMenu.remove(pluginsMenu.getItemCount() - 1);
    } else {
      JLabel emptyPluginsMenuLabel = new JLabel("(No plug-ins available!)");
      emptyPluginsMenuLabel.setForeground(Color.LIGHT_GRAY);
      pluginsMenu.add(emptyPluginsMenuLabel);
    }


    //

    gener = new JMenuItem(m.mes.getString("Menu.4"));
    URL url = ClassLoader.getSystemResource("icons/media-playback-start.png");
    gener.setIcon(new ImageIcon(url));

    url = ClassLoader.getSystemResource("icons/preferences-desktop-theme.png");
    optionen_look.setIcon(new ImageIcon(url));

    zippen = new JMenuItem(m.mes.getString("Menu.13"));
    url = ClassLoader.getSystemResource("icons/package-x-generic.png");
    zippen.setIcon(new ImageIcon(url));

    gallerie = new JMenuItem(m.mes.getString("Menu.14"));
    url = ClassLoader.getSystemResource("icons/text-html.png");
    gallerie.setIcon(new ImageIcon(url));

    exit = new JMenuItem(m.mes.getString("Menu.5"));
    url = ClassLoader.getSystemResource("icons/system-log-out.png");
    exit.setIcon(new ImageIcon(url));

    set_quality = new JMenuItem(m.mes.getString("Menu.6"));
    url = ClassLoader.getSystemResource("icons/preferences-system.png");
    set_quality.setIcon(new ImageIcon(url));

    info = new JMenuItem(m.mes.getString("Menu.7"));
    url = ClassLoader.getSystemResource("icons/help-browser.png");
    info.setIcon(new ImageIcon(url));

    update_check = new JMenuItem(m.mes.getString("Menu.15"));
    url = ClassLoader.getSystemResource("icons/system-software-update.png");
    update_check.setIcon(new ImageIcon(url));

    look_windows = new JMenuItem(m.mes.getString("Menu.8"));
    look_windows_classic = new JMenuItem(m.mes.getString("Menu.9"));
    look_nimbus = new JMenuItem(m.mes.getString("Menu.16"));
    look_metal = new JMenuItem(m.mes.getString("Menu.10"));
    look_motif = new JMenuItem(m.mes.getString("Menu.11"));
    look_gtk = new JMenuItem(m.mes.getString("Menu.12"));

    gener.setEnabled(false);
    zippen.setEnabled(false);
    gallerie.setEnabled(false);

    datei.add(gener);
    datei.add(zippen);
    datei.add(gallerie);
    datei.addSeparator();
    datei.add(exit);
    option.add(optionen_look);
    option.add(set_quality);
    option.addSeparator();
    option.add(update_check);
    about.add(info);
    this.add(datei);
    this.add(option);
    this.add(pluginsMenu);
    this.add(about);

//    MenuButtonsListener menuButtonsListener = new MenuButtonsListener();
//    plugins.addMenuListener(menuButtonsListener);
    MenuListner al = new MenuListner(m, this);
    exit.addActionListener(al);
    gener.addActionListener(al);
    zippen.addActionListener(al);
    gallerie.addActionListener(al);
    set_quality.addActionListener(al);
    info.addActionListener(al);
    look_windows.addActionListener(al);
    look_windows_classic.addActionListener(al);
    look_nimbus.addActionListener(al);
    look_metal.addActionListener(al);
    look_motif.addActionListener(al);
    look_gtk.addActionListener(al);
    update_check.addActionListener(al);

    UIManager.LookAndFeelInfo uii[] = UIManager.getInstalledLookAndFeels();
    for (int i = 0; i < uii.length; i++)
    {
      if (uii[i].toString().substring(uii[i].toString().lastIndexOf(" ") + 1, uii[i].toString().lastIndexOf("]")) //$NON-NLS-1$ //$NON-NLS-2$
      .equalsIgnoreCase("com.sun.java.swing.plaf.windows.WindowsLookAndFeel")) optionen_look.add(look_windows); //$NON-NLS-1$
      if (uii[i].toString().substring(uii[i].toString().lastIndexOf(" ") + 1, uii[i].toString().lastIndexOf("]")) //$NON-NLS-1$ //$NON-NLS-2$
      .equalsIgnoreCase("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel")) optionen_look.add(look_windows_classic); //$NON-NLS-1$
      if (uii[i].toString().substring(uii[i].toString().lastIndexOf(" ") + 1, uii[i].toString().lastIndexOf("]")) //$NON-NLS-1$ //$NON-NLS-2$
      .equalsIgnoreCase("com.sun.java.swing.plaf.motif.MotifLookAndFeel")) optionen_look.add(look_motif); //$NON-NLS-1$
      if (uii[i].toString().substring(uii[i].toString().lastIndexOf(" ") + 1, uii[i].toString().lastIndexOf("]")) //$NON-NLS-1$ //$NON-NLS-2$
      .equalsIgnoreCase("javax.swing.plaf.metal.MetalLookAndFeel")) optionen_look.add(look_metal); //$NON-NLS-1$
      if (uii[i].toString().substring(uii[i].toString().lastIndexOf(" ") + 1, uii[i].toString().lastIndexOf("]")) //$NON-NLS-1$ //$NON-NLS-2$
      .equalsIgnoreCase("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) optionen_look.add(look_gtk); //$NON-NLS-1$
      if (uii[i].toString().substring(uii[i].toString().lastIndexOf(" ") + 1, uii[i].toString().lastIndexOf("]")) //$NON-NLS-1$ //$NON-NLS-2$
          .equalsIgnoreCase("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")) optionen_look.add(look_nimbus); //$NON-NLS-1$
    }
  }

}
