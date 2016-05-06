package com.home.ubbs.mathwhizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.widget.Chronometer;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.data.MathFunction;
import com.home.ubbs.mathwhizz.fragment.PlayFragment;
import com.home.ubbs.mathwhizz.util.MathWhizzAppSession;
import com.home.ubbs.mathwhizz.util.MathWhizzConstants;
import com.home.ubbs.mathwhizz.util.MathWhizzUtil;
import com.home.ubbs.mathwhizz.view.ChoicesAdapter;

/**
 * Created by udyatbhanu-mac on 4/3/16.
 */
public class PlayActivity extends BaseActivity implements ChoicesAdapter.nextPageListener {

    TextView pageNumber;
    Chronometer chronometer;
    private long elapsedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        setDisableOptionsMenu(true);
        pageNumber = (TextView) findViewById(R.id.page);
        chronometer = (Chronometer) findViewById(R.id.timer);
        chronometer.start();
        updatePageNumber();
        if (savedInstanceState == null) {
            Fragment playFragment = PlayFragment.create(MathWhizzUtil.generateMathFunction());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container, playFragment, MathWhizzConstants.PLAY_FRAGMENT_TAG).commit();
        } else {
            PlayFragment playFragment = (PlayFragment) getSupportFragmentManager().findFragmentByTag(MathWhizzConstants.PLAY_FRAGMENT_TAG);
            playFragment.setMathFunction((MathFunction) savedInstanceState.getParcelable(MathWhizzConstants.MATH_FUNCTION_KEY));
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, playFragment, MathWhizzConstants.PLAY_FRAGMENT_TAG).commit();
        }

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                                                     public void onChronometerTick(Chronometer arg0) {
//                                                    String HH =((elapsedTime / 3600) < 10 ? "0" : "") + (elapsedTime / 3600);
                                                         String MM = ((elapsedTime / 60) < 10 ? "0" : "") + (elapsedTime / 60);
                                                         String SS = ((elapsedTime % 60) < 10 ? "0" : "") + (elapsedTime % 60);
                                                         String currentTime = MM + ":" + SS;
                                                         elapsedTime = (SystemClock.elapsedRealtime() - arg0.getBase()) / 1000;
                                                         chronometer.setText(currentTime);

                                                     }
                                                 }
        );


    }

    private void updatePageNumber() {
        int page = MathWhizzAppSession.SET_COUNTER;
        int pageCount = MathWhizzAppSession.SET;
        page = pageCount - (page - 1);
        String text = String.format(getString(R.string.page_number_message), page, MathWhizzAppSession.SET);

        pageNumber.setText(text);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PlayFragment playFragment = (PlayFragment) getSupportFragmentManager().findFragmentByTag(MathWhizzConstants.PLAY_FRAGMENT_TAG);
        bundle.putParcelable(MathWhizzConstants.MATH_FUNCTION_KEY, playFragment.getMathFunction());

    }

    @Override
    public void onNextQuestion() {
        updatePageNumber();
        Fragment playFragment = PlayFragment.create(MathWhizzUtil.generateMathFunction());
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.frame_container, playFragment, MathWhizzConstants.PLAY_FRAGMENT_TAG).commit();
    }

    @Override
    public void onFinishedSession() {
        chronometer.stop();
        if (MathWhizzAppSession.isSessionActive()) {
            MathWhizzAppSession.endSession(true);
        }

        Intent activityChangeIntent = new Intent(PlayActivity.this, ResultsActivity.class);
        startActivity(activityChangeIntent);

        finish();
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.animator.slide_in_right,
                R.animator.slide_out_right);
    }


}
