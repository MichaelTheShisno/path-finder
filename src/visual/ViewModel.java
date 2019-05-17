package visual;
import java.awt.event.*;
/**
 * Class to handle the inputs of the user and relay them back to the frame.
 */
public class ViewModel implements MouseListener {
    private PathFrame pFrame;
    private boolean isMousePressed;
    public ViewModel(PathFrame pFrame) {
        this.pFrame = pFrame;
        pFrame.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
