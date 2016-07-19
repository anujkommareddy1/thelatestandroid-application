package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.MainActivity;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;
import com.thelatest.thelatestmobile.volley.request.NewsFeedRequest;

import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Jesse on 10/21/15.
 */
public class SmallNewsPagerAdapter extends PagerAdapter {

    private Context context;
    private String bigCategory;

    public SmallNewsPagerAdapter(Context context, String bigCategory){
        this.context = context;
        this.bigCategory = bigCategory;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        final View mainView = View.inflate(context, R.layout.viewpageritem_smallnews, null);
        String[] smallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);

        TextView smallNewsCategoryTitleTextView = (TextView)mainView.findViewById(R.id.category_title_textview);
        String smallCategory = smallCategories[position];
        smallNewsCategoryTitleTextView.setText(smallCategory);

        SmallNewsListViewAdapter smallNewsListViewAdapter = new SmallNewsListViewAdapter(context, R.layout.listviewitem_smallnews, new ArrayList<News>());

        SwipyRefreshLayout mSwipyRefreshLayout = (SwipyRefreshLayout)mainView.findViewById(R.id.swipy_refresh_layout);
        CustomOnRefreshListener customOnRefreshListener = new CustomOnRefreshListener(smallCategory, smallNewsListViewAdapter, mSwipyRefreshLayout);
        mSwipyRefreshLayout.setOnRefreshListener(customOnRefreshListener);

        mSwipyRefreshLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(context instanceof MainActivity){
                    ((MainActivity)context).downBottomBar();
                }

                return false;
            }
        });

        ListView listView = (ListView)mainView.findViewById(R.id.listview);
        listView.setAdapter(smallNewsListViewAdapter);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(context instanceof MainActivity){
                    ((MainActivity)context).downBottomBar();
                }
                return false;
            }
        });

        collection.addView(mainView);

        customOnRefreshListener.onRefresh(SwipyRefreshLayoutDirection.BOTTOM);

        return mainView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view){
        collection.removeView((View)view);
    }

    @Override
    public int getCount(){
        String[] smallCategoiesForTheBigCategory = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);
        return smallCategoiesForTheBigCategory.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }

    public class CustomOnRefreshListener implements SwipyRefreshLayout.OnRefreshListener {

        private int currentStartIndex;
        private String smallCategoryString;
        private SmallNewsListViewAdapter smallNewsListViewAdapter;
        private SwipyRefreshLayout mSwipyRefreshLayout;

        public CustomOnRefreshListener(String smallCategoryString, SmallNewsListViewAdapter smallNewsListViewAdapter, SwipyRefreshLayout mSwipyRefreshLayout){
            this.currentStartIndex = 0;
            this.smallCategoryString = smallCategoryString;
            this.smallNewsListViewAdapter = smallNewsListViewAdapter;
            this.mSwipyRefreshLayout = mSwipyRefreshLayout;
        }

        @Override
        public void onRefresh(SwipyRefreshLayoutDirection direction) {
            if (direction == SwipyRefreshLayoutDirection.TOP) {
                smallNewsListViewAdapter.clear();
                this.currentStartIndex = 0;
                onRefresh(SwipyRefreshLayoutDirection.BOTTOM);
            }
            else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
                JSONObject params = new JSONObject();

                try {
                    params.put("slug", NewsCategoryConstants.getCategorySlug(smallCategoryString));
                    params.put("startIndex", currentStartIndex);
                    params.put("endIndex", currentStartIndex + 1);
                    currentStartIndex += 2;
                } catch (Exception e) {
                    Log.e(getClass().toString(), "Error setting params for communication with server");
                    e.printStackTrace();
                }

                Volley volley = Volley.getVolley(context);
                volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL + VolleyConstants.NEWS_FETCH_ROUTE, params, new NewsFeedRequest(smallNewsListViewAdapter, mSwipyRefreshLayout), new CustomErrorListener("ERROR"));
            }
//            mSwipyRefreshLayout.setRefreshing(false);
        }
    }
}
