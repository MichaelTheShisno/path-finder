package visual;

import javax.swing.*;

/**
 * Frame which contains the main content frame.
 */
public class PathFrame extends JFrame implements IConstants {
    public PathFrame() {
        super();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new PathPanel());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
    }
}
