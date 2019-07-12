package com.poc.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.poc.app.fragmentmanager.APPFragmentManager;
import com.poc.app.fragments.SKYHomeFragment;

public class MainActivity extends AppCompatActivity {

    private APPFragmentManager myFragmentManager;
    SpaceNavigationView mBottamNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        myFragmentManager = new APPFragmentManager(this);
        mBottamNav = findViewById(R.id.bottomNav);
        mBottamNav.addSpaceItem(new SpaceItem("", R.drawable.ic_home));
        mBottamNav.addSpaceItem(new SpaceItem("", R.drawable.ic_invoice));
        mBottamNav.addSpaceItem(new SpaceItem("", R.drawable.ic_home));
        mBottamNav.addSpaceItem(new SpaceItem("", R.drawable.ic_home));
        mBottamNav.setCentreButtonIcon(R.drawable.ic_invoice);
        mBottamNav.setCentreButtonColor(getResources().getColor(R.color.white));
      //  mBottamNav.showBadgeAtIndex(0, 2, getResources().getColor(R.color.red));
    }

    public void defaultScreen() {
        myFragmentManager.clearAllFragments();

        myFragmentManager.updateContent(new SKYHomeFragment(), SKYHomeFragment.TAG, null);
    }
}
