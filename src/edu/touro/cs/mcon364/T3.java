package edu.touro.cs.mcon364;


import java.util.ArrayList;
import java.util.Objects;


public class T3 {
    public enum CellValue {X, O, NONE}

    private final CellValue[][] board = new CellValue[3][3];
    private CellValue currentPlayer = CellValue.X;
    private CellValue returnPlayer = CellValue.X;
    private CellValue statusPlayer = CellValue.X;
    private ArrayList<Integer> winningLocation = new ArrayList<>();
    private String gameState = "InProgress";
    private boolean isWinner;
    private int tie = 0;


    public T3() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = CellValue.NONE;
            }
        }
    }

    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = CellValue.NONE;
            }
        }
        currentPlayer = CellValue.X;
        tie = 0;
        gameState = "InProgress";
    }


    public void makeMove(int row, int col) {
        if (isGameOver()) {
            noMoreMoves();
            return;
        }
        if (!isValidMove(row, col)) {
            return;
        }
        board[row][col] = currentPlayer;
        returnPlayer = currentPlayer;

        isWinner = gameCheck();
        gameState = changeGameState();
        if (isGameOver()) {
            return;
        }
        currentPlayer = (currentPlayer == CellValue.X) ? CellValue.O : CellValue.X;
        statusPlayer = nextPlayer();

    }

    private void noMoreMoves() {
    }

    public CellValue winner() {
        if (Objects.equals(changeGameState(), "DRAW")) {
            return CellValue.NONE;
        }
        return currentPlayer;
    }

    public CellValue nextPlayer() {
        if (returnPlayer == CellValue.X) {
            return statusPlayer = CellValue.O;
        }
        return statusPlayer = CellValue.X;
    }

    public CellValue currentPlayer() {
        return returnPlayer;
    }

    public boolean isGameOver() {
        return !Objects.equals(gameState, "InProgress");
    }

    private boolean isValidMove(int row, int column) {
        if (row < 0 || row > 2 || column < 0 || column > 2) {
            return false;
        } else return board[row][column] == CellValue.NONE;
    }

    private boolean gameCheck() {
        for (int i = 0; i < 3; i++) {
            // Rows
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != CellValue.NONE) {
                winningLocation.add(i);
                winningLocation.add(0);
                winningLocation.add(i);
                winningLocation.add(1);
                winningLocation.add(i);
                winningLocation.add(2);
                return true;
            }
            // columns
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != CellValue.NONE) {
                winningLocation.add(0);
                winningLocation.add(i);
                winningLocation.add(1);
                winningLocation.add(i);
                winningLocation.add(2);
                winningLocation.add(i);
                return true;
            }
        }
        // diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != CellValue.NONE) {
            winningLocation.add(0);
            winningLocation.add(0);
            winningLocation.add(1);
            winningLocation.add(1);
            winningLocation.add(2);
            winningLocation.add(2);
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != CellValue.NONE) {
            winningLocation.add(0);
            winningLocation.add(2);
            winningLocation.add(1);
            winningLocation.add(1);
            winningLocation.add(2);
            winningLocation.add(0);
            return true;
        }
        // Check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == CellValue.NONE) {
                    return false;
                }
            }
        }
        tie = 1;
        return false;
    }

    private String changeGameState() {
        if (!isWinner && tie == 1) {
            gameState = "DRAW";
            return gameState;
        }
        if (isWinner) {
            gameState = currentPlayer.toString();
            return gameState;
        }
        gameState = "InProgress";

        return gameState;
    }

    public ArrayList<Integer> getWinningLocation() {
        return winningLocation;
    }

    public boolean isTie() {
        return Objects.equals(gameState, "DRAW");
    }

    public CellValue[][] getBoard() {
        return this.board;
    }

}
