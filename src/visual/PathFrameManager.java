package visual;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Class to handle the inputs of the user and relay them back to the frame.
 */
public class PathFrameManager implements MouseListener {
    private PathFrame pFrame;

    public PathFrameManager(PathFrame pFrame) {
        this.pFrame = pFrame;
        pFrame.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
