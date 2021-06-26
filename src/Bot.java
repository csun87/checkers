import java.util.ArrayList;
import java.util.List;

public class Bot {

    public int calculateScore(int[][] mat, boolean turn) {
        if (this.isGameOver(mat, turn)) {
            return turn ? 1000 : -1000;
        }
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int piece = mat[i][j];
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

    public Pair minimax(int[][] mat, int depth, int alpha, int beta, boolean turn) {
        List<Coordinate> move = new ArrayList<>(2);
        if (depth == 0 || this.isGameOver(mat, turn)) {
            return new Pair(this.calculateScore(mat, turn), null);
        }
        if (!turn) { // bot's turn
            int bestEval = Integer.MIN_VALUE;
            List<List<Coordinate>> moves = this.getMoves(mat, false);
            for (List<Coordinate> curr : moves) {
                int[][] temp = simulateMove(mat, curr);
                Pair score = this.minimax(temp, depth - 1, alpha, beta, !turn);
                if (score.getScore() > bestEval) {
                    bestEval = score.getScore();
                    move = curr;
                }
                alpha = Math.max(alpha, bestEval);
                if (beta <= alpha) {
                    break;
                }
            }
            return new Pair(bestEval, move);
        } else {
            int bestEval = Integer.MAX_VALUE;
            List<List<Coordinate>> moves = this.getMoves(mat, true);
            for (List<Coordinate> curr : moves) {
                int[][] temp = simulateMove(mat, curr);
                Pair score = this.minimax(temp, depth - 1, alpha, beta, !turn);
                if (score.getScore() < bestEval) {
                    bestEval = score.getScore();
                    move = curr;
                }
                beta = Math.min(beta, bestEval);
                if (beta <= alpha) {
                    break;
                }
            }
            return new Pair(bestEval, move);
        }
    }

    private int[][] simulateMove(int[][] mat, List<Coordinate> move) {
        int[][] board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = mat[i][j];
            }
        }
        Coordinate start = move.get(0);
        Coordinate end = move.get(1);

        int distance = Math.abs(start.getRow() - end.getRow()) +
                Math.abs(start.getCol() - end.getCol());
        int piece = board[start.getRow()][start.getCol()];

        if (distance == 2) {
            board[start.getRow()][start.getCol()] = 0;
            board[end.getRow()][end.getCol()] = piece;
        } else {
            board[start.getRow()][start.getCol()] = 0;
            board[end.getRow()][end.getCol()] = piece;
            board[(start.getRow() + end.getRow()) / 2]
                    [(start.getCol() + end.getCol()) / 2] = 0;
        }

        if (piece == 1 && end.getRow() == 7) {
            board[end.getRow()][end.getCol()] = 2;
        } else if (piece == 3 && end.getRow() == 0) {
            board[end.getRow()][end.getCol()] = 4;
        }
        return board;
    }

    private List<List<Coordinate>> getMoves(int[][] mat, boolean turn) {
        Board board = new Board(mat, turn);
        List<List<Coordinate>> moves = new ArrayList<>();
        if (turn) { // player's turn
            for (int i = 0; i < 8 ; i++) {
                for (int j = 0; j < 8; j++) {
                    int piece = board.getPiece(j, i);
                    if (piece == 1 || piece == 2) {
                        List<Coordinate> possibilities = board.validMoves(new Coordinate(j, i));
                        for (Coordinate c : possibilities) {
                            List<Coordinate> toAdd = new ArrayList<>(2);
                            toAdd.add(new Coordinate(j, i));
                            toAdd.add(c);
                            moves.add(toAdd);
                        }
                    }
                }
            }
        } else { // bot's turn
            for (int i = 0; i < 8 ; i++) {
                for (int j = 0; j < 8; j++) {
                    int piece = board.getPiece(j, i);
                    if (piece == 3 || piece == 4) {
                        List<Coordinate> possibilities = board.validMoves(new Coordinate(j, i));
                        for (Coordinate c : possibilities) {
                            List<Coordinate> toAdd = new ArrayList<>(2);
                            toAdd.add(new Coordinate(j, i));
                            toAdd.add(c);
                            moves.add(toAdd);
                        }
                    }
                }
            }
        }
        return moves;
    }

    private boolean isGameOver(int[][] mat, boolean turn) {
        Board board = new Board(mat, turn);
        return board.isGameOver();
    }
}
