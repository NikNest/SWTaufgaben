import org.iMage.plugins.PluginForJmjrst;
import org.jis.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaCrashCourse extends PluginForJmjrst {
    private List<String> javaVersionsList;
    Main main;
    JFrame frame;
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

    public JavaCrashCourse() {
        javaVersionsList = Arrays.asList(javaVersions);
        javaVersionsList = Collections.unmodifiableList(javaVersionsList);
    }

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
        System.out.println(String.format("Found %d Java versions since Java 8", getNumberOfParameters()));
        this.main = main;

    }

    @Override
    public void run() {

    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public void configure() {
        frame = new JFrame("frame name");
        frame.setLocationRelativeTo(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // TODO: fill the frame
        javaVersionsList.stream().forEach((version) -> {
            JLabel jVersion = new JLabel(version);
            main.add(jVersion);
        });
        //
        main.pack();
        frame.setVisible(true);

    }
}
