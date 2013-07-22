package game.pieces;

/**
 *
 * @author givanse
 */
public class Tetris {
    
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
     * All the shapes in one matrix:
     *  _ _ _ _
     * |_|_|_|_|
     * |_|_|_|
     * |_|_|
     * |_|
     */
    public enum TetrisShape {
        
        SQUARE(new boolean[][]{
            {true, true}, 
            {true, true}}),
        STRAIGHT_H(new boolean[][]{
            {true, true, true, true}}), 
        STRAIGHT_V(new boolean[][]{
            {true},
            {true},
            {true},
            {true}}),
        
        /* S shapes */
        S_RIGHT_H(new boolean[][]{
            {false, true, true }, 
            {true , true, false}}), 
        S_RIGHT_V(new boolean[][]{
            {true , false}, 
            {true , true }, 
            {false, true }}), 
        S_LEFT_H(new boolean[][]{
            {true , true, false}, 
            {false, true, true }}), 
        S_LEFT_V(new boolean[][]{
            {false, true }, 
            {true , true }, 
            {true , false}}), 
        
        /* L shapes */
        /* right */
        L_RIGHT_H(new boolean[][]{
            {true, false, false}, 
            {true, true , true }}), 
        L_RIGHT_V(new boolean[][]{
            {false, true}, 
            {false, true}, 
            {true , true}}), 
        L_RIGHT_H_FM(new boolean[][]{
            {true , true , true },
            {false, false, true}}), 
        L_RIGHT_V_FM(new boolean[][]{
            {true, true}, 
            {true, false}, 
            {true, false}}), 
        
        /* left */
        L_LEFT_H(new boolean[][]{
            {false, false, true}, 
            {true , true , true}}), 
        L_LEFT_V(new boolean[][]{
            {true, false}, 
            {true, false}, 
            {true, true }}), 
        L_LEFT_H_FM(new boolean[][]{
            {true, true , true },
            {true, false, false}}), 
        L_LEFT_V_FM(new boolean[][]{
            {true , true}, 
            {false, true}, 
            {false, true}});
        
        private final boolean shapeCutter[][];
        
        /**
         * 
         * @param shapeCutter Used as a "cookie cutter" for pattern matching.
         */
        TetrisShape(boolean shapeCutter[][]) {
            this.shapeCutter = shapeCutter;
        }
        
        public boolean isShapeFound(int x, int y, boolean matrix[][]) {
            return false;
        }
        
        public boolean[][] getShapeCutter() {
            return this.shapeCutter;
        }
        
    }
    
}
