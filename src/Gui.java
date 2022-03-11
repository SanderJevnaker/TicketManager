import javax.swing.*;
import java.awt.*;

public class Gui {
    JFrame frame;

    static class C {
        static class height {
            static int frame = 628;
            static int leftPanel = 540;
            static int rightPanel = 540;
            static int informPanel = 30;
            static int bottomPanel = 30;
        }

        static class width {
            static int frame = 1100;
            static int leftPanel = 550;
            static int rightPanel = 550;
            static int informPanel = 1100;
            static int bottomPanel = 1100;
        }

        static class color {
            static Color leftPanel = Color.blue;
            static Color rightPanel = Color.orange;
            static Color informPanel = Color.cyan;
            static Color bottomPanel = Color.red;
        }

        static class pos {
            static Point frame = new Point(40, 40);
            static Point leftPanel = new Point(0, 30);
            static Point rightPanel = new Point(550, 30);
            static Point informPanel = new Point(0, 0);
            static Point bottomPanel = new Point(0, 570);
        }
    }

    public Gui() {
        frame = new JFrame("Hjertnes Kino");

        JPanel informPanel = new JPanel();
        informPanel.setName("Inform Panel");
        informPanel.setBounds(C.pos.informPanel.x, C.pos.informPanel.y, C.width.informPanel, C.height.informPanel);
        informPanel.setBackground(C.color.informPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setName("Left Panel");
        leftPanel.setBounds(C.pos.leftPanel.x, C.pos.leftPanel.y, C.width.leftPanel, C.height.leftPanel);
        leftPanel.setBackground(C.color.leftPanel);
        leftPanel.add(new JLabel("Tjohei"));

        JPanel rightPanel = new JPanel();
        rightPanel.setName("Right Panel");
        rightPanel.setBounds(C.pos.rightPanel.x, C.pos.rightPanel.y, C.width.rightPanel, C.height.rightPanel);
        rightPanel.setBackground(C.color.rightPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setName("Bottom Panel");
        bottomPanel.setBounds(C.pos.bottomPanel.x, C.pos.bottomPanel.y, C.width.bottomPanel, C.height.bottomPanel);
        bottomPanel.setBackground(C.color.bottomPanel);

        frame.add(informPanel);
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.add(bottomPanel);
        frame.setBounds(C.pos.frame.x, C.pos.frame.y, C.height.frame, C.width.frame);
        //frame.setSize(C.width.frame, C.height.frame);
        frame.setVisible(true);
        //frame.setLayout(null);
        frame.setExtendedState (java.awt.Frame.MAXIMIZED_BOTH);
        //frame.pack();
    }
}
