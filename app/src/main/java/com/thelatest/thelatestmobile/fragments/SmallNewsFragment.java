package com.thelatest.thelatestmobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.adapters.SmallNewsPagerAdapter;

/**
 * Created by Jesse on 10/21/15.
 */
public class SmallNewsFragment extends Fragment{

    public SmallNewsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viewpager_news, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_newscategory);

        Bundle bundle = getArguments();
        String bigCategory = bundle.getString(KeyConstants.BIG_CATEGORY);
        String smallCategory = bundle.getString(KeyConstants.SMALL_CATEGORY);

        String[] smallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);

        int initialIndex = -1;
        for(int i=0 ; i<smallCategories.length ; i++){
            if(smallCategories[i] == smallCategory){
                initialIndex = i;
                break;
            }
        }

        SmallNewsPagerAdapter adapter = new SmallNewsPagerAdapter(rootView.getContext(), bigCategory);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initialIndex);

        return rootView;
    }
}
