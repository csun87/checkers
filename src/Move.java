

public class Move {
    private final int startCol;
    private final int endCol;
    private final int startRow;
    private final int endRow;
    private final int distance;

    public Move(int startRow, int startCol, int endRow, int endCol) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.distance = Math.abs(startRow - endRow) + Math.abs(startCol - endCol);
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndCol() {
        return endCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getDistance() {
        return distance;
    }
}
