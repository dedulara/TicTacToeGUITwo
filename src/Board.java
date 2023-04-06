import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Board extends JFrame
{
    private static final int ROW = 3;
    private static final int COL = 3;

    static Tile[][] board = new Tile[ROW][COL];

    static String player = "X";

    static int winMoves = 5;
    static int tieMoves = 7;
    static int moveCount = 0;

    static JPanel buttonPanel;

    public static void Board()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = new Tile(row, col);
                board[row][col].setText("");
                board[row][col].setFont(new Font(null, Font.BOLD, 50));
                board[row][col].setBackground(Color.white);

                buttonPanel.add(board[row][col]);
                int selectedRow = row;
                int selectedCol = col;

                board[row][col].addActionListener((ActionEvent ae) ->
                {
                    if (isValidMove(selectedRow, selectedCol))
                    {
                        board[selectedRow][selectedCol].setText(player);
                        moveCount++;

                        if (moveCount >= winMoves) {if(isWin(player)) {gameOverPopUp("Win", player);}}
                        if (moveCount >= tieMoves) {if (isTie()) {gameOverPopUp("Tie", player);}}

                        player = togglePlayer(player);
                        Game.turnTF.setText("Turn: " + player);
                    }
                    else {JOptionPane.showMessageDialog(null, "Invalid Move");}
                });
            }
        }
    }

    public static void gameOverPopUp(String outcomeString, String player)
    {
        int popUpGameOver;
        String gameOutcomeString = "";

        if (outcomeString == "Win") {gameOutcomeString = "Player " + player + " wins.\n\nPlay again?";}
        if (outcomeString == "Tie") {gameOutcomeString = "It's a tie.\n\nPlay again?";}

        popUpGameOver = JOptionPane.showConfirmDialog(null, gameOutcomeString, "Game over", JOptionPane.YES_NO_OPTION);

        if (popUpGameOver == JOptionPane.YES_OPTION) {clearBoard();}
        else {System.exit(0);}
    }


    public static void clearBoard()
    {
        for( int row = 0; row < 3; row++) {for(int col= 0; col < 3; col++) {board[row][col].setText("");}}
        player = "O";
    }

    public static boolean isValidMove(int row, int col)
    {
        boolean validMove;
        if(board[row][col].getText().equals("")){validMove = true;}
        else{validMove = false;}
        return validMove;
    }

    public static boolean isWin(String player) {return(isColWin(player) || isRowWin(player) || isDiagonalWin(player));}

    public static boolean isColWin(String player)
    {
        boolean hasWon = false;
        for(int x = 0; x < COL; x++) {if(board[0][x].getText().equals(player) && board[1][x].getText().equals(player) && board[2][x].getText().equals(player)) {hasWon = true;}}
        return hasWon;
    }

    public static boolean isRowWin(String player)
    {
        boolean hasWon = false;
        for(int x = 0; x < COL; x++) {if(board[x][0].getText().equals(player) && board[x][1].getText().equals(player) && board[x][2].getText().equals(player)) {hasWon = true;}}
        return hasWon;
    }

    public static boolean isDiagonalWin(String player)
    {
        boolean hasWon = false;
        if(board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player)) {hasWon = true;}
        if(board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player)) {hasWon = true;}
        return hasWon;
    }

    public static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") || board[row][1].getText().equals("X") || board[row][2].getText().equals("X")) {xFlag = true;}
            if(board[row][0].getText().equals("O") || board[row][1].getText().equals("O") || board[row][2].getText().equals("O")) {oFlag = true;}
            if(! (xFlag && oFlag) ) {return false;}
            xFlag = oFlag = false;

        }
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") || board[1][col].getText().equals("X") || board[2][col].getText().equals("X")) {xFlag = true;}
            if(board[0][col].getText().equals("O") || board[1][col].getText().equals("O") || board[2][col].getText().equals("O")) {oFlag = true;}
            if(! (xFlag && oFlag) ) {return false;}
            xFlag = oFlag = false;
        }

        if(board[0][0].getText().equals("X") || board[1][1].getText().equals("X") || board[2][2].getText().equals("X")) {xFlag = true;}
        if(board[0][0].getText().equals("O") || board[1][1].getText().equals("O") || board[2][2].getText().equals("O")) {oFlag = true;}
        if(! (xFlag && oFlag) ) {return false;}
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") || board[1][1].getText().equals("X") || board[2][0].getText().equals("X")) {xFlag =  true;}
        if(board[0][2].getText().equals("O") || board[1][1].getText().equals("O") || board[2][0].getText().equals("O")) {oFlag =  true;}
        if(! (xFlag && oFlag) ) {return false;}

        return true;
    }

    public static String togglePlayer(String player)
    {
        if(player.equals("X")) {return "O";}
        else {return "X";}
    }
}
