package com.home.ubbs.mathwhizz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.data.MathFunction;
import com.home.ubbs.mathwhizz.view.ChoicesAdapter;

/**
 * Created by udyatbhanu-mac on 4/3/16.
 */
public class PlayFragment extends Fragment {

    private GridView gridview;


    private MathFunction mathFunction;

    public PlayFragment() {

    }


    public static Fragment create(MathFunction mathFunction) {
        PlayFragment fragment = new PlayFragment();
        fragment.setMathFunction(mathFunction);


        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        RelativeLayout resultsLayout = (RelativeLayout) view.findViewById(R.id.resultPanel);
        TextView textView = (TextView) view.findViewById(R.id.expression);
        textView.setText(mathFunction.toString());

        resultsLayout.setTranslationX(-3000f);

        gridview = (GridView) view.findViewById(R.id.gridview);


        gridview.setAdapter(new ChoicesAdapter(getActivity(), getMathFunction()));


        return view;
    }




    public MathFunction getMathFunction() {
        return mathFunction;
    }

    public void setMathFunction(MathFunction mathFunction) {
        this.mathFunction = mathFunction;
    }
}
