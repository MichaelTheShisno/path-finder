package visual;

import java.awt.Color;
import java.awt.Toolkit;

/**
 * Interface used to store constants used by all visual classes.
 */
public interface IConstants {
    int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    double ASPECT_RATIO = (double) SCREEN_WIDTH/SCREEN_HEIGHT;
    int NUM_ROWS = 27;
    int NUM_COLS = (int) (NUM_ROWS*ASPECT_RATIO);
    int TILE_SIZE = SCREEN_HEIGHT/TileGrid.NUM_ROWS;
    int START_ROW = NUM_ROWS/2;
    int START_COL = NUM_COLS/4;
    int END_ROW = NUM_ROWS/2;
    int END_COL = (3*NUM_COLS)/4;
    int MENU_X = SCREEN_WIDTH/4;
    int MENU_Y = TILE_SIZE;
    int MENU_WIDTH = SCREEN_WIDTH/2;
    int MENU_HEIGHT = (8*SCREEN_HEIGHT)/45;
    int STROKE_WIDTH = (TILE_SIZE/8);

    // Menu Colors
    Color menuBackgroundColor = new Color(0, 0, 0, 0.35f);
    // Colors used to fill tiles on the grid.
    Color normalColor = Color.WHITE;
    Color blockedColor = Color.GRAY;
    Color startColor = new Color(0, 221, 0);
    Color endColor = new Color(238, 68, 0);
    Color openColor = new Color(152, 251, 152);
    Color closedColor = new Color(175, 238, 238);
    Color failedColor = new Color(255, 136, 136);
    Color testedColor = new Color(229, 229, 229);
    Color lineColor = Color.yellow;
}
