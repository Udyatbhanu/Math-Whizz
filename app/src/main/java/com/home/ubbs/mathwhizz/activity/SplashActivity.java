package com.home.ubbs.mathwhizz.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.home.ubbs.mathwhizz.view.SplashScreen;

/**
 * Created by udyatbhanu-mac on 4/3/16.
 */
public class SplashActivity extends FragmentActivity {
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen config = new SplashScreen(SplashActivity.this).
                withFullScreen().
                withTargetActivity(SettingsActivity.class).
                withFooterText("Copyright 2016").
                withCenterAnimatedText().
                withFooterAnimatedText().
                withSplashDisplayTime(SPLASH_DISPLAY_LENGTH);


        setContentView(config.build());



    }

}
