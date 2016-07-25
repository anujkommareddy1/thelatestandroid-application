package com.thelatest.thelatestmobile.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.adapters.SmallNewsPagerAdapter;

/**
 * Created by Jesse on 10/21/15.
 */
public class SmallNewsFragment extends android.support.v4.app.Fragment{

    public SmallNewsFragment(){}




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.viewpager_news, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_newscategory);

        Bundle bundle = getArguments();
        String bigCategory = bundle.getString(KeyConstants.BIG_CATEGORY);
        String smallCategory = bundle.getString(KeyConstants.SMALL_CATEGORY);

        LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.dots_layout);





        final String[] smallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);
        ImageView dots[] = new ImageView[smallCategories.length];

        int initialIndex = -1;
        for(int i=0 ; i<smallCategories.length ; i++){
            if(smallCategories[i] == smallCategory){
                initialIndex = i;
                break;
            }
        }


        for(int i=0;i<smallCategories.length;i++){
            dots[i] = new ImageView(rootView.getContext());
            dots[i].setImageResource(R.drawable.dot_unselected);
            dots[i].setVisibility(View.VISIBLE);
            dots[i].setPadding(4,4,4,4);
            dots[i].setTag("D"+i);
            mLinearLayout.addView(dots[i]);
        }

        dots[initialIndex].setImageResource(R.drawable.dot_selected);


        SmallNewsPagerAdapter adapter = new SmallNewsPagerAdapter(rootView.getContext(), bigCategory);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initialIndex);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageView iv;
                for(int i=0;i<smallCategories.length;i++) {
                    iv = (ImageView) rootView.findViewWithTag("D"+i);
                    if(i==position){ iv.setImageResource(R.drawable.dot_selected);
                    }
                    else iv.setImageResource(R.drawable.dot_unselected);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        return rootView;
    }
}
