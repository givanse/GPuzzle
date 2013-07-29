package game.pieces;

import game.Utility;
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
    
    /* Public methods */
    
    public int[] getAvailableTopRowPairs() {
        
        /* Only pairs of columns are valid. */
        if(this.getNumberOfColumns() < 2)
            return new int[0];
        
        /* filter available columns */
        int row = 0;
        ArrayList<Integer> colsList = new ArrayList(this.getNumberOfRows());
        for(int col = 0; col < this.getNumberOfColumns() - 1; col++) {
            if(this.isPositionAvailable(col, row)) {
                int adjacentCol = col + 1;
                if(this.isPositionAvailable(adjacentCol, row)) {
                    colsList.add(col);
                    colsList.add(adjacentCol);
                    col = adjacentCol + 1;
                }
            }
        }
        if(this.getNumberOfColumns() % 2 == 1) {
            int lastColumn = this.getNumberOfColumns() - 1;
            if(this.isPositionAvailable(lastColumn, row)) {
                if(this.isPositionAvailable(lastColumn - 1, row))
                    colsList.add(lastColumn);
            }
        }
        
        /* Convert final ArrayList to Array */
        int availableColumns[] = new int[colsList.size()];
        int i = 0;
        for(Integer columnNumber : colsList) {
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
    
    public SquaresMatrix insertSquare(Square square) {
        this.squares[square.getX()][square.getY()] = square;
        return this;
    }
    
    public void insertRandomSquares(int rowsQuantity) {
        int availableColumns[] = new int[this.getNumberOfColumns()];
        for(int i = 0; i < this.getNumberOfColumns(); i++) {
            availableColumns[i] = i;
        }
        int bottomRow = this.getNumberOfRows() - 1;
        int lastRow = this.getNumberOfRows() - rowsQuantity;
        for(int row = bottomRow; row >= lastRow; row--) {
            int shuffledColumns[] = Utility.shuffleArray(availableColumns);
            int missingSquaresPerRow = 3;
            int lastCol = this.getNumberOfColumns() - missingSquaresPerRow;
            for(int col = 0; col < lastCol; col++) {
                SquareColour sc = SquareColour.getRandomColour();
                this.insertSquare(shuffledColumns[col], row, sc);
            }
        }
    }
    
    public boolean isPositionAvailable(int x, int y) {
        if(x < 0 || x >= this.getNumberOfColumns() ||
           y < 0 || y >= this.getNumberOfRows())
            return false;
        
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
    public boolean deleteSquares(int coordinates[][]) {
        boolean deletedSquares = false;
        
        int totalColumns = coordinates.length;
        if(totalColumns == 0)
            return deletedSquares;
        
        int firstRow = 0;
        int secondRow = 1;
        for(int colNum = 0; colNum < totalColumns; colNum++) {
            int x = coordinates[colNum][firstRow];
            int y = coordinates[colNum][secondRow];
            this.squares[x][y] = null;
            deletedSquares = true;
        }
        
        return deletedSquares;
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    public boolean moveDownFlyingSquares(int x, int y) {
        boolean squaresMoved = false;
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
                        squaresMoved = true;
                        break foundNotNullUpperSquare;
                    }
                }
            }
        }
        return squaresMoved;
    }
    
    public void moveDownFlyingSquares() {
        /* Start from the bottom */
        int lastRow = this.getNumberOfRows() - 1;
        for(int colNum = 0; colNum < this.getNumberOfColumns(); colNum++) {
            this.moveDownFlyingSquares(colNum, lastRow);
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
