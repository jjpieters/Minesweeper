package nl.solusiv.minesweeper20.util;

import android.content.Context;

import java.util.Random;

import nl.solusiv.minesweeper20.cell.Cell;

/**
 * Created by super on 17-3-2018.
 */

public class Generator {

    public static Cell[][] generate(Context context, int bombnumber, final int width, final int height) {
        // Random for generating numbers
        Random r = new Random();

        Cell[][] grid = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                grid[x][y] = new Cell(context, x,y);
            }
        }

        while (bombnumber > 0) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            // -1 is the bomb
            if (grid[x][y].getValue() != -1) {
                grid[x][y].setValue(-1);
                grid[x][y].invalidate();
                bombnumber--;
            }
        }
        grid = calculateNeigbours(grid, width, height);

        return grid;
    }

    private static Cell[][] calculateNeigbours(Cell[][] grid, int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y].setValue(getBombNumber(grid, x, y, width, height));
                grid[x][y].invalidate();
            }
        }

        return grid;
    }


    /*
     * Get the number of bombs for a cell
     */
    private static int getBombNumber(Cell[][] grid, int x, int y, int width, int height) {
        int bombNumber = 0;
        // Check if the field is a bomb and return that is is a bomb (-1)
        if (grid[x][y].getValue() == -1) {
            return -1;
        }

        if (isMineAt(grid, x - 1, y - 1, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x, y - 1, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x + 1, y - 1, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x - 1, y, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x + 1, y, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x - 1, y + 1, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x, y + 1, width, height)) {
            bombNumber++;
        }
        if (isMineAt(grid, x + 1, y + 1, width, height)) {
            bombNumber++;
        }
        return bombNumber;
    }

    /*
     * Check if there is a bomb
     */
    private static boolean isMineAt(Cell[][] grid, int x, int y, int width, int height) {
        if (x > -1 && y > -1 && x < width && y < height) {
            if (grid[x][y].getValue() == -1) {
                return true;
            }
        }
        return false;
    }


}
