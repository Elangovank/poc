package com.poc.app.fragmentmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.poc.app.R;



public class APPFragmentManager {

    /**
     * Last fragment tag
     */
    private static String myLastTag = "";
    private FragmentActivity myContext;

    /**
     * Constructor to Initiate fragment manager
     *
     * @param aContext FragmentActivity
     */
    public APPFragmentManager(FragmentActivity aContext) {
        myContext = aContext;
    }


    /**
     * Update the Current Fragment by passing the below parameters
     *
     * @param aFragment Fragment
     * @param tag       String
     * @param aBundle   Bundle
     */
    public void updateContent(Fragment aFragment, String tag, Bundle aBundle) {
        try {

            Log.e("TAG Screen name", tag);


            // Initialise Fragment Manager
            final FragmentManager aFragmentManager = myContext.getSupportFragmentManager();

            // Initialise Fragment Transaction
            final FragmentTransaction aTransaction = aFragmentManager.beginTransaction();


            if (aBundle != null) {
                aFragment.setArguments(aBundle);
            }

            // Add the selected fragment
            aTransaction.add(R.id.frame_container, aFragment, tag);

            // add the tag to the backstack
            aTransaction.addToBackStack(tag);


            //Repalce New Fragment with old
//            aTransaction.replace(R.id.frame_container,aFragment,tag);
//            aTransaction.addToBackStack(null);


            // Commit the Fragment transaction
            aTransaction.commit();

            // aTransaction.commitAllowingStateLoss();

            myLastTag = tag;

            Log.i("LastTag", myLastTag);

        } catch (StackOverflowError | Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear All Fragments
     */
    public void clearAllFragments() {

        try {
            FragmentManager aFragmentManager = myContext.getSupportFragmentManager();

            for (int i = 0; i < aFragmentManager.getBackStackEntryCount(); ++i) {
                aFragmentManager.popBackStack();
            }
        } catch (StackOverflowError | Exception e) {
            e.printStackTrace();
        }

    }

    public void oneStepBack() {
        FragmentTransaction fts = myContext.getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 2) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        }
    }


    public int getBackstackCount() {

        FragmentManager aFragmentManager = myContext.getSupportFragmentManager();

        return aFragmentManager.getBackStackEntryCount();
    }


    public void removeFragment(int aCount) {

        FragmentManager aFragmentManager = myContext
                .getSupportFragmentManager();

        for (int i = 0; i < aCount; i++) {


            aFragmentManager.popBackStack();
        }

    }


    public void onBackPress() {

        try {

            final InputMethodManager inputMethodManager = (InputMethodManager) myContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                if (myContext.getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(myContext.getCurrentFocus().getWindowToken(), 0);
                }
            }


            FragmentManager aFragmentManager = myContext.getSupportFragmentManager();
            if (aFragmentManager.getBackStackEntryCount() > 1) {
                aFragmentManager.popBackStack();
                aFragmentManager.executePendingTransactions();

                Log.d("TAG", "CURRENT FRAGMENT BACK STACK CLASS " + aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName());


                //TODO Stop video
                String aFragmentTag = aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName();
                Fragment aFragment = myContext.getSupportFragmentManager().findFragmentByTag(aFragmentTag);
                aFragment.onResume();

                if (aFragment instanceof BaseFragment) {
                    ((BaseFragment) aFragment).onResumeFragment();
                }


                String aFragmentName = aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName();
            }else{
               /* ((SKYHomeActivity) myContext).defaultScreen();
                ((SKYHomeActivity) myContext).setHomeItem();*/
            }
        } catch (StackOverflowError | Exception e) {
            e.printStackTrace();
        }
    }


    public void onBackPress1() {

        try {
            FragmentManager aFragmentManager = myContext.getSupportFragmentManager();
            if (aFragmentManager.getBackStackEntryCount() > 0) {
                aFragmentManager.popBackStack();
                aFragmentManager.executePendingTransactions();

                Log.d("TAG", "CURRENT FRAGMENT BACK STACK CLASS " + aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName());


                //TODO Stop video
                String aFragmentTag = aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName();
                Fragment aFragment = myContext.getSupportFragmentManager().findFragmentByTag(aFragmentTag);
                aFragment.onResume();

                if (aFragment instanceof BaseFragment) {
                    ((BaseFragment) aFragment).onResumeFragment();

                }

                String aFragmentName = aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName();
            }
        } catch (StackOverflowError | Exception e) {
            e.printStackTrace();
        }
    }


}
