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
    
    /**
     * Defined as protected just for testing purposes, is should be private.
     * 
     * @param x
     * @param y 
     */
    protected void shiftColumn(int x, int y) { 
        for(int i = y; i > 0; i--) { /* don't reach zero */
            // TODO: Square upperSquare = this.squares[x][i-1];
            // this.squares[x][i] = upperSquare;
        }
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
    
    public void deleteSquares(int squares[][]) {
        
    }
    
    public void moveDownFlyingSquares() {
        for(int x = this.getWidth() - 1; x >= 0; x--)
            for(int y = this.getHeight() - 1; y > 0; y--) {  /* don't reach 0 */
                if(this.isPositionAvailable(x, y))
                    this.shiftColumn(x, y);
            }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Arrays.deepHashCode(this.squares);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SquaresMatrix other = (SquaresMatrix) obj;
        if (!Arrays.deepEquals(this.squares, other.squares)) {
            return false;
        }
        return true;
    }
}
