package com.home.ubbs.mathwhizz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 4/8/16.
 */
public class TestCompleteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_complete, container, false);
        RelativeLayout parentLayout = (RelativeLayout)view.findViewById(R.id.parentLayout);
        parentLayout.getBackground().setAlpha(150);

//        AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
//        alpha.setDuration(0); // Make animation instant
//        alpha.setFillAfter(true); // Tell it to persist after the animation ends
//        // And then on your layout
//        parentLayout.startAnimation(alpha);
        return view;
    }
}
