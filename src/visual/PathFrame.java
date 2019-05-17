package visual;

import javax.swing.*;
import java.awt.*;

public class PathFrame extends JFrame {
    public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static void main(String[] args) {
        PathFrame pFrame = new PathFrame();
    }
    public PathFrame() {
        super();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new TileGrid());
        this.setVisible(true);
        System.out.printf("Screen Dimensions: %d x %d\n", SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
