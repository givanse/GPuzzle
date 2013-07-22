package game.pieces;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author givanse
 */
public class Square {
    
    private int x; /* position in a board, not pixels */
    private int y; /* position in a board, not pixels */
    private boolean isFalling; // defaults to false
    private SquareType squareType;
    
    public enum SquareType {
        BLUE("img/blue.png"),  
        GREEN("img/green.png"), 
        PINK("img/pink.png"), 
        YELLOW("img/yellow.png"), 
        RED("img/red.png");
        
        /* Add an image property to the elements of this Enum */
        Image image;
        SquareType(String imageFilePath) {
            this.image = new ImageIcon(
                              getClass().getResource(imageFilePath)).getImage();
        }
        public Image getImage() { return this.image; }
    }

    public static int SIZE = 32; // pixels

    Square(SquareType squareType) {
        this(0, 0, squareType);
    }
    
    Square(int x, int y, SquareType squareType) {
        this.x = x;
        this.y = y;
        this.squareType = squareType;
        this.isFalling = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        x = x < 0 ? 0 : x;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        y = y < 0 ? 0 : y;
        this.y = y;
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public boolean isFalling() {
        return this.isFalling;
    }

    public void stopFalling() {
        this.isFalling = false;
    }
    
}
