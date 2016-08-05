package com.lexa.linemy.fragment_info;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lexa.linemy.R;

public class FragmentInforPanelTwo extends Fragment {


    private OnFragmentInfoListenerAll mListener;
    private int step;
    private PointF startPoint, endPoint;
    public interface OnFragmentInfoListenerAll {
        void onFragmentInfoAll ();
    }


    public static FragmentInforPanelTwo newInstance(int step) {
        FragmentInforPanelTwo fragmentInforPanelTwo = new FragmentInforPanelTwo();
        Bundle args = new Bundle();
        args.putInt("someInt", step);
        fragmentInforPanelTwo.setArguments(args);
        return fragmentInforPanelTwo;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInfoListenerAll) activity;
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
        View view = inflater.inflate(R.layout.inform_panel_2, container, false);
        String [] arrayStep = getResources().getStringArray(R.array.listArraySteps);
        String [] arrayWhat = getResources().getStringArray(R.array.listArrayWhat);
        String [] arrayHow = getResources().getStringArray(R.array.listArrayHow);

        TextView textView1 = (TextView)view.findViewById(R.id.tv_advise_1);
        textView1.setText(arrayStep[step]);
        TextView textView2 = (TextView)view.findViewById(R.id.tv_advise_2);
        textView2.setText(arrayWhat[step]);
        TextView textView3 = (TextView)view.findViewById(R.id.tv_advise_3);
        textView3.setText(arrayHow[step]);

        final ScrollView scrollView = (ScrollView)view.findViewById(R.id.tv_advise_scroll);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startPoint = new PointF(event.getX(), event.getY());
                        endPoint = new PointF(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        scrollView.scrollBy(0, (int) (endPoint.y - event.getY()));
                        endPoint.y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endPoint.x = event.getX();
                        endPoint.y = event.getY();
                        if(Math.abs(startPoint.x - endPoint.x)>=250){
                        mListener.onFragmentInfoAll();
                        break;
                        }

                    default:
                        break;
                }
                return true;
            }
        });

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
