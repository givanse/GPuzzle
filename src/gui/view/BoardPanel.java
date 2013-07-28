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
    
    /* The units for the next variables are pixels. */
    private static int BORDER_WIDTH = 2;
    public static int SPACE_FILLED_BY_BORDERS = BoardPanel.BORDER_WIDTH * 2;    
    public static int CANVAS_WIDTH  = (Square.SIZE * Board.WIDTH_IN_SQUARES) + 
                                       BoardPanel.SPACE_FILLED_BY_BORDERS; 
    public static int CANVAS_HEIGHT = (Square.SIZE * Board.HEIGHT_IN_SQUARES) + 
                                       BoardPanel.SPACE_FILLED_BY_BORDERS; 
                                    
    public BoardPanel() {
        this.gameService = new GameService(this);
        this.drawableWidth  = BoardPanel.CANVAS_WIDTH - 
                              BoardPanel.SPACE_FILLED_BY_BORDERS;
        this.drawableHeight = BoardPanel.CANVAS_HEIGHT - 
                              BoardPanel.SPACE_FILLED_BY_BORDERS;
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
        int yStart = 0;
        int yEnd = BoardPanel.CANVAS_HEIGHT - BoardPanel.BORDER_WIDTH;
        int i = Square.SIZE;      /* left outside just for formatting reasons */
        for( ; i < BoardPanel.CANVAS_WIDTH; i += Square.SIZE) {
            int xStart = i;
            int xEnd = i;
            this.offScreenGraphics.drawLine(xStart, yStart, xEnd, yEnd);
        }
        /* Draw horizontal lines */
        int xStart = 0;
        int xEnd = BoardPanel.CANVAS_WIDTH - BoardPanel.BORDER_WIDTH;
        i = Square.SIZE;          /* left outside just for formatting reasons */
        for( ; i < BoardPanel.CANVAS_HEIGHT; i += Square.SIZE) {
            yStart = i; 
            yEnd = i;
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
