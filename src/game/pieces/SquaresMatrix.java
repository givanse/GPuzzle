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
        if(x >= this.getNumberOfRows() || y >= this.getNumberOfColumns())
            throw new IndexOutOfBoundsException(
                    "Can not add a Square in that position.");
            
        this.squares[x][y] = new Square(x, y, squareType);
        return this;
    }
    
    public boolean isPositionAvailable(int x, int y) {
        return !(this.getSquare(x, y) instanceof Square);
    }
    
    public int getNumberOfRows() {
        return this.squares.length;
    }
    
    public int getNumberOfColumns() {
        if(this.getNumberOfRows() == 0)
            return 0;
        
        return this.squares[0].length;
    }
    
    /**
     * 
     * @param coordinates 
     */
    public void deleteSquares(int coordinates[][]) {
        if(coordinates.length == 0)
            return;
        
        int totalRows = coordinates.length;
        int firstColumn = 0;
        int secondColumn = 1;
        
        for(int rowNum = 0; rowNum < totalRows; rowNum++) {
            int x = coordinates[rowNum][firstColumn];
            int y = coordinates[rowNum][secondColumn];
            this.squares[x][y] = null;
        }
    }
    
    public void moveDownFlyingSquares() {
        /* Start from the bottom */
        int lastColumn = this.getNumberOfColumns() - 1;
        for(int rowNum = 0; rowNum < this.getNumberOfRows(); rowNum++) {
            this.packColumn(rowNum, lastColumn);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(int x = 0; x < this.getNumberOfRows(); x++)
            for(int y = 0; y < this.getNumberOfColumns(); y++) {
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
