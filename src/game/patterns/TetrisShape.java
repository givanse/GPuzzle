package game.patterns;
    
import game.pieces.Square;
import game.pieces.SquaresMatrix;

/**
 * The last letter of each tetromino type, _H or _V, stands for 
 * Horizontal or Vertical.
 * 
 * The suffix _FM for the L shapes stands for Flipped Mirror. If put 
 * together, an L shape with its flipped mirror; it completes a 4x2 
 * rectangle. Like this:    
 * []<><><>
 * [][][]<>
 * 
 * Note that the shape T is not included.
 * 
 * @author givanse
 */
public enum TetrisShape {      
    SQUARE(new SSquare()),
    STRAIGHT_H(new SSquare()), 
    STRAIGHT_V(new SSquare()),
    /* S shapes */
    S_RIGHT_H(new SSquare()), 
    S_RIGHT_V(new SSquare()), 
    S_LEFT_H(new SSquare()), 
    S_LEFT_V(new SSquare()),
    /* L shapes */
    /* right */
    L_RIGHT_H(new SSquare()), 
    L_RIGHT_V(new SSquare()), 
    L_RIGHT_H_FM(new SSquare()), 
    L_RIGHT_V_FM(new SSquare()),
    /* left */
    L_LEFT_H(new SSquare()), 
    L_LEFT_V(new SSquare()), 
    L_LEFT_H_FM(new SSquare()), 
    L_LEFT_V_FM(new SSquare());

    private Shape shape;

    TetrisShape(Shape shape) {
        this.shape = shape;
    }

    public boolean isShapeFound(int x, int y, SquaresMatrix squares) {
        return this.shape.isShapeFound(x, y, squares);
    }
}
