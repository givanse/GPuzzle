package gui.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

/**
 *
 * @author givanse
 */
public class BoardMouseListener extends MouseAdapter {

    private JTextArea logTextArea;
    
    public BoardMouseListener(JTextArea logTextArea) {
        this.logTextArea = logTextArea; 
    }
    
    @Override
    public void mouseClicked(MouseEvent evt) {
        String str = "(" + evt.getX() + ", " + evt.getY() + ")\n";
        this.logTextArea.append(str);
    }
}
