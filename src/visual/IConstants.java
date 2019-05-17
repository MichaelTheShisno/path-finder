package visual;

import java.awt.*;

public interface IConstants {
    int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    int NUM_ROWS = 45;
    int NUM_COLS = 80;
    int TILE_SIZE = SCREEN_HEIGHT/TileGrid.NUM_ROWS;
}
