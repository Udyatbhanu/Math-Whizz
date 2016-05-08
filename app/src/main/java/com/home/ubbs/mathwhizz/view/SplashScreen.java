package com.home.ubbs.mathwhizz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 5/7/16.
 */
public class SplashScreen {


    Activity activity;
    LayoutInflater inflator;
    View view;
    RelativeLayout splashParent;
    Class<?> targetActivity;
    TextView centerTextView, footerTextView;
    private  int SPLASH_DISPLAY_LENGTH = 3000;

    public SplashScreen (Activity activity){
        this.activity = activity;
        this.inflator = LayoutInflater.from(activity);
        this.view = inflator.inflate(R.layout.layout_splash, null);
        this.splashParent = (RelativeLayout) view.findViewById(R.id.splash_parent);

    }


    public SplashScreen withFullScreen(){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return this;
    }

    public SplashScreen withTargetActivity(Class clazz){
        targetActivity = clazz;
        return this;
    }

    public SplashScreen withSplashDisplayTime(int length){
        SPLASH_DISPLAY_LENGTH = length;
        return this;
    }


    public SplashScreen withFooterText(String text){
        footerTextView = (TextView) view.findViewById(R.id.footer_text_view);
        footerTextView.setText(text);
        return this;

    }


    public SplashScreen withCenterAnimatedText(){

        centerTextView = (TextView) view.findViewById(R.id.main_text_view);
        Animation animation=new TranslateAnimation(0,0,-480,0);
        animation.setDuration(3000);
        centerTextView.startAnimation(animation);
        return this;
    }


    public SplashScreen withFooterAnimatedText(){


        Animation animation=new TranslateAnimation(0,0,480,0);
        animation.setDuration(3000);
        footerTextView.startAnimation(animation);
        return this;
    }

    public View build(){
        setUpHandler();
        return view;
    }
    private void setUpHandler(){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(activity, targetActivity);

                    activity.startActivity(i);
                    // close splash
                    activity.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);

    }
}
