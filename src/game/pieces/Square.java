package game.pieces;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author givanse
 */
public class Square {
    
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

    Square(SquareType color) {
    }
    
}
