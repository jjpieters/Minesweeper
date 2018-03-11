package nl.solusiv.minesweeper.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import nl.solusiv.minesweeper.GameEngine;
import nl.solusiv.minesweeper.R;

/**
 * Created by super on 11-3-2018.
 */

public class Cell extends BaseCell implements View.OnClickListener , View.OnLongClickListener{

    public Cell(Context context, int position){
        super(context);

        setPosition(position);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawButton(canvas);

        if(isFlagged()){
            drawFlag(canvas);
        } else if(isRevealed() && isBomb() && !isClicked()){
            drawBomb(canvas);
        } else {
            if(isClicked()){
                if(getValue() == -1){
                    drawBomb(canvas);
                } else {
                    drawNumber(canvas);
                }
            } else {
                drawButton(canvas);
            }
        }
    }

    private void drawFlag(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBomb(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    @Override
    public void onClick(View view) {
        GameEngine.getInstance().click(getXPos(),getYPos());
    }

    @Override
    public boolean onLongClick(View view) {
        GameEngine.getInstance().flag(getXPos(),getYPos());
        return false;
    }

    private void drawNumber(Canvas canvas){
        Drawable drawable = null;

        switch(getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num_8);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

}
