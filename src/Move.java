import java.util.LinkedList;

public class Move {
    private final Coordinate start;
    private final Coordinate end;
    private final int capture;

    public Move(int startRow, int startCol, int endRow, int endCol) {
        this.start = new Coordinate(startRow, startCol);
        this.end = new Coordinate(endRow, endCol);
        this.capture = -1;
    }

    public Move(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
        this.capture = -1;
    }

    public Move(Coordinate start, Coordinate end, int captured) {
        this.start = start;
        this.end = end;
        this.capture = captured;
    }

    public Move(int startRow, int startCol, int endRow, int endCol, int captured) {
        this.start = new Coordinate(startRow, startCol);
        this.end = new Coordinate(endRow, endCol);
        this.capture = captured;
    }

    public Coordinate getStart() {
        return this.start;
    }

    public Coordinate getEnd() {
        return this.end;
    }

    public int getCaptured() {
        return capture;
    }

    @Override
    public String toString() {
        return "Move{" +
            "start=" + start +
            ", end=" + end +
            ", capture=" + capture +
            '}';
    }
}
