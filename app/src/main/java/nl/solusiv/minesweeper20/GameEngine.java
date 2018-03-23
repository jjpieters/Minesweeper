package nl.solusiv.minesweeper20;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import nl.solusiv.minesweeper20.cell.Cell;
import nl.solusiv.minesweeper20.util.ActionBar;
import nl.solusiv.minesweeper20.util.Generator;

/**
 * Created by super on 17-3-2018.
 */

public class GameEngine extends Activity {
    private static GameEngine instance;

    private int bombNumber = 50, width = 20, height = 20;
    private boolean game = true;
    private boolean startedTimer = false;

    Cell[][] MinesweeperGrid;

    private Context context;

    private ActionBar actionBar;

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context) {
        this.context = context;

        // Create the grid and store
        MinesweeperGrid = Generator.generate(context, bombNumber, width, height);
        PrintGrid(MinesweeperGrid, width, height);

        actionBar = new ActionBar(context, bombNumber);
    }

    public void invalidateGridView(){
        instance = new GameEngine();
    }


    public void flag(int x, int y) {
        if(getCellAt(x,y).isFlagged()){
            getCellAt(x,y).setFlagged(false);
            actionBar.incrementBombs();
        } else {
            getCellAt(x,y).setFlagged(true);
            actionBar.decrementBombs();
        }
        getCellAt(x, y).invalidate();
    }

    /*
     * Print grid in console
     */
    public void PrintGrid(Cell[][] grid, int width, int height) {
        String printedText = "";
        for (int x = 0; x < width; x++) {
            printedText = "|";
            for (int y = 0; y < height; y++) {
                printedText += String.valueOf(grid[x][y].getValue()).replace("-1", "B") + " | ";
            }
            System.out.println(printedText);
        }
    }


    public Cell getCellAt(int position) {
        int x = position % width;
        int y = position / height;
        return MinesweeperGrid[x][y];
    }

    public Cell getCellAt(int x, int y) {
        return MinesweeperGrid[x][y];
    }

    public void click(int x, int y) {
        if(this.game == false){
            return;
        }
        if(!startedTimer){
            this.startedTimer = true;
            actionBar.startTimer();
        }

        if (x >= 0 && y >= 0 && x < width && y < height && !getCellAt(x, y).isClicked()) {
            getCellAt(x, y).setClicked();

            if (getCellAt(x, y).getValue() == 0) {
                for (int xt = -1; xt <= 1; xt++) {
                    for (int yt = -1; yt <= 1; yt++) {
                        if (x + xt >= 0 && y + yt >= 0 && x + xt < width && y + yt < height) {
                            if (getCellAt(x + xt, y + yt).getValue() != -1) {
                                click(x + xt, y + yt);
                            }
                        }
                    }

                }
            }

            if (getCellAt(x, y).isBomb()) {
                onGameLost();
            }
        }

        checkEnd();

    }

    public void setOptions(int bombs, int width, int height) {
        this.bombNumber = bombs;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return this.height;
    }

    private void onGameLost() {
        actionBar.stopTimer();
//        actionBar.endSmiley();
        this.game = false;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                getCellAt(x, y).setRevealed();
            }
        }
    }

    private boolean checkEnd() {
        int bombNotFound = bombNumber;
        int notRevealed = width * height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) {
                    notRevealed--;
                }

                if (getCellAt(x, y).isFlagged() && getCellAt(x, y).isBomb()) {
                    bombNotFound--;
                }
            }
        }

        if (bombNotFound == 0 && notRevealed == 0) {
            actionBar.stopTimer();
            Toast.makeText(context, "Game won!", Toast.LENGTH_SHORT).show();
            this.game = false;
        }
        return false;
    }
}
