package com.ezwriter.speechtotext.Camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.GridView;

import com.ezwriter.speechtotext.R;

public class Gallery extends AppCompatActivity {

    private GalleryDbHelper dbHelper;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        this.gridView = (GridView) findViewById(R.id.activity_main_grid_view);
        this.dbHelper = new GalleryDbHelper(this);
        this.gridView.setAdapter(new GalleryAdapter(this, this.dbHelper.readAllMemories(), false));
        this.gridView.setEmptyView(findViewById(R.id.activity_main_empty_view));
    }

    @Override
    protected void onResume() {
        super.onResume();

        ((CursorAdapter)gridView.getAdapter()).swapCursor(this.dbHelper.readAllMemories());
    }
}
