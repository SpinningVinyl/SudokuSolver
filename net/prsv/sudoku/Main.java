package net.prsv.sudoku;

public class Main {

    static int[][] grid = {{0, 0, 0, 0, 5, 0, 0, 0, 0},
                           {0, 0, 4, 2, 0, 6, 9, 0, 0},
                           {0, 1, 0, 0, 0, 9, 0, 2, 0},
                           {9, 2, 0, 0, 0, 4, 7, 0, 6},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {8, 0, 6, 3, 0, 0, 0, 4, 5},
                           {0, 5, 0, 7, 0, 0, 0, 1, 0},
                           {0, 0, 2, 9, 0, 1, 5, 0, 0},
                           {0, 0, 0, 0, 3, 0, 0, 0, 0}};

    public static void main(String[] args) {
	    SudokuSolver solver = new SudokuSolver(grid);
	    if (solver.solve()) {
            solver.printGrid();
        } else {
            System.out.println("There is no solution!");
        }
    }
}
