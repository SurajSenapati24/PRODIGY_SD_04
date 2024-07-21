/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokusolver;

/**
 *
 * @author Suraj Senapati
 */
public class SudokuSolver {

    private static final int SIZE = 9; // Size of the Sudoku grid

    // Check if it's safe to place a number in a given position
    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if the number is not repeated in the current row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if the number is not repeated in the current column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if the number is not repeated in the current 3x3 box
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solve the Sudoku puzzle using backtracking
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Find an empty cell
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        // Check if placing the number is safe
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursively try to fill the rest of the board
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // If the choice does not lead to a solution, backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true; // All cells are filled, puzzle solved
    }
}

