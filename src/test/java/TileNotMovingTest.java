import com.company.Board;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TileNotMovingTest {
    public int[][] matrix;
    public Board board;
    public int zeroX;
    public int zeroY;
    public Board expectedBoard;

    @Before
    public void init() {
        expectedBoard = null;
    }

    @Test
    public void tileNotMovesRight(){
        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 12, 0}, {13, 14, 11, 15}};
        board = new Board(matrix);
        zeroX = board.getZeroX();
        zeroY = board.getZeroY();

        Board result = board.chng(board.getNewBlock(), zeroX, zeroY, zeroX, zeroY+1);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void tileNotMovesLeft(){
        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {0, 10, 12, 9}, {13, 14, 11, 15}};
        board = new Board(matrix);
        zeroX = board.getZeroX();
        zeroY = board.getZeroY();

        Board result = board.chng(board.getNewBlock(), zeroX, zeroY, zeroX, zeroY-1);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void tileNotMovesDown(){
        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15}};
        board = new Board(matrix);
        zeroX = board.getZeroX();
        zeroY = board.getZeroY();

        Board result = board.chng(board.getNewBlock(), zeroX, zeroY, zeroX+1, zeroY);

        Assert.assertEquals(expectedBoard, result);
    }

    @Test
    public void tileNotMovesUp(){
        matrix = new int[][]{{1, 2, 0, 4}, {5, 6, 7, 8}, {3, 10, 12, 9}, {13, 14, 11, 15}};
        board = new Board(matrix);
        zeroX = board.getZeroX();
        zeroY = board.getZeroY();

        Board result = board.chng(board.getNewBlock(), zeroX, zeroY, zeroX-1, zeroY);

        Assert.assertEquals(expectedBoard, result);
    }
}
