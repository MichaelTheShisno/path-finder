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

    // Animation Constants
    int BASE_DELAY = 4;
    int FAIL_BLINKS = 3;
    int FAIL_DELAY = 250;

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

    int[] lights = {
        0,   0,   0,   0,   0,   1,   1,   2,
        2,   3,   4,   5,   6,   7,   8,   9,
        11,  12,  13,  15,  17,  18,  20,  22,
        24,  26,  28,  30,  32,  35,  37,  39,
        42,  44,  47,  49,  52,  55,  58,  60,
        63,  66,  69,  72,  75,  78,  81,  85,
        88,  91,  94,  97, 101, 104, 107, 111,
        114, 117, 121, 124, 127, 131, 134, 137,
        141, 144, 147, 150, 154, 157, 160, 163,
        167, 170, 173, 176, 179, 182, 185, 188,
        191, 194, 197, 200, 202, 205, 208, 210,
        213, 215, 217, 220, 222, 224, 226, 229,
        231, 232, 234, 236, 238, 239, 241, 242,
        244, 245, 246, 248, 249, 250, 251, 251,
        252, 253, 253, 254, 254, 255, 255, 255,
        255, 255, 255, 255, 254, 254, 253, 253,
        252, 251, 251, 250, 249, 248, 246, 245,
        244, 242, 241, 239, 238, 236, 234, 232,
        231, 229, 226, 224, 222, 220, 217, 215,
        213, 210, 208, 205, 202, 200, 197, 194,
        191, 188, 185, 182, 179, 176, 173, 170,
        167, 163, 160, 157, 154, 150, 147, 144,
        141, 137, 134, 131, 127, 124, 121, 117,
        114, 111, 107, 104, 101,  97,  94,  91,
        88,  85,  81,  78,  75,  72,  69,  66,
        63,  60,  58,  55,  52,  49,  47,  44,
        42,  39,  37,  35,  32,  30,  28,  26,
        24,  22,  20,  18,  17,  15,  13,  12,
        11,   9,   8,   7,   6,   5,   4,   3,
        2,   2,   1,   1,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0,
        0,   0,   0,   0,   0,   0,   0,   0};
}
