package visual;

import javax.swing.*;
import java.awt.*;

class TileGrid extends JPanel implements IConstants {
    TileGrid() {
        super();
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                this.add(new Tile(TILE_SIZE));
            }
        }
    }
}
