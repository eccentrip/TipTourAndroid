package mountainq.helloegg.tiptourguide.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.interfaces.NavigationDrawer;

/**
 * Created by dnay2 on 2016-11-17.
 */

public abstract class NavigationDrawerActivity extends SActivity implements NavigationDrawer {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    View mDrawerLeft;
    ListView mMenuList;

    private boolean isDrawerOpen = false;

    long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState, layout);
        initDrawer();
    }

    private void initDrawer(){
        mDrawerLeft = findViewById(R.id.drawerLeft);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        ) {

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                isDrawerOpen = false;
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    public void setToolbarText(String text){
        ((TextView) findViewById(R.id.toolbarText)).setText(text);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if(mDrawerToggle != null) mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }



    @Override
    public void closeDrawer() {
        if(mDrawerLayout != null && mDrawerLeft != null && isDrawerOpen) mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void openDrawer() {
        if(mDrawerLayout != null && mDrawerLeft != null) mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onBackPressed() {
        if(isDrawerOpen){
            closeDrawer();
        } else if(pressedTime+ 2000 < System.currentTimeMillis()) {
            pressedTime = System.currentTimeMillis();
            Toast.makeText(NavigationDrawerActivity.this, "종료키를 한번 더 눌러주세요", Toast.LENGTH_SHORT).show();
        } else super.onBackPressed();
    }





}
