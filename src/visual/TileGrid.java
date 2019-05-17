package visual;

import javax.swing.*;
import java.awt.*;

class TileGrid extends JPanel implements IConstants {
    public Tile[][] tileMatrix;
    TileGrid() {
        super();
        tileMatrix = new Tile[NUM_ROWS][NUM_COLS];
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tileMatrix[row][col] = new Tile(TILE_SIZE);
                this.add(tileMatrix[row][col]);
            }
        }
    }
}
