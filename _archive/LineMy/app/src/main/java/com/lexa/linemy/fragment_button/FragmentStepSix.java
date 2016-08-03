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
 * Created by Lexa on 08.06.2016.
 */
public class FragmentStepSix extends Fragment {

    private OnFragmentStateSix mListener;

    public interface OnFragmentStateSix {
        void onFragmentStateSix(int i);
        void onFragmentStateSixCancel();
        void onFragmentStateSixCancelAll();
        void onFragmentStateSixSave();

    }


    public static FragmentStepSix newInstance() {
        FragmentStepSix fragmentStepSix = new FragmentStepSix();
        return fragmentStepSix;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentStateSix) activity;
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
        View view = inflater.inflate(R.layout.button_panel_edit, container, false);
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.line_stright_2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateSix(7);
            }
        });
        Button button = (Button)view.findViewById(R.id.cancel_6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateSixCancel();
            }
        });
        Button button2 = (Button)view.findViewById(R.id.save_6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateSixSave();
            }
        });
        Button button3 = (Button)view.findViewById(R.id.go_in_start_5);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateSixCancelAll();
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
