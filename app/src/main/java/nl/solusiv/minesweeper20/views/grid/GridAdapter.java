package nl.solusiv.minesweeper20.views.grid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import nl.solusiv.minesweeper20.GameEngine;

/**
 * Created by super on 17-3-2018.
 */

public class GridAdapter extends BaseAdapter {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        return GameEngine.getInstance().getCellAt(position);
    }
}
