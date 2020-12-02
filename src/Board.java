import java.util.LinkedList;

/* The board will be organized in a fashion such that the top left and bottom right corners are
   light-colored and the top right and bottom left corners are dark-colored. Each player starts
   with 12 pieces as is standard in checkers. They will "spawn" in on all the dark-colored squares
   of the bottom and top 3 rows.
   0 = empty square
   1 = regular light piece; 2 = crowned light piece
   3 = regular dark piece; 4 = crowned dark piece

   0 1 2 3 4 5 6 7 <- player 1 side
   1
   2
   3
   4
   5
   6
   7               <- player 2 side

 */
public class Board {

    private int[][] arr;
    private boolean playerTurn; // true = player 1; false = player 2
    private int numTurns;


    public Board() {
        this.init();
    }

    public void init() {
        this.arr = new int[8][8];
        this.playerTurn = true;
        this.numTurns = 0;

        for (int i = 1; i < 8; i += 2) {
            this.arr[0][i] = 1;
            this.arr[2][i] = 1;
            this.arr[6][i] = 3;
        }
        for (int i = 0; i < 8; i += 2) {
            this.arr[1][i] = 1;
            this.arr[5][i] = 3;
            this.arr[7][i] = 3;
        }
    }

    public boolean playTurn(LinkedList<Move> list) {
        boolean isValidMove = true;
        while (!list.isEmpty()) {
            Move curr = list.pop();
            int piece = this.arr[curr.getStartRow()][curr.getStartCol()];

            if ((piece > 2 && this.playerTurn) || (piece < 3 && !this.playerTurn)) {
                return false;
            }

            if (this.playerTurn) { // if it is player 1's turn
                if (curr.getEndRow() < curr.getStartRow() && piece != 2) {
                    return false;
                } else if (curr.getDistance() != 2) {
                    return isValidCapture(curr);
                }

            } else { // if it is player 2's turn

            }
        }
    }

    private boolean isValidCapture(Move move) {
        int piece = this.arr[move.getStartRow()][move.getStartCol()];
        int captRow = Math.max(move.getStartRow(), move.getEndRow()) -
            Math.min(move.getStartRow(), move.getEndRow());
        int captCol = Math.max(move.getStartCol(), move.getEndCol()) -
            Math.min(move.getStartCol(), move.getEndCol());
        int captPiece = this.arr[captRow][captCol];
        if (this.playerTurn) { // if it is player 1's turn
            if (captPiece <= 2) {
                return false;
            } else {
                return true;
            }
        } else { // if it is player 2's turn
            if (captPiece >= 3) {
                return false;
            } else {
                return true;
            }
        }
    }
}
