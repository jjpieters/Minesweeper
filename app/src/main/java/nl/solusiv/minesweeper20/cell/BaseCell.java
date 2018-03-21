package nl.solusiv.minesweeper20.cell;

import android.content.Context;
import android.view.View;

import nl.solusiv.minesweeper20.GameEngine;

/**
 * Created by super on 17-3-2018.
 */

public class BaseCell extends View {

    private int value;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x,y;

    public BaseCell(Context context) {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb = false;
        isRevealed = false;
        isClicked = false;
        isFlagged = false;

        if (value == -1) {
            isBomb = true;
        }

        this.value = value;
    }

    public boolean isBomb(){
        return this.isBomb;
    }

    public void setBomb(boolean bomb){
        this.isBomb = bomb;
    }

    public boolean isRevealed(){
        return isRevealed;
    }

    public void setRevealed(){
        isRevealed = true;
        invalidate();
    }

    public boolean isClicked(){
        return isClicked;
    }

    public void setClicked(){
        this.isClicked = true;
        this.isRevealed = true;

        //
        invalidate();
    }

    public void setFlagged(boolean flagged){
        this.isFlagged = flagged;
    }

    public boolean isFlagged(){
        return this.isFlagged;
    }

    public int getXPos() {
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getYPos() {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;

        invalidate();

    }

    public int[][] getPosition(){
        return new int[x][y];
    }
}
