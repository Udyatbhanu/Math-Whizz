package com.home.ubbs.mathwhizz.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.view.ThemesRecycleAdapter;

/**
 * Created by udyatbhanu-mac on 5/4/16.
 */
public class ChangeThemeActivity extends BaseActivity  {


//    protected Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button changeTheme, cancelButton;
    private static int cancelState = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        mRecyclerView = (RecyclerView) findViewById(R.id.theme_recycle_view);
        changeTheme = (Button) findViewById(R.id.change_theme_button);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        changeTheme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelState = -2;
                setCustomTheme((BaseActivity.SELECTED_THEME == -1) ? R.style.AppThemeRed : BaseActivity.SELECTED_THEME);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cancelState == -2){
                    cancelState = -1;
                    cancelTheme();
                }else{
                    finish();
                    overridePendingTransition(R.animator.slide_in_right,
                            R.animator.slide_out_right);
                }


            }
        });



        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ThemesRecycleAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                handleFinish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
