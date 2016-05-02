package com.home.ubbs.mathwhizz.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.data.Operator;
import com.home.ubbs.mathwhizz.util.MathWhizzAppSession;
import com.home.ubbs.mathwhizz.view.SettingsItemsAdapter;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udyatbhanu-mac on 4/3/16.
 */
public class SettingsActivity extends BaseActivity implements SettingsItemsAdapter.CheckStateCallback {


    private List<Operator> operatorsSelected = new ArrayList<>();



    private int set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FloatingActionButton startButton = (FloatingActionButton) findViewById(R.id.start_button);
        ListView listView = (ListView) findViewById(R.id.listView);


        if (null != listView) {
            listView.setAdapter(new SettingsItemsAdapter(this));

        }

        if (null != startButton) {
            startButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // Perform action on click

                    if (operatorsSelected.isEmpty()) {
                        Snackbar.make(view, getResources().getString(R.string.snack_bar_message), Snackbar.LENGTH_LONG).show();
                    } else {
                        MathWhizzAppSession.startSession(operatorsSelected, set);
                        Intent activityChangeIntent = new Intent(SettingsActivity.this, PlayActivity.class);
                        SettingsActivity.this.startActivity(activityChangeIntent);
                        SettingsActivity.this.overridePendingTransition(R.animator.slide_in_left,
                                R.animator.slide_out_left);
                    }

                }
            });
        }

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        String []sets = getResources().getStringArray(R.array.session_sets);
        spinner.setItems(sets);
        set = Integer.parseInt(sets[0]);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                set = Integer.parseInt(item);

            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
//                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (MathWhizzAppSession.isSessionActive()) {
            MathWhizzAppSession.endSession(true);
        }

    }

    @Override
    public void onSelectStateChanged(Operator operator, boolean isChecked) {
        if (isChecked) {
            operatorsSelected.add(operator);

        } else {
            operatorsSelected.remove(operator);
        }

    }
}
