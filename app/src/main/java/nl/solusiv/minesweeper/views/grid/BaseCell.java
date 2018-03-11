package nl.solusiv.minesweeper.views.grid;

import android.content.Context;
import android.view.View;

import nl.solusiv.minesweeper.GameEngine;

/**
 * Created by super on 11-3-2018.
 */

public class BaseCell extends View {

    private int value;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x, y;
    private int position;

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

    public void setRevealed(boolean revealed){
        isRevealed = revealed;
    }

    public boolean isClicked(){
        return isClicked;
    }

    public void setClicked(){
        this.isClicked = true;
        this.isRevealed = true;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;

        x = position % GameEngine.getInstance().getWidth();
        y = position / GameEngine.getInstance().getHeight();

        invalidate();
    }

}
