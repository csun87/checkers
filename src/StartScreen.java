import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartScreen extends JFrame implements ActionListener {

    private final JButton newGameBtn;
    private final JButton instructionsBtn;
    private boolean visible;

    public StartScreen() {
        JLabel checkersTitle = new JLabel("Checkers");
        checkersTitle.setFont(new Font("Courier New", Font.BOLD, 64));
        checkersTitle.setBounds(345, 200, 310, 50);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(112, 68, 17));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        buttonPanel.setBounds(250, 300, 500, 250);
        buttonPanel.setLayout(null);

        newGameBtn = new JButton("Game Screen");
        newGameBtn.setBounds(100, 50, 300, 50);
        newGameBtn.addActionListener(this);
        newGameBtn.setBorder(BorderFactory.createEtchedBorder());

        instructionsBtn = new JButton("Instructions");
        instructionsBtn.setBounds(100, 150, 300, 50);
        instructionsBtn.addActionListener(this);
        instructionsBtn.setBorder(BorderFactory.createEtchedBorder());

        buttonPanel.add(newGameBtn);
        buttonPanel.add(instructionsBtn);

        this.setTitle("Checkers");
        this.setLayout(null);
        this.getContentPane().add(checkersTitle);
        this.setIconImage(new ImageIcon("checker_logo.png").getImage());
        this.setSize(1000, 1000);
        this.add(buttonPanel);
        this.getContentPane().setBackground(new Color(246, 209, 87));
        this.getContentPane().add(checkersTitle);
        visible = true;
        this.setVisible(visible);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(instructionsBtn)) {
            InstructionsScreen instructions = new InstructionsScreen();
            this.dispose();
        } else if (e.getSource().equals(newGameBtn)) {
            GameFrame frame = new GameFrame();
            frame.run();
            this.dispose();
        }
    }

    public void toggleVisibility() {
        visible = !visible;
        this.setVisible(visible);
    }

}
