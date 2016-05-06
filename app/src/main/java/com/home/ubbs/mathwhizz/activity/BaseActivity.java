package com.home.ubbs.mathwhizz.activity;

import android.support.annotation.StyleRes;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.home.ubbs.lib.theme.BaseThemeActivity;
import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 5/2/16.
 */
public class BaseActivity extends BaseThemeActivity {



    public static @StyleRes int  SELECTED_THEME = -1;
    protected boolean disableOptionsMenu = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!disableOptionsMenu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.theme_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {
            case R.id.red:

                setCustomTheme(R.style.AppThemeRed);
                return true;
            case R.id.pink:
                setTheme(R.style.AppThemePink);

                return true;
            case R.id.purple:

                setTheme(R.style.AppThemePurple);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isDisableOptionsMenu() {
        return disableOptionsMenu;
    }

    public void setDisableOptionsMenu(boolean disableOptionsMenu) {
        this.disableOptionsMenu = disableOptionsMenu;
    }

    /**
     *
     */
    public void handleFinish(){
        onBackPressed();

    }





    /**
     *
     */
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.animator.slide_in_right,
                R.animator.slide_out_right);

    }
}
