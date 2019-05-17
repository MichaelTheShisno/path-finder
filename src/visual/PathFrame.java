package visual;

import javax.swing.*;

public class PathFrame extends JFrame {
    public static void main(String[] args) {
        PathFrame pFrame = new PathFrame();
    }
    public PathFrame() {
        super();
        this.setSize(1920, 1080);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
