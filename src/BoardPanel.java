import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class GameScreen extends JPanel {

    private Board board;
    private Point clicked;
    private Point released;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    public GameScreen() {
        board = new Board();
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicked = e.getPoint();
                System.out.println("this is running");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released = e.getPoint();
                if (board.playMove(convertPoint(clicked), convertPoint(released))) {
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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
                        Image blueChecker = ImageIO.read(new File("blue_checker.png"));
                        g.drawImage(blueChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                } else if (piece == 2) {
                    try {
                        Image blueCrownedChecker = ImageIO.read(
                            new File("blue_crowned_checker.png"));
                        g.drawImage(blueCrownedChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                } else if (piece == 3) {
                    try {
                        Image redChecker = ImageIO.read(
                            new File("red_checker.png"));
                        g.drawImage(redChecker,
                            col * 100 + 10,
                            row * 100 + 10, null);
                    } catch (Exception ignored) { }
                } else if (piece == 4) {
                    try {
                        Image redCrownedChecker = ImageIO.read(
                            new File("red_crowned_checker.png"));
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
        System.out.println("Row: " + y);
        System.out.println("Column: " + x);
        return new Coordinate(y, x);
    }
}
