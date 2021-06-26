import java.util.List;

public class Pair {
    private int score;
    private List<Coordinate> move;

    public Pair(int score, List<Coordinate> move) {
        this.score = score;
        this.move = move;
    }

    public int getScore() {
        return this.score;
    }

    public List<Coordinate> getMove() {
        return this.move;
    }
}
