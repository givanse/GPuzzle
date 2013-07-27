package game.pieces;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
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
    protected void packColumn(int x, int y) {
        /* for each square in this column, from bottom to top */
        for(int currY = y; currY >= 1; currY--) {
            Square currentSquare = this.getSquare(x, currY);
            if(!(currentSquare instanceof Square)) {
                foundNotNullUpperSquare:/* iterate upwards until valid square */
                for(int upperY = currY - 1; upperY >= 0; upperY--) {
                    Square upperSquare = this.getSquare(x, upperY);
                    if(upperSquare instanceof Square) {
                        /* Update current with upper colour */
                        this.insertSquare(
                                x, currY, upperSquare.getSquareColour());
                        /* Delete upper square */
                        this.squares[x][upperY] = null;
                        break foundNotNullUpperSquare;
                    }
                }
            }
        }
    }
    
    /* Public methods */
    
    public Square getSquare(int x, int y) {
        return this.squares[x][y];
    }
    
    /**
     * Adds a new square to the matrix. If a square already exists in the (x, y)
     * position, it is overwritten.
     * 
     * @param x
     * @param y
     * @param square 
     */
    public SquaresMatrix insertSquare(int x, int y, SquareColour squareType) {
        if(x >= this.getWidth() || y >= this.getHeight())
            throw new IndexOutOfBoundsException(
                    "Can not add a Square in that position.");
            
        this.squares[x][y] = new Square(x, y, squareType);
        return this;
    }
    
    public boolean isPositionAvailable(int x, int y) {
        return !(this.getSquare(x, y) instanceof Square);
    }
    
    public int getWidth() {
        return this.squares.length;
    }
    
    public int getHeight() {
        return this.squares[0].length;
    }
    
    public void deleteSquares(int squares[][]) {
        if(squares[0] == null)
            return;
        int width = squares[0].length;
        int height = squares.length;
        for(int x = 0; x < width; x++)
            for(int y = 0; x < height; y++)
                this.squares[x][y] = null;
    }
    
    public void moveDownFlyingSquares() {
        int constantY = this.getHeight() - 1;        /* Start from the bottom */
        for(int x = 0; x < this.getWidth(); x++) {
            this.packColumn(x, constantY);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(int x = 0; x < this.getWidth(); x++)
            for(int y = 0; y < this.getHeight(); y++) {
                Square square = this.getSquare(x, y);
                str += (square == null) ? 
                        "x=" + x + ", y=" + y + ", null, " : 
                        square.toString() + ", ";
            }
        return "SquaresMatrix{" + "squares=" + str + '}';
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
