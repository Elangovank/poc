package com.poc.app.fragmentmanager;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.poc.app.utils.APPNetworkUtil;

public abstract class BaseFragment extends Fragment {

    APPNetworkUtil aAPPNetworkUtil = new APPNetworkUtil();

    public abstract void onBackPressed();

    public abstract boolean onResumeFragment();

    public boolean checkInternet() {
        return aAPPNetworkUtil.isInternetOn(getActivity());
    }

    public String getETValue(EditText aEditText) {
        if (aEditText != null)
            return aEditText.getText().toString().trim();
        return "";
    }

    public String getTXTValue(TextView aTextText) {
        if (aTextText != null)
            return aTextText.getText().toString().trim();
        return "";
    }

}
