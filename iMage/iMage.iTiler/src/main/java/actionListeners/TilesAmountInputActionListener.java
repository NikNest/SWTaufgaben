package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TilesAmountInputActionListener implements ActionListener {


    public int getTileHeight() {
        return 10;
    }

    public int getTileWidth() {
       return 10;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println(e.getSource());
        System.out.println(e.paramString());
    }
}
