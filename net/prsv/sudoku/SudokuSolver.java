package net.prsv.sudoku;

import java.util.stream.IntStream;

/** @author Pavel Urusov
 * This class solves Sudoku puzzles using the backtracking algorithm.
 */

public class SudokuSolver {

    private final int[][] grid;

    public SudokuSolver(int[][] grid) {
        // check if the parameter is a 9x9 grid
        if (grid == null || grid.length != 9 || grid[0].length != 9) {
            throw new IllegalArgumentException("Must be a 9x9 grid");
        }
        // create a copy of the initial grid
        this.grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, grid[i].length);
        }
    }

    public boolean solve() {
        // iterate over rows and columns
        for(int row = 0; row < 9; row++) {
            for(int column = 0; column < 9; column++) {
                // check if the cell is empty
                if(grid[row][column] == 0) {
                    // iterate all numbers from 1 to 9
                    for(int n = 1; n <= 9; n++) {
                        // is the number legal?
                        if (isLegal(row, column, n)) {
                            grid[row][column] = n; // yes - put it into the cell
                            if(solve()) {
                                return true; // if this leads to a solution - return true
                            }
                            grid[row][column] = 0; // else backtrack
                        }
                    }
                    // iterated over all possible values of n and found no solution
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid() {
        for (int row = 0; row < 9; row ++) {
            for (int column = 0; column < 9; column++) {
                System.out.print(grid[row][column] + "  ");
            }
            System.out.println();
        }
    }

    private boolean isLegal(int row, int column, int n) {
        // checks if cell position is within the bounds
        // and n is between 1 and 9 (inclusive)
        if (row < 0 || row > 8 || column < 0 || column > 8 || n < 1 || n > 9) {
            return false;
        }
        // check if n already present in the current row / column
        if (IntStream.range(0,9).anyMatch(k -> (grid[row][k] == n) || (grid[k][column] == n))) {
            return false;
        }
        // get the top left corner of the current 3x3 square
        int gridRow = (row / 3) * 3;
        int gridColumn = (column / 3) * 3;
        // check if n already present in the current 3x3 square
        return IntStream.range(0, 3).noneMatch(i -> IntStream.range(0, 3).anyMatch(j -> grid[gridRow + i][gridColumn + j] == n));
    }
}
