package com.lexa.linemy.fragment_button;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lexa.linemy.R;

/**
 * Created by Lexa on 08.06.2016.
 */
public class FragmentStepFive extends Fragment {

    private OnFragmentStateFive mListener;

    public interface OnFragmentStateFive {
        void onFragmentStateFive (float i, String text);
        void onFragmentStateFiveCancel ();
        void onFragmentStateFiveCancelAll ();
        void onFragmentStateFiveSave ();
    }


    public static FragmentStepFive newInstance() {
        FragmentStepFive fragmentStepFive = new FragmentStepFive();
        return fragmentStepFive;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentStateFive) activity;
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
        View view = inflater.inflate(R.layout.button_panel_streets, container, false);
        final EditText imageButton = (EditText)view.findViewById(R.id.street_horiz);
        imageButton.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mListener.onFragmentStateFive(360.0f,imageButton.getText().toString());
                   imageButton.setText(null);
                }
                return false;
            }

        });

        final EditText imageButton2 = (EditText)view.findViewById(R.id.street_vert);
        imageButton2.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mListener.onFragmentStateFive(90.0f,imageButton2.getText().toString());
                    imageButton2.setText(null);
                }
                return false;
            }

        });
        Button button = (Button)view.findViewById(R.id.cancel_5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFiveCancel();
            }
        });
        Button button2 = (Button)view.findViewById(R.id.save_5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFiveSave();
            }
        });
        Button button3 = (Button)view.findViewById(R.id.go_in_start_4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateFiveCancelAll();
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
