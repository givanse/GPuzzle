package game.pieces;

import game.Utility;
import game.patterns.Tetromino;
import game.pieces.Square.SquareColour;
import java.util.ArrayList;
import java.util.Objects;

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
    
    public Board(int rowsFilledWithSquares) {
        this(Board.WIDTH_IN_SQUARES, Board.HEIGHT_IN_SQUARES);
        this.squaresMatrix.insertRandomSquares(rowsFilledWithSquares);
    }
    
    public Board(int widthInSquares, int heightInSquares) {
        this(new SquaresMatrix(widthInSquares, heightInSquares));
    }
    
    public Board(SquaresMatrix squares) {
        this.squaresMatrix = squares;
        if(this.squaresMatrix.getNumberOfRows() > 0)
            this.addRandomFallingPairOfSquares();
    }
    
    /* Public methods */
    
    public final boolean addRandomFallingPairOfSquares() {
        /**
         * Produce an array with the same width of the board.
         */
        int availableColumns[] = this.squaresMatrix.getAvailableTopRowPairs();
        
        if(availableColumns.length < 2)
            return false;
        
        int shuffledColumns[] = Utility.shuffleArray(availableColumns);
        
        int xCoords[] = Utility.getTwoConsecutiveNumbers(shuffledColumns);
        int x1 = xCoords[0];
        int x2 = xCoords[1];
        
        SquareColour randomColour1 = SquareColour.getRandomColour();
        SquareColour randomColour2 = SquareColour.getRandomColour();
        
        int yConstant = 0;
        Square square1 = new Square(x1, yConstant, randomColour1);
        Square square2 = new Square(x2, yConstant, randomColour2);
        
        this.fallingSquares.add(square1);
        this.fallingSquares.add(square2);
        if(square1 instanceof Square && square2 instanceof Square)
            return true;
        else
            return false;
    }
    
    /**
     * This is the "brain" of this class. It calculates the new position for
     * the falling squares, validates and adds them to the board if they collide
     * with a board square. It also check if a tetromino pattern is found once
     * it has been made part of the board matrix.
     */
    public void updateSquares() {
        for(int i = 0; i < this.fallingSquares.size(); i++) {
            Square s = this.fallingSquares.get(i);
            int newX = s.getX();
            int newY = s.getY() + 1;
            if(isPositionAvailable(newX, newY)) {
                /* keep falling */
                s.setY(newY);
            } else {
                /* stop falling */
                this.fallingSquares.remove(s);
                i--;
                this.squaresMatrix.insertSquare(s);
                this.checkAndDeleteCompletedTetrisShape(s.getX(), s.getY());
            }
        }
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
    
    /**
     * 
     * @param x
     * @param y
     * @param xSwap swaping towards this x
     * @param ySwap swaping towards this y
     */
    public boolean swapSquares(int x, int y, int xSwap, int ySwap) {
        SwapDirection swapDirection = null;
        if(y == ySwap)
            swapDirection = (x - xSwap) == 1 ? SwapDirection.LEFT : 
                                               SwapDirection.RIGHT;
        if(x == xSwap)
            swapDirection = (y - ySwap) == 1 ? SwapDirection.UP : 
                                               SwapDirection.DOWN;
        
        if(swapDirection == null)
            return false;
        
        boolean swapCompleted = false;
        if(this.isValidSwap(x, y, swapDirection)) {
            Square square = this.squaresMatrix.getSquare(x, y);
            Square squareSwap = this.squaresMatrix.getSquare(xSwap, ySwap);
            /* swap coordinates */
            this.squaresMatrix.insertSquare(x, y, squareSwap.getSquareColour());
            this.squaresMatrix.insertSquare(xSwap, ySwap, square.getSquareColour());
            swapCompleted = true;
        }
        return swapCompleted;
    }
    
    public boolean checkAndDeleteCompletedTetrisShape(int x, int y) {
        boolean deletedPattern = false;
        int patternFound[][] = 
                     Tetromino.performPatternMatching(x, y, this.squaresMatrix);
        deletedPattern = this.squaresMatrix.deleteSquares(patternFound);
        /* Pack the empty spaces left by the deleted tetromino */
        for(int i = 0; i < patternFound.length; i++) {
            int delX = patternFound[i][0];
            int delY = patternFound[i][1];
            if(this.squaresMatrix.moveDownFlyingSquares(delX, delY)) {
                this.checkAndDeleteCompletedTetrisShapes();
            }
        }
        return deletedPattern;
    }
    
    public void checkAndDeleteCompletedTetrisShapes() {
        for(int x = 0; x < this.squaresMatrix.getNumberOfColumns(); x++) {
            for(int y = 0; y < this.squaresMatrix.getNumberOfRows(); y++) {
                this.checkAndDeleteCompletedTetrisShape(x, y);
            }
        }
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
