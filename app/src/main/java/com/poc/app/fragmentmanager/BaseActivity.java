package com.poc.app.fragmentmanager;

import android.support.v7.app.AppCompatActivity;

import com.poc.app.utils.APPNetworkUtil;

public class BaseActivity extends AppCompatActivity {

    APPNetworkUtil aAPPNetworkUtil = new APPNetworkUtil();

    public boolean checkInternet() {
        return aAPPNetworkUtil.isInternetOn(this);
    }

}


