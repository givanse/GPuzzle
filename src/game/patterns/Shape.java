package game.patterns;

/**
 * The parent/interface for all the different Tetris shapes
 * 
 * @author givanse
 */
public abstract class Shape {
        
    abstract boolean findPatternMatch(int x, int y, boolean matrix[][]);
    
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

}