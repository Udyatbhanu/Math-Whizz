package com.home.ubbs.mathwhizz.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.home.ubbs.lib.theme.ThemeSettings;
import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.view.ThemesRecycleAdapter;

/**
 * Created by udyatbhanu-mac on 5/4/16.
 */
public class ChangeThemeActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button chnageTheme, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);
        mRecyclerView = (RecyclerView) findViewById(R.id.theme_recycle_view);
        chnageTheme = (Button) findViewById(R.id.change_theme_button);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        chnageTheme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTheme(ThemeSettings.style, ChangeThemeActivity.this);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.animator.slide_in_right,
                        R.animator.slide_out_right);

            }
        });



        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ThemesRecycleAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }
}
