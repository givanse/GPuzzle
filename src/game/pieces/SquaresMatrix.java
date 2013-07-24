package game.pieces;

import game.pieces.Square.SquareColour;
import java.util.Arrays;

/**
 * This data structure is just a two dimensional array of Squares that provides
 * convenient methods.
 * 
 * @author givanse
 */
public class SquaresMatrix {

    private final Square squares[][];
    
    public SquaresMatrix(int width, int height) {
        this.squares = new Square[width][height];
    }
    
    /* Public methods */
    
    public Square getSquare(int x, int y) {
        return this.squares[x][y];
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param square 
     */
    public SquaresMatrix setSquare(int x, int y, SquareColour squareType) {
        this.squares[x][y] = new Square(x, y, squareType);
        return this;
    }
    
    public boolean isPositionAvailable(int x, int y) {
        return this.getSquare(x, y) ==  null;
    }
    
    public int getWidth() {
        return this.squares[0].length;
    }
    
    public int getHeight() {
        return this.squares.length;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this) 
            return true;
        if(!(obj instanceof SquaresMatrix))
            return false;
        SquaresMatrix other = (SquaresMatrix) obj;
        for(int x = 0; x < this.getWidth(); x++) {
            for(int y = 0; y < this.getHeight(); y++) {
                Square s1 = this.getSquare(x, y);
                Square s2 = other.getSquare(x, y);
                if(!s1.equals(s2))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.squares);
        return hash;
    }
    
    public void deleteSquares(int squares[][]) {
        
    }
}
