package nl.solusiv.minesweeper20.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import nl.solusiv.minesweeper20.GameEngine;
import nl.solusiv.minesweeper20.R;

/**
 * Created by super on 17-3-2018.
 */

public class ActionBar extends Activity {
    Context context;
    int time = 000;
    int bombs;
    Thread t;

    public ActionBar(final Context context, int bombs) {
        this.bombs = bombs;
        this.context = context;

        updateBombCount(bombs);

        ((Activity)context).findViewById(R.id.smiley).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameEngine.getInstance().invalidateGridView();
            }
        });
//        ((Activity)context).findViewById(R.id.dxLogo).setOnClickListener(new View.OnLongClickListener(){
//            public void onClick(View v){}
//        });
    }

    public void updateBombCount(int bombs){
        String bombsString = String.format("%03d", bombs);

        ((TextView) ((Activity) context).findViewById(R.id.num_of_bombs)).setText(bombsString);

    }

    public void startTimer(){
        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if(time >= 998){
                            break;
                        }
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Add 1 to the timer and make it 3 numbers
                                time++;
                                String bombsString = String.format("%03d", time);
                                ((TextView) ((Activity) context).findViewById(R.id.timer)).setText(bombsString);
                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };

        t.start();
    }


    public void incrementBombs(){
        this.bombs += 1;
        updateBombCount(this.bombs);
    }

    public void decrementBombs(){
        if(this.bombs - 1 >= 0){
            this.bombs -= 1;
            updateBombCount(this.bombs);
        }
    }

    public void stopTimer(){
        t.interrupt();
    }

    public boolean endSmiley(){
        return false;
    }
}
