package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;

/**
 * Created by Jesse on 10/21/15.
 */
public class NewsPanelPagerAdapter extends PagerAdapter {

    private Context context;
    private String bigCategory;
    private String smallCategory;

    public NewsPanelPagerAdapter(Context context, String bigCategory, String smallCategory){
        this.context = context;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        View mainView = null;

        // BIG CATEGORY CHOICE
        if(smallCategory == null){
            mainView = View.inflate(context, R.layout.viewpageritem_bignews, null);

            TextView bigNewsCategoryTextView = (TextView)mainView.findViewById(R.id.textview_bignews_title);
            String thisBigCategory = NewsCategoryConstants.getBigCategories()[position];
            bigNewsCategoryTextView.setText(thisBigCategory);

            LinearLayout smallNewsLinearLayout = (LinearLayout)mainView.findViewById(R.id.linearlayout_smallnews);

            for(String smallCategoryForTheChoosenBigCategory : NewsCategoryConstants.getSmallCategoriesForBigCategory(thisBigCategory)){
                View smallView = View.inflate(context, R.layout.listviewitem_bignewspage_smallnews, null);
                TextView smallNewsCategoryTextView = (TextView)smallView.findViewById(R.id.textview_smallnewscategory);
                TextView smallNewsTitleTextView = (TextView)smallView.findViewById(R.id.textview_smallnewstitle);
                TextView smallNewsInfoTextView = (TextView)smallView.findViewById(R.id.textview_smallnewsinfo);

                smallNewsCategoryTextView.setText(smallCategoryForTheChoosenBigCategory);
                smallNewsTitleTextView.setText("supermodel Gigi Hadid slams body-shaming trolls");
                smallNewsInfoTextView.setText("2 hours ago");

                smallNewsLinearLayout.addView(smallView);
            }
        }

        // SMALL CATEGORY CHOICE
        else{
            mainView = View.inflate(context, R.layout.viewpageritem_smallnews, null);

            TextView smallNewsCategoryTitleTextView = (TextView)mainView.findViewById(R.id.textview_smallnews_title);
            String[] thisSmallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);
            smallNewsCategoryTitleTextView.setText(thisSmallCategories[position]);

            LinearLayout newsLinearLayout = (LinearLayout)mainView.findViewById(R.id.linearlayout_news);

            for(int i=0 ; i<10 ; i++){
                View smallView = View.inflate(context, R.layout.listviewitem_smallnews_news, null);

                TextView newsTitleTextView = (TextView)smallView.findViewById(R.id.textview_title);
                TextView newsInfoTextView = (TextView)smallView.findViewById(R.id.textview_info);

                newsTitleTextView.setText("supermodel Gigi Hadid slams body-shaming trolls");
                newsInfoTextView.setText("2 hours ago");

                newsLinearLayout.addView(smallView);
            }
        }

        collection.addView(mainView);

        return mainView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view){
        collection.removeView((View)view);
    }

    @Override
    public int getCount(){
        int totalCount = 0;

        // BIG CATEGORY CHOICE
        if(smallCategory == null){
            String[] bigCategories = NewsCategoryConstants.getBigCategories();
            totalCount = bigCategories.length;
        }

        // SMALL CATEGORY CHOICE
        else{
            String[] smallCategoiesForTheBigCategory = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);
            totalCount = smallCategoiesForTheBigCategory.length;
        }

        return totalCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }
}
