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
public class FragmentStepThree extends Fragment {

    private OnFragmentStateThree mListener;

    public interface OnFragmentStateThree {
        void onFragmentStateThree (int i);
        void onFragmentStateThreeCancel ();
        void onFragmentStateThreeCancelAll ();
        void onFragmentStateThreeSave ();
    }


    public static FragmentStepThree newInstance() {
        FragmentStepThree fragmentStepThree = new FragmentStepThree();
        return fragmentStepThree;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentStateThree) activity;
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
        View view = inflater.inflate(R.layout.button_panel_arrows, container, false);
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.arrow_stright);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateThree(4);
            }
        });
        ImageButton imageButton2 = (ImageButton)view.findViewById(R.id.arrow_arch);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateThree(5);
            }
        });
        ImageButton imageButton3 = (ImageButton)view.findViewById(R.id.arrow_arch_2);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateThree(6);
            }
        });
        Button button = (Button)view.findViewById(R.id.cancel_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateThreeCancel();
            }
        });
        Button button2 = (Button)view.findViewById(R.id.save_3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateThreeSave();
            }
        });
        Button button3 = (Button)view.findViewById(R.id.go_in_start_2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentStateThreeCancelAll();
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

