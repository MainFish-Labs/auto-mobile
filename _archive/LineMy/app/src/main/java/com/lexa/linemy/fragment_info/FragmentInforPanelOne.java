package com.lexa.linemy.fragment_info;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lexa.linemy.R;

/**
 * Created by Lexa on 05.06.2016.
 */
public class FragmentInforPanelOne extends Fragment {

    private OnFragmentInfoListener mListener;
    private int step;

    public interface OnFragmentInfoListener {
        void onFragmentInfo ();
    }

    public static FragmentInforPanelOne newInstance(int step) {
        FragmentInforPanelOne fragmentInforPanelOne = new FragmentInforPanelOne();
        Bundle args = new Bundle();
        args.putInt("someInt", step);
        fragmentInforPanelOne.setArguments(args);
        return fragmentInforPanelOne;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInfoListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = getArguments().getInt("someInt");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inform_panel_1, container, false);

        String [] arrayStepNumber = getResources().getStringArray(R.array.listArrayStepsIs);

        TextView textView1 = (TextView)view.findViewById(R.id.tv_advise_step_number);
        textView1.setText(String.valueOf(step+1));
        TextView textView2 = (TextView)view.findViewById(R.id.tv_advise);
        textView2.setText(arrayStepNumber[step]);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;

                    case MotionEvent.ACTION_UP:
                        mListener.onFragmentInfo();
                        break;

                    default:
                        break;
                }
                return true;}});

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onPause()
    {
        super.onPause();
    }



    @Override
    public void onResume()
    {
        super.onResume();

    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

