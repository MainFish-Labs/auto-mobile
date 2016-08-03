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
public class FragmentStepFour extends Fragment {

    private OnFragmentStateFour mListener;

    public interface OnFragmentStateFour {
        void onFragmentStateFour (int i);
        void onFragmentStateFourCancel ();
        void onFragmentStateFourCancelAll ();
        void onFragmentStateFourSave ();
    }


    public static FragmentStepFour newInstance() {
        FragmentStepFour fragmentStepFour = new FragmentStepFour();
        return fragmentStepFour;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentStateFour) activity;
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
        View view = inflater.inflate(R.layout.button_panel_signs, container, false);
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.sign_1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFour(3);
            }
        });
        ImageButton imageButton2 = (ImageButton)view.findViewById(R.id.sign_2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFour(4);
            }
        });
        ImageButton imageButton3 = (ImageButton)view.findViewById(R.id.traffic_light);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFour(5);
            }
        });
        Button button = (Button)view.findViewById(R.id.cancel_4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFourCancel();
            }
        });
        Button button2 = (Button)view.findViewById(R.id.save_4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFourSave();
            }
        });
        Button button3 = (Button)view.findViewById(R.id.go_in_start_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFourCancelAll();
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
