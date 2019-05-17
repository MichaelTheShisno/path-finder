package visual;

import javax.swing.*;

public class PathFrame extends JFrame implements IConstants {
    public TileGrid grid;
    public PathFrameManager vm;
    public PathFrame() {
        super();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid = new TileGrid();
        vm = new PathFrameManager(this);
        this.add(grid);
        this.setVisible(true);
    }
}
