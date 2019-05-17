package visual;

import javax.swing.*;
import java.awt.*;

public class TileGrid extends JPanel {
    public static final int NUM_ROWS = 45;
    public static final int NUM_COLS = 80;
    public static final int SIZE = PathFrame.SCREEN_HEIGHT/TileGrid.NUM_ROWS;

    public TileGrid() {
        super();
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                this.add(new Tile(SIZE));
            }
        }
    }
}
