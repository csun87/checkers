import java.util.LinkedList;

public class Move {
    private final Coordinate start;
    private final Coordinate end;
    private final int capture;
    private boolean kinged;

    public Move(int startRow, int startCol, int endRow, int endCol) {
        this.start = new Coordinate(startRow, startCol);
        this.end = new Coordinate(endRow, endCol);
        this.capture = -1;
        this.kinged = false;
    }

    public Move(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
        this.capture = -1;
        this.kinged = false;
    }

    public Move(Coordinate start, Coordinate end, int captured) {
        this.start = start;
        this.end = end;
        this.capture = captured;
        this.kinged = false;
    }

    public Move(int startRow, int startCol, int endRow, int endCol, int captured) {
        this.start = new Coordinate(startRow, startCol);
        this.end = new Coordinate(endRow, endCol);
        this.capture = captured;
        this.kinged = false;
    }

    public Move(Coordinate start, Coordinate end, int captured, boolean kinged) {
        this.start = start;
        this.end = end;
        this.capture = captured;
        this.kinged = kinged;
    }

    public Move(int startRow, int startCol, int endRow, int endCol, int captured, boolean kinged) {
        this.start = new Coordinate(startRow, startCol);
        this.end = new Coordinate(endRow, endCol);
        this.capture = captured;
        this.kinged = kinged;
    }

    public Coordinate getStart() {
        return this.start;
    }

    public Coordinate getEnd() {
        return this.end;
    }

    public int getCaptured() {
        return this.capture;
    }

    public boolean getKinged() {
        return this.kinged;
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
