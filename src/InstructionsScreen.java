import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsScreen extends JFrame implements ActionListener {

    private final JButton backBtn;
    private final JPanel words;
    private boolean visible;

    public InstructionsScreen() {
        backBtn = new JButton("Back");
        backBtn.setBounds(50, 50, 75, 50);
        backBtn.addActionListener(this);

        words = new JPanel();
        words.setBackground(new Color(112, 68, 17));
        words.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        words.setBounds(200, 100, 600, 750);
        words.setLayout(null);

        JLabel checkers = new JLabel("Checkers");
        checkers.setFont(new Font("Courier New", Font.BOLD, 64));
        checkers.setForeground(Color.WHITE);
        checkers.setBounds(145, 10, 310, 50);
        words.add(checkers);

        JLabel instructs = new JLabel();
        instructs.setFont(new Font("Courier New", Font.PLAIN, 14));
        instructs.setForeground(Color.WHITE);
        instructs.setBounds(10, 50, 580, 700);
        instructs.setText("<html><p>" +
            "This is a game for two players. The goal is to capture all of your opponent's" +
            " checkers from the board by capturing them. Players will take turns moving their" +
            " checkers. Checkers can generally only be moved diagonally forwards one square each. " +
            "You can capture other checkers by jumping over them. These jumps can be chained" +
            " together to capture multiple checker pieces at once. Checkers cannot jump over" +
            " checkers of the same color. When a checker reaches the end of the board (the " +
            "row closest to the opposing player), it is crowned and can move either forwards or" +
            " backwards, but still only diagonally. The last player with checkers still on " +
            "the board is victorious. <br>" +
            "Additionally, this digital version of checkers allows you to save and replay games. " +
            "</p></html>");
        words.add(instructs);

        this.setTitle("Checkers");
        this.add(words);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("checker_logo.png").getImage());
        this.setSize(1000, 1000);
        this.getContentPane().setBackground(new Color(246, 209, 87));
        this.getContentPane().add(backBtn);
        visible = true;
        this.setVisible(visible);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public void toggleVisiblity() {
        visible = !visible;
        this.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backBtn)) {
            StartScreen start = new StartScreen();
            this.dispose();
        }
    }
}
