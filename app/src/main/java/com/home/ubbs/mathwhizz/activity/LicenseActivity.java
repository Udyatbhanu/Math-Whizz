package com.home.ubbs.mathwhizz.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 5/5/16.
 */
public class LicenseActivity extends BaseActivity  {


    TextView blurBehindTextView, materialDrawerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setDisableOptionsMenu(true);

        blurBehindTextView = (TextView)findViewById(R.id.blur_behind_text_view);
        materialDrawerTextView = (TextView)findViewById(R.id.material_spinner_text_view);

        blurBehindTextView.setText(Html.fromHtml(getString(R.string.blur_behind_license)));
        materialDrawerTextView.setText(Html.fromHtml(getString(R.string.material_spinner_license)));


    }


    /**
     *
     * @param item
     * @return
     */
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
