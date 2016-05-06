package com.home.ubbs.mathwhizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.faradaj.blurbehind.BlurBehind;
import com.faradaj.blurbehind.OnBlurCompleteListener;
import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.util.MathWhizzAppSession;
import com.home.ubbs.mathwhizz.view.ResultsRecyclerAdapter;

/**
 * Created by udyatbhanu-mac on 4/8/16.
 */
public class ResultsActivity extends BaseActivity implements ResultsRecyclerAdapter.finishButtonListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setDisableOptionsMenu(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.results_recycler_view);
        mRecyclerView.setHasFixedSize(false);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mAdapter = new ResultsRecyclerAdapter(MathWhizzAppSession.getResults(), this);
        mRecyclerView.setAdapter(mAdapter);


        final View decorView = getWindow().getDecorView();


            decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    finishButton = (Button)mRecyclerView.findViewById(R.id.finishButton);
                    if(null!= finishButton){
                        finishButton.setVisibility(View.INVISIBLE);
                    }
                    BlurBehind.getInstance().execute(ResultsActivity.this, new OnBlurCompleteListener() {
                        @Override
                        public void onBlurComplete() {
                            Intent intent = new Intent(ResultsActivity.this, CompleteActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                            startActivity(intent);

                        }
                    });
                }
            });




    }

    @Override
    public void onResume(){
        super.onResume();
        if(null!= finishButton){
            finishButton.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void finishButtonClicked() {
        finish();
    }
}

