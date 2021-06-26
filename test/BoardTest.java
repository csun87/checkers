import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void testNewBoard() {
        Board board = new Board();
        int[][] arr = board.getBoard();
        assertArrayEquals(new int[]{0, 1, 0, 1, 0 , 1, 0, 1}, arr[0]);
        assertArrayEquals(new int[]{1, 0, 1, 0, 1 , 0, 1, 0}, arr[1]);
        assertArrayEquals(new int[]{0, 1, 0, 1, 0 , 1, 0, 1}, arr[2]);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0 , 0, 0, 0}, arr[3]);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0 , 0, 0, 0}, arr[4]);
        assertArrayEquals(new int[]{3, 0, 3, 0, 3, 0, 3, 0}, arr[5]);
        assertArrayEquals(new int[]{0, 3, 0, 3, 0 , 3, 0, 3}, arr[6]);
        assertArrayEquals(new int[]{3, 0, 3, 0, 3, 0, 3, 0}, arr[7]);
        arr[7] = new int[]{2,2,2,2,2,2,2,2};
        assertFalse(Arrays.equals(arr, board.getBoard()));
    }

    @Test
    public void testSimpleValidMoves() {
        Board board = new Board();
        assertThrows(IllegalCallerException.class, () -> {
            board.validMoves(new Coordinate(0, 0));
        });
        LinkedList<Coordinate> actual = board.validMoves(new Coordinate(2,1));
        assertEquals(new Coordinate(3, 0), actual.pop());
        assertEquals(new Coordinate(3, 2), actual.pop());
        assertTrue(actual.isEmpty());
        assertTrue(board.validMoves(new Coordinate(0, 1)).isEmpty());
    }

    // by testing playMove(), I am also testing validMoves().
    @Test
    public void testPlayMoveSimple() {
        Board board = new Board();
        assertTrue(board.playMove(new Coordinate(2, 1), new Coordinate(3, 2)));
    }

    @Test
    public void testPlayMoveAgainstWall() {
        Board board = new Board();
        assertTrue(board.playMove(new Coordinate(2, 1), new Coordinate(3, 2)));
        assertFalse(board.playMove(new Coordinate(5, 0), new Coordinate(5, -1)));
        assertTrue(board.playMove(new Coordinate(5, 0), new Coordinate(4, 1)));
    }

    @Test
    public void testPlayMoveToOccupiedSquare() {
        Board board = new Board();
        assertFalse(board.playMove(new Coordinate(0, 1), new Coordinate(1, 2)));
    }

    @Test
    public void testCapture() {
        Board board = new Board();
        assertTrue(board.playMove(new Coordinate(2, 1), new Coordinate(3, 2)));
        assertTrue(board.playMove(new Coordinate(5, 4), new Coordinate(4, 3)));
        assertTrue(board.playMove(new Coordinate(3, 2), new Coordinate(5, 4)));
    }

    @Test
    public void testUndoSimple() {
        Board board = new Board();
        assertTrue(board.playMove(new Coordinate(2, 1), new Coordinate(3, 2)));
        board.undo();
        int[][] arr = board.getBoard();
        assertArrayEquals(new int[]{0, 1, 0, 1, 0 , 1, 0, 1}, arr[0]);
        assertArrayEquals(new int[]{1, 0, 1, 0, 1 , 0, 1, 0}, arr[1]);
        assertArrayEquals(new int[]{0, 1, 0, 1, 0 , 1, 0, 1}, arr[2]);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0 , 0, 0, 0}, arr[3]);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0 , 0, 0, 0}, arr[4]);
        assertArrayEquals(new int[]{3, 0, 3, 0, 3, 0, 3, 0}, arr[5]);
        assertArrayEquals(new int[]{0, 3, 0, 3, 0 , 3, 0, 3}, arr[6]);
        assertArrayEquals(new int[]{3, 0, 3, 0, 3, 0, 3, 0}, arr[7]);
    }

}