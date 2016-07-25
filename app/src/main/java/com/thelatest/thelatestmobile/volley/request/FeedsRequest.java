package com.thelatest.thelatestmobile.volley.request;

import com.android.volley.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.thelatest.thelatestmobile.adapters.BigCategoryListViewAdapter;
import com.thelatest.thelatestmobile.objects.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pedro on 7/18/2016.
 */
public class FeedsRequest implements Response.Listener<JSONObject>{

    private BigCategoryListViewAdapter mBigCategoryListViewAdapter;
    private ArrayList<News> mNews;
    private SwipyRefreshLayout mSwipyRefresh;
    private ImageLoader imageLoader;

    public FeedsRequest(BigCategoryListViewAdapter bclv, ArrayList<News> n, SwipyRefreshLayout swipy){
        this.mBigCategoryListViewAdapter = bclv;
        this.mSwipyRefresh = swipy;
        this.mNews = n;

    }
    @Override
    public void onResponse(JSONObject response) {

            JSONArray responseArr = null;
            try {
                responseArr = response.getJSONArray("newsArr");
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < responseArr.length(); i++) {
                News news = null;
                try {
                    JSONObject object = responseArr.getJSONObject(i);
                    String photoURLString = object.getString("photo");
                    String titleString = object.getString("title");
                    String publisherString = object.getString("publisher");
                    String dateString = object.getJSONObject("date").getString("date");
                    String contentString = object.getString("contents");
                    String urlString = object.getString("url");
                    String idString = object.getString("id");

                    news = new News(photoURLString, titleString, publisherString, dateString, contentString, urlString,idString);
                } catch (Exception e) {
                    news = new News("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown","Unknown");
                }
                mNews.add(news);
            }
        mBigCategoryListViewAdapter.notifyDataSetChanged();
        mSwipyRefresh.setRefreshing(false);

    }
}
