package visual;

import java.awt.*;

public interface IConstants {
    int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    int NUM_ROWS = 45;
    int NUM_COLS = 80;
    int TILE_SIZE = SCREEN_HEIGHT/TileGrid.NUM_ROWS;
    int START_ROW = NUM_ROWS / 2;
    int START_COL = NUM_COLS / 4;
    int END_ROW = NUM_ROWS / 2;
    int END_COL = (3 * NUM_COLS) / 4;

    // Colors used to fill tiles on the grid.
    Color normalColor = Color.WHITE;
    Color blockedColor = Color.GRAY;
    Color startColor = new Color(0, 221, 0);
    Color endColor = new Color(238, 68, 0);
    Color openColor = new Color(152, 251, 152);
    Color closedColor = new Color(175, 238, 238);
    Color failedColor = new Color(255, 136, 136);
    Color testedColor = new Color(229, 229, 229);
}
