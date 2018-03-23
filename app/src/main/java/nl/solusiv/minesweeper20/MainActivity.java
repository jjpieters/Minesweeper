package nl.solusiv.minesweeper20;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.toolbar);

        // Create GameEngine
        GameEngine.getInstance().createGrid(this);

        fixGridContentWidth();

    }

    /**
     * Fix the content width of the grid layout
     * Setting the width of the layout containing the Grid by using the amount of grid columns and the width of a column
     */
    private void fixGridContentWidth() {
        // 1: Calculate values
        int gridColumns = GameEngine.getInstance().getWidth(); // Amount
        int columnWidth = 90; // Column width; As defined in `Grid.java`
        int marginPaddingCorrection = 20; // The total of margin and padding applied left and right. (Currently we apply (`activity_main.xml`) a padding of `10dp` in all directions, that means we use 20 because we have the left and right side.

        // 2: Using the density (`dp`) to determine the actual positioning correction in pixels that we need to apply.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float positioningCorrection = marginPaddingCorrection * displayMetrics.density;

        // 3: Calculate the total width for the layout
        int gridLayoutWith = columnWidth * gridColumns + (int) positioningCorrection;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(gridLayoutWith, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 4: Apply it
        FrameLayout linearLayout = findViewById(R.id.layout_grid);
        linearLayout.setLayoutParams(layoutParams); // It will redraw itself
    }
}
