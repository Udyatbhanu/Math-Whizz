package com.home.ubbs.mathwhizz.activity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.home.ubbs.lib.theme.BaseThemeActivity;
import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 5/2/16.
 */
public class BaseActivity extends BaseThemeActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.theme_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.red:

                setTheme(R.style.AppThemeRed, this);
                return true;
            case R.id.pink:
                setTheme(R.style.AppThemePink, this);

                return true;
            case R.id.purple:

                setTheme(R.style.AppThemePurple, this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
