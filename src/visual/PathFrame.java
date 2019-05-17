package visual;

import javax.swing.*;

public class PathFrame extends JFrame implements IConstants {
    public PathFrame() {
        super();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new TileGrid());
        this.setVisible(true);
    }
}
