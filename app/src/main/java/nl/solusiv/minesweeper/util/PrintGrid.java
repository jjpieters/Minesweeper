package nl.solusiv.minesweeper.util;

/**
 * Created by super on 10-3-2018.
 */

public class PrintGrid {

    public static void print(final int[][] grid, final int width, final int height) {
        for (int x = 0; x < width; x++) {
            String printedText = "| ";
            for (int y = 0; y < height; y++) {
                printedText += String.valueOf(grid[x][y]).replace("-1", "B") + " | ";
            }
        }
    }
}