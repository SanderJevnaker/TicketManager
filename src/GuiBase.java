import javax.swing.*;
import java.awt.*;

public class GuiBase {
    static GridBagConstraints makeConstraints(int xNdx, int yNdx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = xNdx;
        gbc.gridy = yNdx;

        return gbc;
    }
        static JFrame getFrame() { // Frame is created by Gui constructor, ie. before it is possible to call getFrame()
        return (JFrame) Frame.getFrames()[0];
    }

}

