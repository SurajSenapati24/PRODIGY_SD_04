/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudokusolver;

/**
 *
 * @author Suraj Senapati
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame {

    private static final int SIZE = 9; // Size of the Sudoku grid
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private SudokuSolver solver = new SudokuSolver();

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(SIZE, SIZE));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(cells[row][col]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton loadButton = new JButton("Load Puzzle");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPuzzle();
            }
        });
        controlPanel.add(loadButton);

        JButton solveButton = new JButton("Solve Puzzle");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solvePuzzle();
            }
        });
        controlPanel.add(solveButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void loadPuzzle() {
        // Example Sudoku puzzle (0 represents empty cells)
        int[][] puzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                    cells[row][col].setEditable(false);
                } else {
                    cells[row][col].setText("");
                    cells[row][col].setEditable(true);
                }
            }
        }
    }

    private void solvePuzzle() {
        int[][] board = new int[SIZE][SIZE];
        try {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (!cells[row][col].getText().isEmpty()) {
                        board[row][col] = Integer.parseInt(cells[row][col].getText());
                    } else {
                        board[row][col] = 0;
                    }
                }
            }

            if (solver.solveSudoku(board)) {
                for (int row = 0; row < SIZE; row++) {
                    for (int col = 0; col < SIZE; col++) {
                        cells[row][col].setText(String.valueOf(board[row][col]));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No solution exists for the given puzzle", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuSolverGUI().setVisible(true);
            }
        });
    }
}

