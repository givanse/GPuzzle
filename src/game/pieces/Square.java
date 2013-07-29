package game.pieces;

import java.awt.Image;
import java.util.Objects;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author givanse
 */
public class Square {
    
    private int x; /* position in a board, not pixels */
    private int y; /* position in a board, not pixels */
    private boolean isFalling; // defaults to false
    private SquareColour squareColour;
    
    public enum SquareColour {
        BLUE("img/blue.png"),  
        GREEN("img/green.png"), 
        PINK("img/pink.png"), 
        YELLOW("img/yellow.png"), 
        RED("img/red.png");
        
        /* Add an image property to the elements of this Enum */
        Image image;
        SquareColour(String imageFilePath) {
            this.image = new ImageIcon(
                              getClass().getResource(imageFilePath)).getImage();
        }
        
        public Image getImage() { return this.image; }
        
        public static SquareColour getRandomColour() {
            Square.SquareColour sqrColours[] = Square.SquareColour.values();
            Random random = new Random(System.currentTimeMillis());
            int randomColourNumber = random.nextInt(sqrColours.length);
            return sqrColours[randomColourNumber];
        }
    }

    public static int SIZE = 32; // pixels

    Square(SquareColour squareType) {
        this(0, 0, squareType);
    }
    
    Square(int x, int y, SquareColour squareType) {
        this.x = x;
        this.y = y;
        this.squareColour = squareType;
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

    public SquareColour getSquareColour() {
        return squareColour;
    }

    public boolean isFalling() {
        return this.isFalling;
    }

    public void stopFalling() {
        this.isFalling = false;
    }

    @Override
    public String toString() {
        return "Square{" + "x=" + x + ", y=" + y + 
               ", squareColour=" + squareColour + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        hash = 67 * hash + (this.isFalling ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.squareColour);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Square other = (Square) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.isFalling != other.isFalling) {
            return false;
        }
        if (this.squareColour != other.squareColour) {
            return false;
        }
        return true;
    }
 
}
