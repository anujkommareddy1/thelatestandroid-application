package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
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
public class BigNewsPagerAdapter extends PagerAdapter {

    private Context context;
    private String bigCategory;
    private String smallCategory;

    public BigNewsPagerAdapter(Context context, String bigCategory){
        this.context = context;
        this.bigCategory = bigCategory;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        View mainView = View.inflate(context, R.layout.viewpageritem_smallnews, null);

        TextView smallNewsCategoryTitleTextView = (TextView)mainView.findViewById(R.id.category_title_textview);
        String[] thisSmallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);
        smallNewsCategoryTitleTextView.setText(thisSmallCategories[position]);

        final SmallNewsListViewAdapter smallNewsListViewAdapter = new SmallNewsListViewAdapter(context, R.layout.listviewitem_smallnews, new ArrayList<News>());

        final SwipyRefreshLayout mSwipyRefreshLayout = (SwipyRefreshLayout)mainView.findViewById(R.id.swipy_refresh_layout);
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(direction == SwipyRefreshLayoutDirection.TOP){

                }
                else if(direction == SwipyRefreshLayoutDirection.BOTTOM){
                    JSONObject params = new JSONObject();

                    try {
                        params.put("slug", NewsCategoryConstants.getCategorySlug(smallCategory));
                        params.put("startIndex", 0);
                        params.put("endIndex", 2);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    Volley volley = Volley.getVolley(context);
                    volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL + VolleyConstants.NEWS_FETCH_ROUTE, params, new NewsFeedRequest(smallNewsListViewAdapter, mSwipyRefreshLayout), new CustomErrorListener("ERROR"));
                }
            }
        });

        ListView listView = (ListView)mainView.findViewById(R.id.listview);
        listView.setAdapter( smallNewsListViewAdapter );

        collection.addView(mainView);

        return mainView;
    }

    public void getSmallNewsFromServer(SmallNewsListViewAdapter smallNewsListViewAdapter){
        JSONObject params = new JSONObject();

        try {
            params.put("slug", NewsCategoryConstants.getCategorySlug(smallCategory));
            params.put("startIndex", 0);
            params.put("endIndex", 2);
        }catch(Exception e){
            e.printStackTrace();
        }

        Volley volley = Volley.getVolley(context);
        volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL + VolleyConstants.NEWS_FETCH_ROUTE, params, new NewsFeedRequest(smallNewsListViewAdapter, null), new CustomErrorListener("ERROR"));
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
}
