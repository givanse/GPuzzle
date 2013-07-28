package game.pieces;

import game.patterns.Tetromino;
import game.pieces.Square.SquareColour;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author givanse
 */
public class Board {
    
    private final SquaresMatrix squaresMatrix;
    private final ArrayList<Square> fallingSquares = new ArrayList<>(2);
    
    public enum SwapDirection {LEFT, RIGHT, UP, DOWN};
    public static int WIDTH_IN_SQUARES = 12;  // number of squares 
    public static int HEIGHT_IN_SQUARES = 18; // number of squares
    
    public Board() {
        this(Board.WIDTH_IN_SQUARES, Board.HEIGHT_IN_SQUARES);
    }
    
    public Board(int widthInSquares, int heightInSquares) {
        this(new SquaresMatrix(widthInSquares, heightInSquares));
    }
    
    public Board(SquaresMatrix squares) {
        this.squaresMatrix = squares;
        this.generateRandomFallingPair();
    }
    
    private void generateRandomFallingPair() {
        /**
         * Produce an array with the same width of the board.
         */
        int availableColumns[] = this.squaresMatrix.getAvailableTopColumns();
        /* Shuffle the availble columns. */
        
        /* Use the first two columns. */
        int col1Indx = 0, col2Indx = 0;
        col2Indx = (availableColumns.length == 1) ? 0 : 1;
        int x1 = availableColumns[col1Indx];
        int x2 = availableColumns[col2Indx];
        
        /**
         * Produce two random colours, that may be repeated.
         */
        Random randomGenerator = new Random(System.currentTimeMillis());
        SquareColour sqrColours[] = SquareColour.values();
        int randomColourNumber = randomGenerator.nextInt(sqrColours.length);
        SquareColour randomColour1 = sqrColours[randomColourNumber];
        randomColourNumber = randomGenerator.nextInt(sqrColours.length);
        SquareColour randomColour2 = sqrColours[randomColourNumber];
        
        int yConstant = 0;
        Square square1 = new Square(x1, yConstant, randomColour1);
        Square square2 = new Square(x2, yConstant, randomColour2);
        
        this.fallingSquares.add(square1);
        this.fallingSquares.add(square2);
    }
    
    /* Public methods */
    
    public void update() {
        
    }
    
    public ArrayList<Square> getFallingSquares() {
        return this.fallingSquares;
    }
    
    public final boolean isPositionAvailable(int x, int y) {
        return this.squaresMatrix.isPositionAvailable(x, y);
    }
    
    public boolean isValidSwap(int x, int y, SwapDirection swapDirection) {      
        if(x < 0 || y < 0)
            return false;
        
        if(this.isPositionAvailable(x, y))        /* No square for swap here. */
            return false;
        
        int newX = x;
        int newY = y;
        
        newX += (swapDirection == SwapDirection.RIGHT) ? 1 : 0;
        newX += (swapDirection == SwapDirection.LEFT) ? -1 : 0;
        
        newY += (swapDirection == SwapDirection.DOWN) ? 1 : 0;
        newY += (swapDirection == SwapDirection.UP) ? -1 : 0;
        
        if(newX < 0 || newY < 0)
            return false;
        
        boolean foundSquareForSwap = !this.isPositionAvailable(newX, newY);
            return foundSquareForSwap;
    }
    
    public void deleteCompletedTetrisShapes() {
        for(int x = 0; x < this.squaresMatrix.getNumberOfRows(); x++) {
            for(int y = 0; y < this.squaresMatrix.getNumberOfColumns(); y++) {
                int patternFound[][] = 
                      Tetromino.performPatternMatching(x, y, this.squaresMatrix);
                this.squaresMatrix.deleteSquares(patternFound);
                this.moveDownFlyingSquares();
            }
        }
    }
    
    public void moveDownFlyingSquares() {
        this.squaresMatrix.moveDownFlyingSquares();
    }
    
    public Square[][] getSquares() {
        return this.squaresMatrix.getSquares();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.squaresMatrix);
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
        final Board other = (Board) obj;
        if (!Objects.equals(this.squaresMatrix, other.squaresMatrix)) {
            return false;
        }
        return true;
    }
    
}
