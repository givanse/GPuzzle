package gui.view;

import game.pieces.Board;
import game.pieces.Square;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author givanse
 */
public final class BoardPanel extends JPanel {
   
    protected static Color BACKGROUND_COLOR = Color.DARK_GRAY;
    protected static Color BACKGROUND_GRID_COLOR = Color.LIGHT_GRAY;
    
    private Image offScreenImage;
    private Graphics offScreenGraphics;
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
        this.drawableWidth  = BoardPanel.CANVAS_WIDTH - 
                              BoardPanel.SPACE_FILLED_BY_BORDERS;
        this.drawableHeight = BoardPanel.CANVAS_HEIGHT - 
                              BoardPanel.SPACE_FILLED_BY_BORDERS;
        this.changeCursorToDefault();
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
    
    private void drawObjectsInMemory(Square boardSquares[][], 
                                     ArrayList<Square> fallingSquares) {
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
        this.drawSquares(this.offScreenGraphics, boardSquares);
        this.drawSquares(this.offScreenGraphics, fallingSquares);
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
    
    private void drawSquare(Graphics graphics, Square square) {
        int xPixels = (square.getX() * Square.SIZE);
        int yPixels = (square.getY() * Square.SIZE);
        graphics.drawImage(square.getSquareColour().getImage(), 
                           xPixels, yPixels, this);
    }
    
    private void drawSquares(Graphics graphics, ArrayList<Square> squares) {
        for(Square s : squares) {
            this.drawSquare(graphics, s);
        }
    }
    
    private void drawSquares(Graphics graphics, Square squares[][]) {
        for(Square[] row : squares) {
            for(Square s : row) {
                if(s == null)
                    continue;
                else
                    this.drawSquare(graphics, s);
            }
        }
    }
    
    /* Public methods */
    
    public void changeCursor(Image cursorImage) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = cursorImage.getWidth(this) / 2; 
        int y = x;
        Point cursorHotSpot = new Point(x, y);
        Cursor cursor = toolkit.createCustomCursor(
                                          cursorImage, cursorHotSpot, "Cursor");
        this.setCursor(cursor);
    }
    
    public void changeCursorToDefault() {
        Image cursorImage = new ImageIcon(
                           getClass().getResource("img/cursor.png")).getImage();
        this.changeCursor(cursorImage);
    }
    
    public void drawObjects(Square boardSquares[][], 
                            ArrayList<Square> fallingSquares) {
        this.drawObjectsInMemory(boardSquares, fallingSquares);
        this.drawObjectsOnScreen();
    }
    
}
