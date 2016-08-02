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
public class xEPR_ShapeFragment extends Fragment {

    protected xEPR_DrawView mDrawView;

    public xEPR_ShapeFragment(){
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
        View rootView = inflater.inflate(R.layout.xepr_drawing_fragment_hit, container, false);
        mDrawView = (xEPR_DrawView) rootView.findViewById(R.id.drawingview);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_arrow:
                mDrawView.mCurrentShape = xEPR_DrawView.ARROW;
                mDrawView.reset();
                break;
            case R.id.action_line:
                mDrawView.mCurrentShape = xEPR_DrawView.LINE;
                mDrawView.reset();
                break;

        }

        return super.onOptionsItemSelected(item);
    }




    public void onClick(View view) {

        if (view.getId() == R.id.arrow_btn) {
            //draw button clicked
            mDrawView.mCurrentShape = xEPR_DrawView.ARROW;
            mDrawView.reset();
        } else if (view.getId() == R.id.line_btn) {
            mDrawView.mCurrentShape = xEPR_DrawView.LINE;
            mDrawView.reset();
        }

    }

}
