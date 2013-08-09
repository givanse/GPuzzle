package gui.view;

import game.Utility;
import game.pieces.Square;
import gui.model.GameModel;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author givanse
 */
public class SmartCursor {

    private int boardWidth;
    private int boardHeight;
    
    private int x;
    private int y;
    private int xBeingHold;
    private int yBeingHold;
    private Square squareBeingHold;
    
    
    private Image sightCursorImage;
    private GameModel gameModel;
    
    public SmartCursor(int boardWidht, int boardHeight) {
        this.boardWidth = boardWidht;
        this.boardHeight = boardHeight;
        this.x = boardWidht - 1;
        this.y = boardHeight - 1;
        this.sightCursorImage = new ImageIcon(getClass()
                               .getResource("img/smartCursor.png")).getImage();
    }
    
    public void update() {
            this.moveRandomlyOneSpace();
    }
    
    private void moveRandomlyOneSpace() {
        Random rnd = new Random();
        
        int xOrY = rnd.nextInt(2);
        
        if(xOrY == 1) {
            int posOrNeg;
            if(this.x == this.boardWidth - 1)
                posOrNeg = 0;
            else if(this.x == 0)
                posOrNeg = 1;
            else
                posOrNeg = rnd.nextInt(2);
            
            if(posOrNeg == 1)
                this.x++;
            else
                this.x--;
        } else {
            int posOrNeg;
            if(this.y == this.boardHeight - 1)
                posOrNeg = 0;
            else if(this.y == 0)
                posOrNeg = 1;
            else
                posOrNeg = rnd.nextInt(2);
            
            if(posOrNeg == 1)
                this.y++;
            else
                this.y--;
        }
    }
    
    private void moveRandomly() {
        int availableColumns[] = new int[this.boardWidth];
        for(int i = 0; i < this.boardWidth; i++) {
            availableColumns[i] = i;
        }
        int shuffledColumns[] = Utility.shuffleArray(availableColumns);
        this.x = shuffledColumns[0];
        
        int availableRows[] = new int[this.boardHeight];
        for(int i = 0; i < this.boardHeight; i++) {
            availableRows[i] = i;
        }
        int shuffledRows[] = Utility.shuffleArray(availableRows);
        this.y = shuffledRows[0];
    }
    
    public Image getCursorImage() {
        if(this.squareBeingHold instanceof Square)
            return this.squareBeingHold.getSquareColour().getImage();
        return this.sightCursorImage;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
}
