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
            {true, true}}) {
                @Override
                protected boolean findPatternMatch(int x, int y, 
                                                   boolean matrix[][]) {
                    boolean isMatch = true;
                    upwardLeft:
                    for(int i = x; i > x-2; i--)
                        for(int j = y; j > y-2; j--)
                            if(matrix[i][j] == false) {
                                isMatch =  false;
                                break upwardLeft;
                            }
                    if(isMatch) return true; else isMatch = true;
                    
                    upwardRight:
                    for(int i = x; i < x+2; i++)
                        for(int j = y; j > y-2; j--)
                            if(matrix[i][j] == false) {
                                isMatch = false;
                                break upwardRight;
                            }
                    if(isMatch) return true; else isMatch = true;
                    
                    downwardLeft:
                    for(int i = x; i > x-2; i--)
                        for(int j = y; j < y+2; j++)
                            if(matrix[i][j] == false) {
                                isMatch = false;
                                break downwardLeft;
                            }
                    if(isMatch) return true; else isMatch = true;
                    
                    /*  last chance */
                    for(int i = x; i < x+2; i++)
                        for(int j = y; j < y+2; j++)
                            if(matrix[i][j] == false)
                                return false;
                    return true;
                }
            },
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
            {true , true, false}}) {
                @Override
                protected boolean findPatternMatch(int x, int y, 
                                                   boolean matrix[][]) {
                    return false;
                }
        }, 
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
        
        protected boolean findPatternMatch(int x, int y, boolean matrix[][]) {
            throw new Error("You need to override this method.");
        }
        
        /* Public methods */
        
        /**
         *
         * @param x
         * @param y
         * @param matrix
         * @return true if a match was found, false otherwise
         * @throws Exception if this method is not override.
         */
        public boolean isShapeFound(int x, int y, boolean matrix[][]) {
            if(matrix[x][y] == false)
                return false;
            return this.findPatternMatch(x, y, matrix);
        }
        
        public boolean[][] getShapeCutter() {
            return this.shapeCutter;
        }
        
    }
    
}
