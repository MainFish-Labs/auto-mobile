package com.lexa.linemy.fragment_button;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.lexa.linemy.R;

/**
 * Created by Lexa on 07.06.2016.
 */
public class FragmentStepTwo extends Fragment {

    private OnFragmentStateTwo mListener;

    public interface OnFragmentStateTwo {
        void onFragmentStateTwo(int i);
        void onFragmentStateTwoCancel();
        void onFragmentStateTwoRotate();
        void onFragmentStateTwoCancelAll();
        void onFragmentStateTwoSave();

    }


    public static FragmentStepTwo newInstance() {
        FragmentStepTwo fragmentStepTwo = new FragmentStepTwo();
        return fragmentStepTwo;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentStateTwo) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_panel_cars, container, false);
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.car_is);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateTwo(1);
            }
        });
        ImageButton imageButton2 = (ImageButton)view.findViewById(R.id.car_is_two);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateTwo(2);
            }
        });
        Button button = (Button)view.findViewById(R.id.cancel_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateTwoCancel();
            }
        });
        Button button2 = (Button)view.findViewById(R.id.save_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateTwoSave();
            }
        });
        Button button3 = (Button)view.findViewById(R.id.rotate);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateTwoRotate();
            }
        });
        Button button4 = (Button)view.findViewById(R.id.go_in_start);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateTwoCancelAll();
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