package game.patterns;

import game.pieces.Square;
import game.pieces.Square.SquareColour;
import game.pieces.SquaresMatrix;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class TetrominoTest {
    
    @Test
    public void isSquareMatchTest() {
        SquaresMatrix squares = new SquaresMatrix(0, 0);
        
        assertFalse(Tetromino.isSquareMatch(-1, 0, 
                                            SquareColour.BLUE, squares));
        assertFalse(Tetromino.isSquareMatch(0, -1,  
                                            SquareColour.BLUE, squares));
        assertFalse(Tetromino.isSquareMatch(-1, -1,  
                                            SquareColour.BLUE, squares));
        
        assertFalse(Tetromino.isSquareMatch(0, 0, null, squares));
        
        assertFalse(Tetromino.isSquareMatch(0, 0, SquareColour.BLUE, null));
        
        assertFalse(Tetromino.isSquareMatch(0, 0, null, null));
        
        assertFalse(Tetromino.isSquareMatch(0, 0, SquareColour.BLUE, squares));
        
        squares = new SquaresMatrix(1, 1);
        assertFalse(Tetromino.isSquareMatch(0, 0, SquareColour.BLUE, squares));
        
        squares = new SquaresMatrix(1, 1)
                .insertSquare(0, 0, SquareColour.YELLOW);
        assertFalse(Tetromino.isSquareMatch(0, 0, SquareColour.BLUE, squares));
        assertTrue(Tetromino.isSquareMatch(0, 0, SquareColour.YELLOW, squares));
    }
    
}