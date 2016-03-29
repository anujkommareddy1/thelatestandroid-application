package com.thelatest.thelatestmobile.volley.request;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Response;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.thelatest.thelatestmobile.objects.News;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Logan on 11/24/15.
 */
public class NewsFeedRequest implements Response.Listener<JSONObject> {

    private ArrayAdapter<News> newsListViewAdapter;
    private SwipyRefreshLayout mSwipyRefreshLayout;

    public NewsFeedRequest(ArrayAdapter<News> newsListViewAdapter, SwipyRefreshLayout mSwipyRefreshLayout){
        this.newsListViewAdapter = newsListViewAdapter;
        this.mSwipyRefreshLayout = mSwipyRefreshLayout;
    }

    @Override
    public void onResponse(JSONObject response){

        JSONArray responseArr = null;
        try {
            responseArr = response.getJSONArray("newsArr");
        }catch(Exception e) {
            e.printStackTrace();
        }

        for(int i=0 ; i<responseArr.length() ; i++) {
            News news = null;

            try {
                JSONObject object = responseArr.getJSONObject(i);
                String photoURLString = object.getString("photo");
                String titleString = object.getString("title");
                String publisherString = object.getString("publisher");
                String dateString = object.getJSONObject("date").getString("date");
                String contentString = object.getString("contents");
                news = new News(photoURLString, titleString, publisherString, dateString, contentString);
            }catch(Exception e) {
                news = new News("Unknown", "Unknown", "Unknown", "Unknown", "Unknown");
            }

            newsListViewAdapter.add(news);
        }
        newsListViewAdapter.notifyDataSetChanged();
        mSwipyRefreshLayout.setRefreshing(false);
    }
}
