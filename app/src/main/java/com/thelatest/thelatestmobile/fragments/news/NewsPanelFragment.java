package com.thelatest.thelatestmobile.fragments.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.NewsPanelPagerAdapter;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;

/**
 * Created by Jesse on 10/21/15.
 */
public class NewsPanelFragment extends Fragment{

    public NewsPanelFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viewpager_newspanel, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_newscategory);

        Bundle bundle = getArguments();
        String bigCategory = bundle.getString(KeyConstants.BIG_CATEGORY);
        String smallCategory = bundle.getString(KeyConstants.SMALL_CATEGORY);

        int initialIndex = (smallCategory == null)
                ? NewsCategoryConstants.getCategoryIndex(bigCategory)
                : NewsCategoryConstants.getCategoryIndex(smallCategory);

        NewsPanelPagerAdapter adapter = new NewsPanelPagerAdapter(rootView.getContext(), bigCategory, smallCategory);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initialIndex);

        return rootView;
    }
}
