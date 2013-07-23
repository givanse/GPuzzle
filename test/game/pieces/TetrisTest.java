package game.pieces;

import game.pieces.Tetris.TetrisShape;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author givanse
 */
public class TetrisTest {
    
    private final boolean square[][] = new boolean[][] {
            {false, false, false, false},
            {false, true , true , false},
            {false, true , true , false},
            {false, false, false, false}
        };

    private final boolean sRight[][] = new boolean[][] {
            {false, false, false, false, false},
            {false, false, true , true , false},
            {false, true , true , false, false},
            {false, false, false, false, false}
        };
    
    @Test
    public void shapeDefinitionsTest() {
        int expectedSize = 6;
        boolean shapeCutterLLeftV[][] = TetrisShape.L_LEFT_V.getShapeCutter();
        int actualSize = shapeCutterLLeftV[0].length * shapeCutterLLeftV.length;
        assertEquals(expectedSize, actualSize);
        
        boolean shapeCutterLLeftVFM[][] = TetrisShape.
                                          L_LEFT_V_FM.getShapeCutter();
        actualSize = shapeCutterLLeftVFM[0].length * shapeCutterLLeftVFM.length;
        assertEquals(expectedSize, actualSize);
    }
    
    @Test
    public void isShapeFoundTest() {
        /* pattern match a square shape */
        int x = 0, y = 0;
        String message;
        boolean expected = false;
        boolean actual;
        for(x = 0; x < 4; x++) {
            y = 0;
            message = "SQUARE (" + x + ", " + y + ")";
            actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
            assertEquals(message, expected, actual);
            message = "SQUARE (" + x + ", " + y + ")";
            y = 3;
            actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
            assertEquals(message, expected, actual);
        }
        
        x = 0; y = 1;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
        assertEquals(message, expected, actual);
        x = 0; y = 2;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
        assertEquals(message, expected, actual);
        
        x = 3; y = 1;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
        assertEquals(message, expected, actual);
        x = 3; y = 2;
        message = "SQUARE (" + x + ", " + y + ")";
        actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
        assertEquals(message, expected, actual);
        
        expected = true;
        for(x = 1; x <= 2; x++)
            for(y = 1; y <= 2; y++) {
                actual = TetrisShape.SQUARE.findPatternMatch(x, y, this.square);
                message = "SQUARE (" + x + ", " + y + ")";
                assertEquals(message, expected, actual);
            }
        
        /* pattern match a S_RIGHT_H */
        message = "S_RIGHT_H (" + x + ", " + y + ")";
        expected = false;
        for(x = 0; x < this.sRight[0].length; x++) {
            y = 0;
            actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
            assertEquals(message, expected, actual);
            y = 4;
            actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
            assertEquals(message, expected, actual);
        }
        
        x = 0; y = 1;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
        assertEquals(message, expected, actual);
        x = 0; y = 2;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
        assertEquals(message, expected, actual);
        x = 1; y = 1;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight); 
        assertEquals(message, expected, actual);
        
        x = 3; y = 2;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
        assertEquals(message, expected, actual);
        x = 4; y = 1;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
        assertEquals(message, expected, actual);
        x = 4; y = 2;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight);
        assertEquals(message, expected, actual);
        
        expected = true;
        x = 1; y = 2;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight); 
        assertEquals(message, expected, actual);
        x = 2; y = 1;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight); 
        assertEquals(message, expected, actual);
        x = 2; y = 2;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight); 
        assertEquals(message, expected, actual);
        x = 3; y = 1;
        actual = TetrisShape.S_RIGHT_H.findPatternMatch(x, y, this.sRight); 
        assertEquals(message, expected, actual);
    }
    
}