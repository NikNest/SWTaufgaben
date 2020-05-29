import org.iMage.plugins.PluginForJmjrst;
import org.jis.Main;
import org.kohsuke.MetaInfServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@MetaInfServices(PluginForJmjrst.class)
public class JavaCrashCourse extends PluginForJmjrst {
    private List<String> javaVersionsList;
    private String choosenJavaVersion = "";
    private Main main;
    private JFrame frameStart;
    private JFrame frameConfigure;
    final private String[] javaVersions = {
            "Java SE 8(LTS)",
            "Java SE 9",
            "Java SE 10",
            "Java SE 11(LTS)",
            "Java SE 12",
            "Java SE 13",
            "Java SE 14",
            "Java SE 15",
            "Java SE 16",
            "Java SE 17(LTS)"
    };

    @Override
    public String getName() {
        return "JavaCrashCourse";
    }

    @Override
    public int getNumberOfParameters() {
        return javaVersionsList.size();
    }

    @Override
    public void init(Main main) {
        javaVersionsList = Arrays.asList(javaVersions);
        javaVersionsList = Collections.unmodifiableList(javaVersionsList);
        this.main = main;
    }

    @Override
    public void run() {
        System.out.println(String.format("Found %d Java versions since Java 8", getNumberOfParameters()));
        JFrame frameStart = new JFrame("Java Crash Course");
        frameStart.setLocationRelativeTo(main);
        frameStart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameStart.setPreferredSize(new Dimension(250, 150));
        JPanel panelStart = new JPanel();
        panelStart.setLayout(new BoxLayout(panelStart, BoxLayout.PAGE_AXIS));
        JLabel msg;
        switch(choosenJavaVersion) {
            case "Java SE 8(LTS)":
                msg = new JLabel("Keeping updated");
                break;
            case "Java SE 9",
                    "Java SE 10",
                    "Java SE 11(LTS)",
                    "Java SE 12",
                    "Java SE 13",
                    "Java SE 14",
                    "Java SE 15",
                    "Java SE 16",
                    "Java SE 17(LTS)":
                msg = new JLabel("Running late");
                break;
            default:
                msg = new JLabel(String.format("JavaCrashCourse(%d)", getNumberOfParameters()));
                break;
        }
//        panelStart.revalidate();
//        panelStart.repaint();
        panelStart.removeAll();
        panelStart.add(msg);
        frameStart.add(panelStart);
        frameStart.pack();
        frameStart.setVisible(true);
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public void configure() {
        frameConfigure = new JFrame("Java Versions");
        frameConfigure.setPreferredSize(new Dimension(250, 260));
        frameConfigure.setLayout(new BorderLayout());
        frameConfigure.setLocationRelativeTo(main);
        frameConfigure.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        ButtonGroup radioButtons = new ButtonGroup();
        javaVersionsList.stream().forEach((version) -> {
            JRadioButton jVersion = new JRadioButton(version);
            jVersion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    choosenJavaVersion = e.getActionCommand();
                }
            });
            radioButtons.add(jVersion);
            panel.add(jVersion);
        });
        frameConfigure.add(panel);
        frameConfigure.pack();
        frameConfigure.setVisible(true);
    }
}
