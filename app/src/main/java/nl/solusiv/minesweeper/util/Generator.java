package nl.solusiv.minesweeper.util;

import java.util.Random;

/**
 * Created by super on 10-3-2018.
 */

public class Generator {

    public static int[][] generate(int bombnumber, final int width, final int height) {
        // Random for generating numbers
        Random r = new Random();

        int[][] grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            grid[x] = new int[height];
        }

        while (bombnumber > 0) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            // -1 is the bomb
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                bombnumber--;
            }
        }
        grid = calculateNeightbours(grid, width, height);

        return grid;

    }


    private static int[][] calculateNeightbours(int[][] grid, final int width, final int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = getNeighbourNumber(grid, x, y, width, height);
            }
        }
        return grid;
    }

    private static int getNeighbourNumber(final int grid[][], final int x, final int y, final int width, final int height) {
        if (grid[x][y] == -1) {
            return -1;
        }

        int count = 0;

        if (isMineAt(grid, x - 1, y + 1, width, height)) { // Top left
            count++;
        }
        if (isMineAt(grid, x, y + 1, width, height)) { // Top
            count++;
        }
        if (isMineAt(grid, x + 1, y + 1, width, height)) { // Top right
            count++;
        }
        if (isMineAt(grid, x - 1, y, width, height)) { // Left
            count++;
        }
        if (isMineAt(grid, x + 1, y, width, height)) { // Right
            count++;
        }
        if (isMineAt(grid, x - 1, y - 1, width, height)) { // Left bottom
            count++;
        }
        if (isMineAt(grid, x, y - 1, width, height)) { // Bottom
            count++;
        }
        if (isMineAt(grid, x + 1, y - 1, width, height)) { // Bottom right
            count++;
        }

        return count;
    }

    private static boolean isMineAt(final int[][] grid, final int x, final int y, final int width, final int height) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            if (grid[x][y] == -1) {
                return true;
            }
        }
        return false;
    }
}
