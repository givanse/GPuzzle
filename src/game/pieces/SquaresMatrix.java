package game.pieces;

import game.pieces.Square.SquareColour;
import java.util.ArrayList;
import java.util.Arrays;

/**
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
    
    public int[] getAvailableTopColumns() {
        if(this.getNumberOfColumns() < 1)
            return null;
        
        int row = 0;
        ArrayList<Integer> columnsList = 
                                       new ArrayList(this.getNumberOfRows());
        for(int column = 0; column < this.getNumberOfColumns(); column++) {
            if(this.isPositionAvailable(column, row))
                columnsList.add(column);
        }
        
        /* ArrayList to Array */
        int availableColumns[] = new int[columnsList.size()];
        int i = 0;
        for(Integer columnNumber : columnsList) {
            availableColumns[i++] = columnNumber;
        }
        
        return availableColumns;
    }
    
    public Square getSquare(int x, int y) {
        return this.squares[x][y];
    }
    
    public Square[][] getSquares() {
        return this.squares;
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
        if(x >= this.getNumberOfColumns() || y >= this.getNumberOfRows())
            throw new IndexOutOfBoundsException(
                    "Can not add a Square in that position.");
            
        this.squares[x][y] = new Square(x, y, squareType);
        
        return this;
    }
    
    public boolean isPositionAvailable(int x, int y) {
        return !(this.getSquare(x, y) instanceof Square);
    }
    
    /**
     * 
     * @return The width of this matrix.
     */
    public int getNumberOfColumns() {
        return this.squares.length;
    }
    
    /**
     * 
     * @return The height of this matrix. 
     */
    public int getNumberOfRows() {
        if(this.getNumberOfColumns() == 0)
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
        
        int totalColumns = coordinates.length;
        int firstRow = 0;
        int secondRow = 1;
        
        for(int colNum = 0; colNum < totalColumns; colNum++) {
            int x = coordinates[colNum][firstRow];
            int y = coordinates[colNum][secondRow];
            this.squares[x][y] = null;
        }
    }
    
    public void moveDownFlyingSquares() {
        /* Start from the bottom */
        int lastRow = this.getNumberOfRows() - 1;
        for(int colNum = 0; colNum < this.getNumberOfColumns(); colNum++) {
            this.packColumn(colNum, lastRow);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(int x = 0; x < this.getNumberOfColumns(); x++)
            for(int y = 0; y < this.getNumberOfRows(); y++) {
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
