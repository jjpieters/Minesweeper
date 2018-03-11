package nl.solusiv.minesweeper;

import android.content.Context;
import android.view.View;

import nl.solusiv.minesweeper.util.Generator;
import nl.solusiv.minesweeper.util.PrintGrid;
import nl.solusiv.minesweeper.views.grid.Cell;

/**
 * Created by super on 10-3-2018.
 */

public class GameEngine {
    private static GameEngine instance;

    private static int BOMB_NUMBER = 10;
    private static int WIDTH = 10;
    private static int HEIGHT = 10;
    private Context context;

    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public GameEngine() {

    }

    public void createGrid(Context context) {
        this.context = context;

        // Create the grid and store
        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);
        PrintGrid.print(GeneratedGrid, WIDTH, HEIGHT);
        setGrid(context, GeneratedGrid);
    }

    public int getWidth() {
        return this.WIDTH;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    private void setGrid(final Context context, final int[][] grid) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (MinesweeperGrid[x][y] == null) {
                    MinesweeperGrid[x][y] = new Cell(context, y * HEIGHT + x);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / HEIGHT;
        return MinesweeperGrid[x][y];
    }

    public Cell getCellAt(int x, int y) {
        return MinesweeperGrid[x][y];
    }

    public void click(int x, int y) {
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked()) {
            getCellAt(x, y).setClicked();

            if (getCellAt(x, y).getValue() == 0) {
                for (int xt = -1; xt <= 1; xt++) {
                    for (int yt = -1; yt <= 1; yt++) {
                        if (xt != yt) {
                            click(x + xt, y + yt);
                        }
                    }
                }
            }

            if(getCellAt(x,y).isBomb()){
                onGameLost();
            }
        }
    }

    public void flag(int x, int y){
        boolean isFlagged = getCellAt(x, y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();
    }

    private void onGameLost(){
        // Handle Lost Game
    }
}
