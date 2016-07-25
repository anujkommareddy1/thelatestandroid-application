package com.thelatest.thelatestmobile.fragments;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.BigCategoryPagerAdapter;
import com.thelatest.thelatestmobile.adapters.BigCategoryPagerAdapterBackUp;
import com.thelatest.thelatestmobile.adapters.SmallNewsPagerAdapter;

import org.w3c.dom.Text;


public class BigCategoryFragments extends Fragment {
    private View layout;
    public BigCategoryFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

                // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_big_category_fragments, container, false);
        ViewPager mViewPager = (ViewPager) layout.findViewById(R.id.view_pager_bigcat);

       BigCategoryPagerAdapter adapter = new BigCategoryPagerAdapter(layout.getContext());
        String[] testao = NewsCategoryConstants.getBigCategories();
       // BigCategoryPagerAdapterBackUp adapter = new BigCategoryPagerAdapterBackUp(layout.getContext());

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            String[] aux = new String[]{"dot1","dot2","dot3","dot4","dot5"};
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageView iv;
                for(int i=0;i<NewsCategoryConstants.getBigCategories().length;i++) {
                    iv = (ImageView) layout.findViewWithTag(aux[i]);
                    if(i==position){ iv.setImageResource(R.drawable.dot_selected);
                       }
                    else iv.setImageResource(R.drawable.dot_unselected);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        return layout;
    }



}
