import com.company.Board;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TileNotMovingTest {
    private int[][] matrix;
    private Board board;
    private int zeroX;
    private int zeroY;
    private Board expectedBoard = null;

    @DataProvider
    public Object[][] matrixData() {
        return new Object[][]{
                {new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 12, 0}, {13, 14, 11, 15}}, 0, 1},
                {new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {0, 10, 12, 9}, {13, 14, 11, 15}}, 0, -1},
                {new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15}}, 1, 0},
                {new int[][]{{1, 2, 0, 4}, {5, 6, 7, 8}, {3, 10, 12, 9}, {13, 14, 11, 15}}, -1, 0},
        };
    }

    @Test(dataProvider = "matrixData")
    public void tileNotMovesRight(int[][] matrix, int plusZeroX, int plusZeroY){
        board = new Board(matrix);
        zeroX = board.getZeroX();
        zeroY = board.getZeroY();

        Board result = board.changePosition(board.getNewBlock(), zeroX, zeroY, zeroX+plusZeroX, zeroY+plusZeroY);

        Assert.assertEquals(expectedBoard, result);
    }

}
