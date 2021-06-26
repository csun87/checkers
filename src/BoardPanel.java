import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

// This class actually creates the visual board panel that will go inside the actual frame of
// the "game screen."
public class BoardPanel extends JPanel {

    private Board board;
    private Point clicked;
    private Point released;
    private JLabel statusLabel;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    public BoardPanel(JLabel status) {
        board = new Board();
        setFocusable(true);
        this.statusLabel = status;
        this.setSize(new Dimension(800, 800));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicked = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released = e.getPoint();
                if (board.playMove(convertPoint(clicked), convertPoint(released))) {
                    repaint();
                    updateStatusText();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(new Dimension(800, 800));
        updateStatusText();

        g.drawLine(0, 0, 800, 0);
        g.drawLine(0, 100, 800, 100);
        g.drawLine(0, 200, 800, 200);
        g.drawLine(0, 300, 800, 300);
        g.drawLine(0, 400, 800, 400);
        g.drawLine(0, 500, 800, 500);
        g.drawLine(0, 600, 800, 600);
        g.drawLine(0, 700, 800, 700);
        g.drawLine(0, 800, 800, 800);

        g.drawLine(0, 0, 0, 800);
        g.drawLine(100, 0, 100, 800);
        g.drawLine(200, 0, 200, 800);
        g.drawLine(300, 0, 300, 800);
        g.drawLine(400, 0, 400, 800);
        g.drawLine(500, 0, 500, 800);
        g.drawLine(600, 0, 600, 800);
        g.drawLine(700, 0, 700, 800);
        g.drawLine(800, 0, 800, 800);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (row % 2 == 0 && col % 2 == 1) {
                    g.fillRect(col * 100, row * 100, 100, 100);
                } else if (row % 2 == 1 && col % 2 == 0) {
                    g.fillRect(col * 100, row * 100, 100, 100);
                }

                int piece = board.getPiece(row, col);
                if (piece == 1) {
                    try {
                        Image blueChecker = ImageIO.read(new File("files/blue_checker.png"));
                        g.drawImage(blueChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                } else if (piece == 2) {
                    try {
                        Image blueCrownedChecker = ImageIO.read(
                            new File("files/blue_crowned_checker.png"));
                        g.drawImage(blueCrownedChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                } else if (piece == 3) {
                    try {
                        Image redChecker = ImageIO.read(
                            new File("files/red_checker.png"));
                        g.drawImage(redChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                } else if (piece == 4) {
                    try {
                        Image redCrownedChecker = ImageIO.read(
                            new File("files/red_crowned_checker.png"));
                        g.drawImage(redCrownedChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                }
            }
        }
    }

    public void reset() {
        board.init();
        repaint();
        requestFocusInWindow();
    }

    public Coordinate convertPoint(Point p) {
        int x = p.x / 100;
        int y = p.y / 100;
        return new Coordinate(y, x);
    }

    public void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SAVE FILES", "csav");
        fileChooser.setDialogTitle("Where to save this file?");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File("savedGame.csav"));
        fileChooser.showOpenDialog(this);

        if (fileChooser.getSelectedFile().toString().endsWith("csav")) {
            board.saveGame(fileChooser.getSelectedFile().getAbsoluteFile());
        } else {
            String fileName = fileChooser.getSelectedFile().toString();
            fileName = fileName.concat(".csav");
            File file = new File(fileName);
            board.saveGame(file);
        }
    }

    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SAVE FILES", "csav");
        fileChooser.setDialogTitle("Where is the saved file to load?");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
        board.loadGame(fileChooser.getSelectedFile().getAbsoluteFile());
        repaint();
        requestFocusInWindow();
    }

    public void newGame() {
        board.init();
        repaint();
    }

    public void undo() {
        board.undo();
        repaint();
        updateStatusText();
        requestFocusInWindow();
    }

    public void updateStatusText() {
        if (board.isGameOver() && board.getPlayerTurn()) {
            statusLabel.setText("Game over! Player 2 wins!");
        } else if (board.isGameOver() && !board.getPlayerTurn()) {
            statusLabel.setText("Game over! Player 1 wins!");
        } else if (board.getPlayerTurn()) {
            statusLabel.setText("Player 1's turn");
        } else if (!board.getPlayerTurn()) {
            statusLabel.setText("Player 2's turn");
        }
    }
}
