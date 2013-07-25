package game.pieces;

import game.patterns.Tetromino;
import game.patterns.TetrominoType;
import game.pieces.Square.SquareColour;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author givanse
 */
public class Board {
    
    private final SquaresMatrix boardSquares;
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
        this.boardSquares = squares;
        this.generateRandomFallingPair();
    }
    
    private void generateRandomFallingPair() {
        Random random = new Random(System.currentTimeMillis());
        int width = this.boardSquares.getWidth();
        int randomX = random.nextInt(width);
        int constantY = 0;
        SquareColour sqrTypes[] = SquareColour.values();
        int randomType = random.nextInt(sqrTypes.length);
        Square square1 = new Square(randomX, constantY, sqrTypes[randomType]);
        this.fallingSquares.add(square1);
        randomX = (randomX == width - 1) ? randomX - 1 : randomX + 1;
        randomType = random.nextInt(sqrTypes.length);
        Square square2 = new Square(randomX, constantY, sqrTypes[randomType]);
        this.fallingSquares.add(square2);
    }
    
    /* Public methods */
    
    public void update() {
        
    }
    
    public ArrayList<Square> getFallingSquares() {
        return this.fallingSquares;
    }
    
    public final boolean isPositionAvailable(int x, int y) {
        return this.boardSquares.isPositionAvailable(x, y);
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
        for(int x = 0; x < this.boardSquares.getWidth(); x++)
            for(int y = 0; y < this.boardSquares.getHeight(); y++) {
                int patternFound[][] = 
                      Tetromino.performPatternMatching(x, y, this.boardSquares);
                this.boardSquares.deleteSquares(patternFound);
                this.moveDownFlyingSquares();
            }
    }
    
    public void moveDownFlyingSquares() {
        this.boardSquares.moveDownFlyingSquares();
    }
    
    public SquaresMatrix getSquares() {
        return this.boardSquares;
    }
}
