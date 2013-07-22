package game.pieces;

/**
 *
 * @author givanse
 */
public class Board {
    
    private final int width;
    private final int height;
    private final boolean coordinates[][];
    private final Square squares[][];
    
    public enum SwapDirection {LEFT, RIGHT, UP, DOWN};
    public static int WIDTH_IN_SQUARES = 12;  // number of squares 
    public static int HEIGHT_IN_SQUARES = 18; // number of squares
    
    public Board() {
        this(Board.WIDTH_IN_SQUARES, Board.HEIGHT_IN_SQUARES);
    }
    
    public Board(int widthInSquares, int heightInSquares) {
        this.width = widthInSquares;
        this.height = heightInSquares;
        this.coordinates = new boolean[this.width][this.height];
        this.squares = new Square[this.width][this.height];
    }
    
    public Board(Square[][] squares) {
        this.squares = squares;
        this.width = this.squares[0].length;
        this.height = this.squares.length;
        this.coordinates = new boolean[this.width][this.height];
         for(int x = 0; x < this.width ; x++)
            for(int y = 0; y < this.height; y++) {
                this.updatePosition(x, y, this.isPositionAvailable(x, y));
            }
    }
    
    /* Public methods */
    
    public void update() {
        
    }
    
    public Square[][] getSquares() {
        return this.squares;
    }
    
    public boolean isPositionAvailable(int x, int y) {
        return false;
    }
    
    public void updatePosition(int x, int y, boolean isAvailable) {
        this.coordinates[x][y] =  isAvailable;
    }
    
    public boolean isValidSwap(int x, int y, SwapDirection dir) {
        return true;
    }
    
    public void deleteCompletedTetrisShapes() {
        
    }
    
    public void moveDownLooseRows() {
        
    }
}
