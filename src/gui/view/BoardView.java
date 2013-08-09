package gui.view;

import game.pieces.Board;
import game.pieces.Square;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author givanse
 */
public final class BoardView extends JPanel {
   
    protected static Color BACKGROUND_COLOR = Color.DARK_GRAY;
    protected static Color BACKGROUND_GRID_COLOR = Color.LIGHT_GRAY;
    
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    
    /**
     * Canvas is the drawable area of this view.
     * The units for this values are pixels.
     */
    private final int canvasWidth;
    private final int canvasHeight;
    private final Image sightCursorImage;
    
    /* The units for the next variables are pixels. */
    private static int BORDER_WIDTH = 2;
    private static int SPACE_FILLED_BY_BORDERS = BoardView.BORDER_WIDTH * 2;    
    public static int VIEW_WIDTH  = (Square.SIZE * Board.WIDTH_IN_SQUARES) + 
                                       BoardView.SPACE_FILLED_BY_BORDERS; 
    public static int VIEW_HEIGHT = (Square.SIZE * Board.HEIGHT_IN_SQUARES) + 
                                       BoardView.SPACE_FILLED_BY_BORDERS;
    /* The units for the previous variables are pixels. */
                                    
    public BoardView() {
        this.canvasWidth  = BoardView.VIEW_WIDTH - 
                            BoardView.SPACE_FILLED_BY_BORDERS;
        this.canvasHeight = BoardView.VIEW_HEIGHT - 
                            BoardView.SPACE_FILLED_BY_BORDERS;
        this.sightCursorImage = new ImageIcon(getClass()
                                     .getResource("img/cursor.png")).getImage();
        this.changeCursorToSightIcon();
    }
    
    private void drawObjectsOnScreen() {    
        Graphics panelGraphics;
        try {
            panelGraphics = this.getGraphics();
            if(panelGraphics != null && this.offScreenImage != null) {
                panelGraphics.drawImage(this.offScreenImage, 
                                        BoardView.BORDER_WIDTH, 
                                        BoardView.BORDER_WIDTH, null);
                Toolkit.getDefaultToolkit().sync(); // Re-draw
                panelGraphics.dispose();
            }
        } catch (Exception e) {
            System.out.println("Error while refreshing: " + e.getMessage());
        }
    }
    
    private void drawObjectsInMemory(Square boardSquares[][], 
                                     ArrayList<Square> fallingSquares,
                                     SmartCursor smartCursor) {
        if(this.offScreenImage == null) {
            this.offScreenImage = this.createImage(this.canvasWidth, 
                                                   this.canvasHeight);
            if(this.offScreenImage == null) {
                System.out.println("An off screen image couldn't be created.");
                return;
            } else {
                this.offScreenGraphics = this.offScreenImage.getGraphics();
            }
        }
        this.offScreenGraphics.setColor(BoardView.BACKGROUND_COLOR);
        /* clear the canvas */
        this.offScreenGraphics.fillRect(0, 0, this.canvasWidth, 
                                              this.canvasHeight);
        this.drawBackgroundGrid();
        this.drawSquares(this.offScreenGraphics, boardSquares);
        this.drawSquares(this.offScreenGraphics, fallingSquares);
        
        /* Draw smart cursor. */
        int xPixels = (smartCursor.getX() * Square.SIZE) + Square.SIZE / 6;
        int yPixels = (smartCursor.getY() * Square.SIZE) + Square.SIZE / 6;
        Image smartCursorImage = smartCursor.getCursorImage();
        this.offScreenGraphics.drawImage(
                                      smartCursorImage, xPixels, yPixels, this);
        
        /**
         * Artificially fire the event that will start with the swap process.
         */
        this.getParent().dispatchEvent(
                new KeyEvent(this.getParent(), KeyEvent.KEY_PRESSED, 1000, 0,
                             KeyEvent.VK_SPACE, ' '));
    }
      
    private void drawBackgroundGrid() {
        this.offScreenGraphics.setColor(BoardView.BACKGROUND_GRID_COLOR);
        /* Draw vertical lines */
        int yStart = 0;
        int yEnd = BoardView.VIEW_HEIGHT - BoardView.BORDER_WIDTH;
        int i = Square.SIZE;      /* left outside just for formatting reasons */
        for( ; i < BoardView.VIEW_WIDTH; i += Square.SIZE) {
            int xStart = i;
            int xEnd = i;
            this.offScreenGraphics.drawLine(xStart, yStart, xEnd, yEnd);
        }
        /* Draw horizontal lines */
        int xStart = 0;
        int xEnd = BoardView.VIEW_WIDTH - BoardView.BORDER_WIDTH;
        i = Square.SIZE;          /* left outside just for formatting reasons */
        for( ; i < BoardView.VIEW_HEIGHT; i += Square.SIZE) {
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
    
    public void drawObjects(Square boardSquares[][], 
                            ArrayList<Square> fallingSquares,
                            SmartCursor smartCursor) {
        this.drawObjectsInMemory(boardSquares, fallingSquares, smartCursor);
        this.drawObjectsOnScreen();
    }
    
    public void drawSmartCursor(Image image, int x, int y) {
        this.offScreenGraphics.drawImage(image, x, y, this);
        this.drawObjectsOnScreen();
    }
     
    public void changeCursor(Image cursorImage) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = cursorImage.getWidth(this) / 2; 
        int y = x;
        Point cursorHotSpot = new Point(x, y);
        Cursor cursor = toolkit.createCustomCursor(
                                          cursorImage, cursorHotSpot, "Cursor");
        this.setCursor(cursor);
    }
    
    public void changeCursorToSightIcon() {
        this.changeCursor(this.sightCursorImage);
    }
    
    /**
     * 
     * @param x coordinate of the selected square
     * @param y coordinate of the selected square
     */
    public void changeCursorToSelectedSquare(Square square) {
        Image squareImage = square.getSquareColour().getImage();
        this.changeCursor(squareImage);
    }
    
    public Graphics getOffscreenGraphics() {
        return this.offScreenGraphics;
    }
    
}
