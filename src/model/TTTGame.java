package model;

//Author: Andrew Cohn

// the goal of this class is to boil the TicTacToe game prof. mercer wrote into the bare essentials. I started, it but it's missing features.


public class TTTGame {
    char[][] board;

    public class Point {
        public int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public TTTGame() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
    }

    public String toString() {
        String outStr = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                outStr += String.valueOf(board[i][j]) + " ";
            }
            outStr += "\n";
        }
        return outStr;
    }

    public void makeMove(char player, Point move) {
        board[move.row][move.col] = player;
    }

    public boolean isOver(){
        return maxMovesRemaining() == 0 || tied() || didWin('x') || didWin('O');
    }

    public boolean tied() {
        return maxMovesRemaining() == 0 && !didWin('X') && !didWin('O');
    }

    public boolean didWin(char player){
        return wonByCol(player)||wonByRow(player)||wonByDiagonal(player);
    }
    private boolean wonByRow(char playerChar) {
        for (int r = 0; r < 3; r++) {
            int rowSum = 0;
            for (int c = 0; c < 3; c++)
                if (board[r][c] == playerChar)
                    rowSum++;
            if (rowSum == 3)
                return true;
        }
        return false;
    }

    private boolean wonByCol(char playerChar) {
        for (int c = 0; c < 3; c++) {
            int colSum = 0;
            for (int r = 0; r < 3; r++)
                if (board[r][c] == playerChar)
                    colSum++;
            if (colSum == 3)
                return true;
        }
        return false;
    }

    private boolean wonByDiagonal(char playerChar) {
        // Check Diagonal from upper left to lower right
        int sum = 0;
        for (int r = 0; r < 3; r++)
            if (board[r][r] == playerChar)
                sum++;
        if (sum == 3)
            return true;

        // Check Diagonal from upper right to lower left
        sum = 0;
        for (int r = 3 - 1; r >= 0; r--)
            if (board[3 - r - 1][r] == playerChar)
                sum++;
        if (sum == 3)
            return true;

        // No win on either diagonal
        return false;
    }

    public int maxMovesRemaining() {
        int result = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '_')
                    result++;
            }
        }
        return result;
    }
}


