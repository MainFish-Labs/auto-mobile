package com.mainfish.europrotocola04;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by artli on 05.05.2016.
 */
public class ShapeFragment extends Fragment {

    protected DrawView mDrawView;

    public ShapeFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drawing_fragment_hit, container, false);
        mDrawView = (DrawView) rootView.findViewById(R.id.drawingview);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_arrow:
                mDrawView.mCurrentShape = DrawView.ARROW;
                mDrawView.reset();
                break;
            case R.id.action_line:
                mDrawView.mCurrentShape = DrawView.LINE;
                mDrawView.reset();
                break;

        }

        return super.onOptionsItemSelected(item);
    }




    public void onClick(View view) {

        if (view.getId() == R.id.arrow_btn) {
            //draw button clicked
            mDrawView.mCurrentShape = DrawView.ARROW;
            mDrawView.reset();
        } else if (view.getId() == R.id.line_btn) {
            mDrawView.mCurrentShape = DrawView.LINE;
            mDrawView.reset();
        }

    }

}
