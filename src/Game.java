import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game extends JFrame
{
    JPanel wholePanel;
    JPanel buttonPanel;
    JPanel bottomPanel;
    static JTextField turnTF;
    JButton quitButton;

    String player = "X";

    public static void main(String[] args)
    {
        JFrame framej = new Game();

        framej.setTitle("Tic Tac Toe");
        framej.setVisible(true);
    }

    public Game()
    {
        wholePanel = new JPanel();
        wholePanel.setLayout(new BorderLayout());
        createButtons();
        buttonPanel.setPreferredSize(new Dimension(300,300));
        wholePanel.setBackground(Color.white);
        wholePanel.add(buttonPanel);
        createBottomPanel();
        wholePanel.add(bottomPanel, BorderLayout.SOUTH);
        add(wholePanel);
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createButtons()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        Board.Board();
        buttonPanel.add(Board.buttonPanel);
    }

    public void createBottomPanel()
    {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new GridLayout(1,2));
        turnTF = new JTextField();
        turnTF.setEditable(false);
        turnTF.setText("Turn: " + player);
        turnTF.setBackground(Color.white);
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        bottomPanel.add(turnTF);
        bottomPanel.add(quitButton);
    }
}
