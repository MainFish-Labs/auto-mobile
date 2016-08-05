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
 * Created by Lexa on 05.06.2016.
 */
public class FragmentStepOne extends Fragment {

    private OnFragmentStateOne mListener;

    public interface OnFragmentStateOne {
        void onFragmentStateOne (int i);
        void onFragmentStateOneCancel ();
        void onFragmentStateOneSave ();
    }


    public static FragmentStepOne newInstance() {
        FragmentStepOne fragmentStepOne = new FragmentStepOne();
        return fragmentStepOne;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentStateOne) activity;
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
        View view = inflater.inflate(R.layout.button_panel_line, container, false);
        ImageButton imageButton4 = (ImageButton)view.findViewById(R.id.line_stright_edit);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateOne(9);
            }
        });
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.line_stright);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateOne(1);
            }
        });
        ImageButton imageButton2 = (ImageButton)view.findViewById(R.id.line_duble);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateOne(2);
            }
        });
        ImageButton imageButton3 = (ImageButton)view.findViewById(R.id.dashed_line);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateOne(3);
            }
        });
        Button button = (Button)view.findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateOneCancel();
            }
        });
        Button button2 = (Button)view.findViewById(R.id.save);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateOneSave();
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

