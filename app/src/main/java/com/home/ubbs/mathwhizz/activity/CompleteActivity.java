package com.home.ubbs.mathwhizz.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.faradaj.blurbehind.BlurBehind;
import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 4/9/16.
 */
public class CompleteActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);




        RelativeLayout parentLayout  =  (RelativeLayout) findViewById(R.id.parentLayout);

        parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(savedInstanceState==null){
            BlurBehind.getInstance()
                    .withAlpha(200)
                    .withFilterColor(Color.parseColor("#C71585"))
                    .setBackground(this);
        }

    }

    /**
     *
     */
    @Override
    public void onBackPressed(){
        finish();

    }

}
