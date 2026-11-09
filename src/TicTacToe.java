import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    // Start the game
    // Clear the board
    // Let player X go first
    // Ask the player for a row and column
    // Check if the move is valid
    // Check if someone won or tied
    // Switch players
    // Ask if they want to play again

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            String player = "X";
            int moves = 0;
            boolean gameOver = false;

            // Start the game
            while (!gameOver) {
                display();
                System.out.println("Player " + player + "'s turn");

                int row;
                int col;

                // Get a valid move
                do {
                    row = SafeInput.getRangedInt(console, "Enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(console, "Enter column", 1, COLS) - 1;

                    if (!isValidMove(row, col)) {
                        System.out.println("That spot is already taken. Try again.");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moves++;

                // Check for a win or tie
                if (moves >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    // Switch players
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }
            }

            playAgain = SafeInput.getYNConfirm(console, "Play again?");
        } while (playAgain);

        System.out.println("Thanks for playing!");
        console.close();
    }

    // Clear the board
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Display the board
    private static void display() {
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(" " + board[row][col] + " ");

                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    // Check if a move is valid
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    // Check if a player wins
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player)
                || isDiagnalWin(player);
    }

    // Check rows
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            boolean win = true;

            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    // Check columns
    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            boolean win = true;

            for (int row = 0; row < ROWS; row++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    // Check diagonals
    private static boolean isDiagnalWin(String player) {
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < ROWS; i++) {
            if (!board[i][i].equals(player)) {
                diagonal1 = false;
            }

            if (!board[i][COLS - 1 - i].equals(player)) {
                diagonal2 = false;
            }
        }

        return diagonal1 || diagonal2;
    }

    // Check if the board is full
    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }
}
