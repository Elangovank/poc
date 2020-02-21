package com.poc.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.poc.app.R;
import com.poc.app.constants.PrefConstants;
import com.poc.app.fragmentmanager.APPFragmentManager;
import com.poc.app.fragmentmanager.BaseFragment;
import com.poc.app.helper.AppDialogs;
import com.poc.app.http.Response;
import com.poc.app.http.ResponseListener;
import com.poc.app.webservice.WebService;


/**
 * A simple {@link Fragment} subclass.
 */
public class SKYHomeFragment extends BaseFragment implements ResponseListener, PrefConstants {

    View myEmail;
    public static String TAG = SKYHomeFragment.class.getSimpleName();

    FragmentActivity myContext = null;
    private APPFragmentManager myFragmentManager;

    ImageView myDreamImg, myExploreImg, myDiscoverImg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        init(view);

        return view;
    }

    private void login() {
        if (checkInternet()) {

            AppDialogs.showProgressDialog(myContext);
            //WebService.login(myContext, "");
        } else {
            AppDialogs.OkListener okListener = new AppDialogs.OkListener() {
                @Override
                public void postiveAction() {

                }
            };
            AppDialogs.showSnackbarAction(myContext, myEmail, getString(R.string.no_internet), okListener);
        }
    }

    public void init(View rootview) {
        setHasOptionsMenu(true);
        myContext = getActivity();


        myFragmentManager = new APPFragmentManager(myContext);
        myDreamImg = rootview.findViewById(R.id.img_dream);
        myExploreImg = rootview.findViewById(R.id.img_explore);
        myDiscoverImg = rootview.findViewById(R.id.img_discover);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
       /* SKYHomeActivity activity = (SKYHomeActivity) getActivity();
        activity.exitApp();*/
    }

    @Override
    public boolean onResumeFragment() {
       /* ((SKYHomeActivity) myContext).setHeader(getString(R.string.here_we_go));
        ((SKYHomeActivity) myContext).setMenuIcon(true);
        ((SKYHomeActivity) myContext).mTopToolbar.setVisibility(View.VISIBLE);
        ((SKYHomeActivity) myContext).bottomSheetVisibility(true);
        ((SKYHomeActivity) myContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((SKYHomeActivity) myContext).lockDrawer(false);*/
        return true;
    }


    @Override
    public void onResume() {
      /*  ((SKYHomeActivity) myContext).mTopToolbar.setVisibility(View.VISIBLE);
        ((SKYHomeActivity) myContext).bottomSheetVisibility(true);
        ((SKYHomeActivity) myContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((SKYHomeActivity) myContext).bottomSheetVisibility(true);
        ((SKYHomeActivity) myContext).setHeader(getString(R.string.here_we_go));
        ((SKYHomeActivity) myContext).setMenuIcon(true);
        ((SKYHomeActivity) myContext).showAddPostIcon(false);
        ((SKYHomeActivity) myContext).showInviteIcon(false);
        ((SKYHomeActivity) myContext).setHomeItem();*/

        super.onResume();
    }

    @Override
    public void onResponse(Response response) {
        AppDialogs.hideProgressDialog();
        if (response != null) {
            if (response.requestType == WebService.API.notificationCount.hashCode()) {
                if (response.isSuccess()) {

                } else {
                }

            }
        } else
            AppDialogs.okAction(myContext, getString(R.string.server_unreach), getString(R.string.something));
    }
}
