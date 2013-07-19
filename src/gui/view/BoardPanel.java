package gui.view;

import game.GameService;
import game.pieces.Square;
import game.pieces.Square.SquareType;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.EnumSet;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author givanse
 */
public class BoardPanel extends JPanel {
    
    protected static Color BACKGROUND_COLOR = Color.DARK_GRAY;
    
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private GameService gameService;
    
    public BoardPanel() {
        this.gameService = new GameService(this);
        /* add custom cursor */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = new ImageIcon(
                           getClass().getResource("img/cursor.png")).getImage();
        int x = cursorImage.getWidth(this) / 2; 
        int y = x;
        Point cursorHotSpot = new Point(x, y);
        Cursor cursor = toolkit.createCustomCursor(
                                          cursorImage, cursorHotSpot, "Cursor");
        this.setCursor(cursor);
    }
    
    private void drawObjectsOnScreen() {    
        Graphics panelGraphics;
        try {
            panelGraphics = this.getGraphics();
            if(panelGraphics != null && this.offScreenImage != null) {
                panelGraphics.drawImage(this.offScreenImage, 0, 0, null);
                Toolkit.getDefaultToolkit().sync(); // Re-draw
                panelGraphics.dispose();
            }
        } catch (Exception e) {
            System.out.println("Error while refreshing: " + e.getMessage());
        }
    }
    
    private void drawObjectsInMemory() {
        if(this.offScreenImage == null) {
            this.offScreenImage = this.createImage(game.pieces.Board.WIDTH, 
                                                   game.pieces.Board.HEIGHT);
            if(this.offScreenImage == null) {
                System.out.println("An off screen image couldn't be created.");
                return;
            } else {
                this.offScreenGraphics = this.offScreenImage.getGraphics();
            }
        }
        this.offScreenGraphics.setColor(BoardPanel.BACKGROUND_COLOR);
        /* clear the canvas */
        this.offScreenGraphics.fillRect(0, 0, game.pieces.Board.WIDTH, 
                                              game.pieces.Board.HEIGHT);
        /* just some provitional random drawings */
        int x = 0, y = 0;
        for(SquareType st : EnumSet.allOf(SquareType.class)) {
            Image square = st.getImage();
            this.offScreenGraphics.drawImage(square, x, y, this);
            x += Square.SIZE;
            y += Square.SIZE;
        }
    }
      
    /* Public methods */
      
    @Override
    public void addNotify() {
        super.addNotify();
        this.gameService.start();
    }
    
    public void drawObjects() {
     this.drawObjectsInMemory();
     this.drawObjectsOnScreen();
    }
    
}
