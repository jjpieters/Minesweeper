package nl.solusiv.minesweeper.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import nl.solusiv.minesweeper.GameEngine;

/**
 * Created by super on 11-3-2018.
 */

public class Grid extends GridView {

    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);

        setNumColumns(GameEngine.getInstance().getWidth());
        setAdapter(new GridAdapter());
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GameEngine.getInstance().getWidth() * GameEngine.getInstance().getHeight();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent) {
            return GameEngine.getInstance().getCellAt(position);
        }
    }
}
