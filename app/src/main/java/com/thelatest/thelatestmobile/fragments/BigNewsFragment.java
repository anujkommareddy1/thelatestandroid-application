package com.thelatest.thelatestmobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.BigNewsPagerAdapter;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;

/**
 * Created by Jesse on 10/21/15.
 */
public class BigNewsFragment extends android.support.v4.app.Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viewpager_news, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_newscategory);

        Bundle bundle = getArguments();
        String bigCategory = bundle.getString(KeyConstants.BIG_CATEGORY);

        int initialIndex = NewsCategoryConstants.getCategoryID(bigCategory);

        BigNewsPagerAdapter adapter = new BigNewsPagerAdapter(rootView.getContext(), bigCategory);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initialIndex);

        return rootView;
    }
}