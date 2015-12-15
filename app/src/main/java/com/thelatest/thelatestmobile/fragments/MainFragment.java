package com.thelatest.thelatestmobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thelatest.thelatestmobile.R;

/**
 * Created by Jesse on 10/12/15.
 */
public class MainFragment extends Fragment {

    private View rootView;

    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        return rootView;
    }
}
