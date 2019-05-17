package visual;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class Tile extends JComponent {
    private final int size;

    Tile(int size) {
        super();
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Rectangle tile = new Rectangle(size, size);
        g2.draw(tile);
    }
}
