package gui.view;

import game.GameService;
import game.pieces.Board;
import game.pieces.Square;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author givanse
 */
public class BoardPanel extends JPanel {
   
    protected static Color BACKGROUND_COLOR = Color.DARK_GRAY;
    protected static Color BACKGROUND_GRID_COLOR = Color.LIGHT_GRAY;
    
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private GameService gameService;
    private final int drawableWidth;
    private final int drawableHeight;
    
    protected static int BORDER_WIDTH = 2;
    public static int WIDTH = (Square.SIZE * Board.WIDTH);          /* pixels */
    public static int HEIGHT = (Square.SIZE * Board.HEIGHT);       /* pixels */
                                    
    public BoardPanel() {
        this.gameService = new GameService(this);
        int borderSpace = BoardPanel.BORDER_WIDTH * 2;
        this.drawableWidth = BoardPanel.WIDTH - borderSpace;
        this.drawableHeight = BoardPanel.HEIGHT - borderSpace;
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
                panelGraphics.drawImage(this.offScreenImage, 
                                        BoardPanel.BORDER_WIDTH, 
                                        BoardPanel.BORDER_WIDTH, null);
                Toolkit.getDefaultToolkit().sync(); // Re-draw
                panelGraphics.dispose();
            }
        } catch (Exception e) {
            System.out.println("Error while refreshing: " + e.getMessage());
        }
    }
    
    private void drawObjectsInMemory() {
        if(this.offScreenImage == null) {
            this.offScreenImage = this.createImage(this.drawableWidth, 
                                                   this.drawableHeight);
            if(this.offScreenImage == null) {
                System.out.println("An off screen image couldn't be created.");
                return;
            } else {
                this.offScreenGraphics = this.offScreenImage.getGraphics();
            }
        }
        this.offScreenGraphics.setColor(BoardPanel.BACKGROUND_COLOR);
        /* clear the canvas */
        this.offScreenGraphics.fillRect(0, 0, this.drawableWidth, 
                                              this.drawableHeight);
        this.drawBackgroundGrid();
        this.gameService.drawObjects(this.offScreenGraphics);
    }
      
    private void drawBackgroundGrid() {
        this.offScreenGraphics.setColor(BoardPanel.BACKGROUND_GRID_COLOR);
        /* Draw vertical lines */
        int yStart = 0, yEnd = BoardPanel.HEIGHT - BoardPanel.BORDER_WIDTH;
        for(int i = Square.SIZE; i < BoardPanel.WIDTH; i += Square.SIZE) {
            int xStart = i, xEnd = i;
            this.offScreenGraphics.drawLine(xStart, yStart, xEnd, yEnd);
        }
        /* Draw horizontal lines */
        int xStart = 0, xEnd = BoardPanel.WIDTH - BoardPanel.BORDER_WIDTH;
        for(int i = Square.SIZE; i < BoardPanel.HEIGHT; i += Square.SIZE) {
            yStart = i; yEnd = i;
            this.offScreenGraphics.drawLine(xStart, yStart, xEnd, yEnd);
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
