package game.patterns;

import game.pieces.Square;
import game.pieces.SquaresMatrix;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class ShapeTest {
    
    @Test
    public void patternMatchSQUARE_Test() {
        /**
         * oooo
         * oxxo
         * oxxo
         * oooo
         */
        SquaresMatrix squares = new SquaresMatrix(4, 4)
                .insert(1, 1, Square.SquareType.BLUE)
                .insert(1, 2, Square.SquareType.BLUE)
                .insert(2, 1, Square.SquareType.BLUE)
                .insert(2, 2, Square.SquareType.BLUE);
        /* pattern match a square shape */
        int x = -1, y = -1;
        String message;
        boolean expected = false;
        boolean actual;
        for(x = 0; x < 4; x++) {
            y = 0;
            message = "SQUARE (" + x + ", " + y + ")";
            actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
            assertEquals(message, expected, actual);
            message = "SQUARE (" + x + ", " + y + ")";
            y = 3;
            actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
            assertEquals(message, expected, actual);
        }
        
        x = 0; y = 1;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
        assertEquals(message, expected, actual);
        x = 0; y = 2;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
        assertEquals(message, expected, actual);
        
        x = 3; y = 1;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
        assertEquals(message, expected, actual);
        x = 3; y = 2;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
        assertEquals(message, expected, actual);
        
        expected = true;
        for(x = 1; x <= 2; x++)
            for(y = 1; y <= 2; y++) {
                actual = TetrisShape.SQUARE.isShapeFound(x, y, squares);
                message = "SQUARE (" + x + ", " + y + ")";
                assertEquals(message, expected, actual);
            }
    }
    
    @Test
    public void patternMatchS_RIGHT_H_Test() {
        /**
         * ooooo
         * ooxxo
         * oxxoo
         * ooooo
         */
        SquaresMatrix squares = new SquaresMatrix(5, 5)
                .insert(1, 2, Square.SquareType.BLUE)
                .insert(1, 3, Square.SquareType.BLUE)
                .insert(2, 1, Square.SquareType.BLUE)
                .insert(2, 2, Square.SquareType.BLUE);
        /* pattern match a S_RIGHT_H */
        String message;
        boolean expected = false;
        boolean actual;
        int x, y;
        for(x = 0; x < squares.getWidth(); x++) {
            y = 0;
            actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
            message = "S_RIGHT_H (" + x + ", " + y + ")";
            assertEquals(message, expected, actual);
            y = 4;
            actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
            message = "S_RIGHT_H (" + x + ", " + y + ")";
            assertEquals(message, expected, actual);
        }
        
        x = 0; y = 1;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 0; y = 2;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 1; y = 1;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        
        x = 3; y = 2;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 4; y = 1;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 4; y = 2;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        
        expected = true;
        x = 1; y = 2;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 2; y = 1;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 2; y = 2;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
        x = 3; y = 1;
        actual = TetrisShape.S_RIGHT_H.isShapeFound(x, y, squares);
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        assertEquals(message, expected, actual);
    }
    
}