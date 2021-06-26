import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/* The board will be organized in a fashion such that the top left and bottom right corners are
   light-colored and the top right and bottom left corners are dark-colored. Each player starts
   with 12 pieces as is standard in checkers. They will "spawn" in on all the dark-colored squares
   of the bottom and top 3 rows.
   0 = empty square
   1 = regular light piece; 2 = crowned light piece
   3 = regular dark piece; 4 = crowned dark piece
   This class handles the actual game state of the board.

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
    private Deque<Move> moves;
    private boolean playerTurn; // true = player 1; false = player 2
    private int numWhitePieces;
    private int numBlackPieces;
    private int numTurns;
    private boolean gameOver;


    public Board() {
        this.init();
    }

    public void init() {
        this.arr = new int[8][8];
        this.playerTurn = true;
        this.numTurns = 0;
        this.numWhitePieces = 12;
        this.numBlackPieces = 12;
        this.moves = new ArrayDeque<>();
        this.gameOver = false;

        for (int i = 1; i < 8; i += 2) { // initializing the state of the board
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

    // This function just gives you the value of a certain index in the 2-D array.
    public int getPiece(int row, int col) {
        if (row > 7 || col > 7 || row < 0 || col < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.arr[row][col];
    }

    // This function returns a LinkedList of all valid moves given a certain piece.
    public LinkedList<Coordinate> validMoves(Coordinate curr) {
        LinkedList<Coordinate> out = new LinkedList<>();
        if (!gameOver) {
            int piece = this.arr[curr.getRow()][curr.getCol()];
            if (piece == 0) {
                throw new IllegalCallerException("Player clicked on empty square");
            } else if (this.playerTurn && piece >= 3) {
                throw new IllegalCallerException("Player 1 tried clicking on player 2 piece");
            } else if (!this.playerTurn && piece <= 2) {
                throw new IllegalCallerException("Player 2 tried clicking on player 1 piece");
            }

            int upLeft;
            int upRight;
            int downLeft;
            int downRight;

            try {
                upLeft = this.arr[curr.getRow() - 1][curr.getCol() - 1];
            } catch (Exception e) {
                upLeft = -1;
            }

            try {
                upRight = this.arr[curr.getRow() - 1][curr.getCol() + 1];
            } catch (Exception e) {
                upRight = -1;
            }

            try {
                downLeft = this.arr[curr.getRow() + 1][curr.getCol() - 1];
            } catch (Exception e) {
                downLeft = -1;
            }

            try {
                downRight = this.arr[curr.getRow() + 1][curr.getCol() + 1];
            } catch (Exception e) {
                downRight = -1;
            }


            if (piece == 1 || piece == 2) {
                if (downLeft == 0) {
                    out.add(new Coordinate(curr.getRow() + 1, curr.getCol() - 1));
                } else if (downLeft >= 3) {
                    try {
                        if (this.arr[curr.getRow() + 2][curr.getCol() - 2] == 0) {
                            out.add(new Coordinate(curr.getRow() + 2, curr.getCol() - 2));
                        }
                    } catch (Exception ignored) {
                    }
                }

                if (downRight == 0) {
                    out.add(new Coordinate(curr.getRow() + 1, curr.getCol() + 1));
                } else if (downRight >= 3) {
                    try {
                        if (this.arr[curr.getRow() + 2][curr.getCol() + 2] == 0) {
                            out.add(new Coordinate(curr.getRow() + 2, curr.getCol() + 2));
                        }
                    } catch (Exception ignored) {
                    }
                }

                if (piece == 2) {
                    if (upRight == 0) {
                        out.add(new Coordinate(curr.getRow() - 1, curr.getCol() + 1));
                    } else if (upRight >= 3) {
                        try {
                            if (this.arr[curr.getRow() - 2][curr.getCol() + 2] == 0) {
                                out.add(new Coordinate(curr.getRow() - 2, curr.getCol() + 2));
                            }
                        } catch (Exception ignored) {
                        }
                    }

                    if (upLeft == 0) {
                        out.add(new Coordinate(curr.getRow() - 1, curr.getCol() - 1));
                    } else if (upLeft >= 3) {
                        try {
                            if (this.arr[curr.getRow() - 2][curr.getCol() - 2] == 0) {
                                out.add(new Coordinate(curr.getRow() - 2, curr.getCol() - 2));
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }

            if (piece == 3 || piece == 4) {

                if (upRight == 0) {
                    out.add(new Coordinate(curr.getRow() - 1, curr.getCol() + 1));
                } else if (upRight == 2 || upRight == 1) {
                    try {
                        if (this.arr[curr.getRow() - 2][curr.getCol() + 2] == 0) {
                            out.add(new Coordinate(curr.getRow() - 2, curr.getCol() + 2));
                        }
                    } catch (Exception ignored) {
                    }
                }

                if (upLeft == 0) {
                    out.add(new Coordinate(curr.getRow() - 1, curr.getCol() - 1));
                } else if (upLeft <= 2) {
                    try {
                        if (this.arr[curr.getRow() - 2][curr.getCol() - 2] == 0) {
                            out.add(new Coordinate(curr.getRow() - 2, curr.getCol() - 2));
                        }
                    } catch (Exception ignored) {
                    }
                }

                if (piece == 4) {
                    if (downLeft == 0) {
                        out.add(new Coordinate(curr.getRow() + 1, curr.getCol() - 1));
                    } else if (downLeft == 1 || downLeft == 2) {
                        try {
                            if (this.arr[curr.getRow() + 2][curr.getCol() - 2] == 0) {
                                out.add(new Coordinate(curr.getRow() + 2, curr.getCol() - 2));
                            }
                        } catch (Exception ignored) {
                        }
                    }

                    if (downRight == 0) {
                        out.add(new Coordinate(curr.getRow() + 1, curr.getCol() + 1));
                    } else if (downRight == 1 || downRight == 2) {
                        try {
                            if (this.arr[curr.getRow() + 2][curr.getCol() + 2] == 0) {
                                out.add(new Coordinate(curr.getRow() + 2, curr.getCol() + 2));
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }
        return out;
    }

    // When the user clicks and drags a checkers, this function is called. It checks if a move
    // is valid and updates the game state/array accordingly.
    public boolean playMove(Coordinate start, Coordinate end) {

        int captured = -1;
        boolean kinged = false;

        if (!validMoves(start).contains(end)) {
            System.out.println("Move not valid");
            return false;
        }

        int distance = Math.abs(start.getRow() - end.getRow()) +
            Math.abs(start.getCol() - end.getCol());
        int piece = this.arr[start.getRow()][start.getCol()];

        if (distance == 2) {
            this.arr[start.getRow()][start.getCol()] = 0;
            this.arr[end.getRow()][end.getCol()] = piece;
        } else {
            this.arr[start.getRow()][start.getCol()] = 0;
            this.arr[end.getRow()][end.getCol()] = piece;
            captured = this.arr[(start.getRow() + end.getRow()) / 2]
                [(start.getCol() + end.getCol()) / 2];
            this.arr[(start.getRow() + end.getRow()) / 2]
                [(start.getCol() + end.getCol()) / 2] = 0;
            this.numBlackPieces = 0;
            this.numWhitePieces = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.arr[i][j] == 1 || this.arr[i][j] == 2) {
                        this.numWhitePieces++;
                    } else if (this.arr[i][j] == 3 || this.arr[i][j] == 4) {
                        this.numBlackPieces++;
                    }
                }
            }
        }

        if (piece == 1 && end.getRow() == 7) {
            this.arr[end.getRow()][end.getCol()] = 2;
            kinged = true;
        } else if (piece == 3 && end.getRow() == 0) {
            this.arr[end.getRow()][end.getCol()] = 4;
            kinged = true;
        }

        this.playerTurn = !this.playerTurn;
        this.numTurns++;
        this.moves.push(new Move(start, end, captured, kinged));

        if (this.isGameOver()) {
            System.out.println("Game Over");
            this.gameOver = true;
        }

        return true;
    }

    // Undoes the last move. If no moves have been played yet, then it does nothing.
    public void undo() {
        if (this.moves.isEmpty()) {
            return;
        }

        Move move = this.moves.pop();
        Coordinate start = move.getStart();
        Coordinate end = move.getEnd();
        if (move.getKinged()) {
            this.arr[start.getRow()][start.getCol()] = this.arr[end.getRow()][end.getCol()] - 1;
        } else {
            this.arr[start.getRow()][start.getCol()] = this.arr[end.getRow()][end.getCol()];
        }
        this.arr[end.getRow()][end.getCol()] = 0;
        if (move.getCaptured() != -1) {
            this.arr[(start.getRow() + end.getRow())/2][(start.getCol() + end.getCol())/2] = move.getCaptured();
        }
        this.playerTurn = !this.playerTurn;
        this.numTurns--;
        if (this.gameOver) {
            this.gameOver = false;
        }
    }

    // Checks if the game is over.
    public boolean isGameOver() {
        if (this.numWhitePieces == 0 || this.numBlackPieces == 0) {
            gameOver = true;
            return true;
        }
        Boolean out = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    if (!validMoves(new Coordinate(i, j)).isEmpty()) {
                        gameOver = false;
                        out = false;
                    }
                } catch (Exception ignored) { }
            }
        }
        gameOver = out;
        return out;
    }

    // Returns the array of the board. For testing purposes
    public int[][] getBoard() {
        int[][] copy = new int[8][8];
        for (int i = 0; i < 8; i++) {
            copy[i] = this.arr[i];
        }
        return copy;
    }

    // Saves the state of the game to a file.
    public void saveGame(File file) { // line 1 = player turn, line 2-9 = array
        BufferedWriter bw = null;
        FileWriter fw;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);
            if (fw == null || bw == null) {
                throw new IllegalArgumentException("A writer is null");
            }

            if (this.playerTurn) { // saves player turn
                bw.write("1");
            } else {
                bw.write("0");
            }

            bw.newLine();
            for (int i = 0; i < 8; i++) {
                bw.write(Arrays.toString(this.arr[i])); // saves array state
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    // Lets players load saved games from files.
    public void loadGame(File file) {
        init();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exist.");
        }

        try {
            if (Integer.valueOf(reader.readLine()) == 0) {
                this.playerTurn = false;
            }

            for (int i = 0; i < 8; i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(reader.readLine());
                this.arr[i][0] = builder.charAt(1) - 48;
                this.arr[i][1] = builder.charAt(4) - 48;
                this.arr[i][2] = builder.charAt(7) - 48;
                this.arr[i][3] = builder.charAt(10) - 48;
                this.arr[i][4] = builder.charAt(13) - 48;
                this.arr[i][5] = builder.charAt(16) - 48;
                this.arr[i][6] = builder.charAt(19) - 48;
                this.arr[i][7] = builder.charAt(22) - 48;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal file passed");
        }
    }

    public boolean getPlayerTurn() {
        return this.playerTurn;
    }
}