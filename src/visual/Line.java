package visual;

import javax.swing.*;
import java.awt.*;

public class Line extends JComponent implements IConstants {
    private final int r1, c1;
    private final int r2, c2;

    public Line(Tile tile1, Tile tile2) {
        super();
        this.r1 = tile1.getX() + (TILE_SIZE / 2);
        this.c1 = tile1.getY() + (TILE_SIZE / 2);
        this.r2 = tile2.getX() + (TILE_SIZE / 2);
        this.c2 = tile2.getY() + (TILE_SIZE / 2);
    }

    public String toString() {
        return String.format("(%d,%d)->(%d,%d)",r1,c1,r2,c2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(lineColor);
        g2.drawLine(r1, c1, r2, c2);
    }
}
