package game.patterns;

/**
 *
 * @author givanse
 */
public class SSquare extends Shape {
    
    /**
     * 
     * @param x
     * @param y
     * @param matrix
     * @return 
     */
    @Override
    boolean findPatternMatch(int x, int y, boolean matrix[][]) {
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
        if(isMatch) return true;

        /*  last chance */
        for(int i = x; i < x+2; i++)
            for(int j = y; j < y+2; j++)
                if(matrix[i][j] == false)
                    return false;
        return true;
    }
}