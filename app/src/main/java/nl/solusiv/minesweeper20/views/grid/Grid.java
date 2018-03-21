package nl.solusiv.minesweeper20.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import nl.solusiv.minesweeper20.GameEngine;
import nl.solusiv.minesweeper20.R;

/**
 * Created by super on 17-3-2018.
 */

public class Grid extends GridView {

    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);

        setColumnWidth(90);
        setStretchMode(NO_STRETCH);

        setNumColumns(GameEngine.getInstance().getWidth());
        setAdapter(new GridAdapter());

        this.invalidateViews();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;
        int widthSpec;

        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            // The great Android "hackatlon", the love, the magic.
            // The two leftmost bits in the height measure spec have
            // a special meaning, hence we can't use them to describe height.
            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        }
        else {
            // Any other height should be respected as is.
            heightSpec = heightMeasureSpec;
        }

        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
