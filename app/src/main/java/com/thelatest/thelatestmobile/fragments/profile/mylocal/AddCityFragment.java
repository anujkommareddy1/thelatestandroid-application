package com.thelatest.thelatestmobile.fragments.profile.mylocal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thelatest.thelatestmobile.R;

/**
 * Created by Jesse on 10/20/15.
 */
public class AddCityFragment extends Fragment {

    public AddCityFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_profile_mylocal_addcity, container, false);
        return rootView;
    }
}
