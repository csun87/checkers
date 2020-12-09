import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame implements Runnable {

    @Override
    public void run() {
        final JFrame frame = new JFrame("Checkers");
        frame.getContentPane().setLayout(null);

        JPanel statusPanel = new JPanel();
        JLabel statusLabel = new JLabel("Player 1's turn");
        statusPanel.add(statusLabel);
        statusPanel.setBounds(0, 0, 1000, 30);
        frame.add(statusPanel);

        JMenuBar mb = new JMenuBar();
        JMenu actions = new JMenu("Actions");
        JMenuItem loadGame = new JMenuItem("Load Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        actions.add(loadGame);
        actions.add(saveGame);
        mb.add(actions);
        frame.setJMenuBar(mb);

        BoardPanel boardPanel = new BoardPanel(statusLabel);
        boardPanel.setSize(new Dimension(800, 800));
        frame.add(boardPanel, BorderLayout.CENTER);
        boardPanel.setLocation(0, 80);

        JPanel buttonsPanel = new JPanel();
        JButton undoBtn = new JButton("Undo Move");

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.undo();
            }
        });

        JButton saveBtn = new JButton("Save Game");

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.saveGame();
            }
        });

        JButton loadBtn = new JButton("Load Game");

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.loadGame();
            }
        });

        JButton backBtn = new JButton("Start Screen");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartScreen();
                frame.dispose();
            }
        });

        buttonsPanel.add(undoBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(150, 20)));
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(150, 20)));
        buttonsPanel.add(loadBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(150, 20)));
        buttonsPanel.add(backBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(150, 20)));
        frame.add(buttonsPanel);
        buttonsPanel.setBounds(815, 100, 150, 600);

        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("checker_logo.png").getImage());
        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
