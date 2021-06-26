import java.util.ArrayList;
import java.util.List;

public class Bot {

    public Bot() {

    }

    public int calculateScore(Board board) {
        if (board.isGameOver()) {
            return board.getPlayerTurn() ? 1000 : -1000;
        }
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int piece = board.getPiece(i, j);
                switch (piece) {
                    case 1:
                        score--;
                        break;
                    case 2:
                        score -= 3;
                        break;
                    case 3:
                        score++;
                        break;
                    case 4:
                        score += 3;
                        break;
                    default:
                        break;
                }
            }
        }
        return score;
    }

    public Pair minimax(Board board, int depth, int alpha, int beta, boolean turn) {
        List<Coordinate> move = new ArrayList<>(2);
        if board.isGameOver() || depth == 0 {
            return new Pair(this.calculateScore(board), null);
        }
        if (!turn) {
            int bestEval = Integer.MAX_VALUE;
            for
        }
    }
}
