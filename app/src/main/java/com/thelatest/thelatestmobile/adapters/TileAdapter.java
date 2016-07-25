package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.MainActivity;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pedro on 6/9/2016.
 */
public class TileAdapter extends BaseAdapter {

    private Context context;
    private String[] StringBigCat;
    private News news;
    private ImageLoader imageLoader;

    public TileAdapter(Context context, int resource, String[] stringBigCat) {
        this.StringBigCat = stringBigCat;
        this.context = context;
        this.imageLoader = ImageLoader.getInstance();
        if (!this.imageLoader.isInited()) {
            this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        }
    }

    @Override
    public int getCount() {
        return StringBigCat.length;
    }

    @Override
    public Object getItem(int position) {
        return StringBigCat[position];
    }

    @Override
    public long getItemId(int position) {
        return NewsCategoryConstants.getCategoryID(StringBigCat.toString());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.listviewitem_smallnews_tile,null);




        final TextView tvBigCategoryTitle = (TextView) layout.findViewById(R.id.news_big_category_title);
        final TextView tv = (TextView)  layout.findViewById(R.id.news_title_textview);
      // final TextView tv2 = (TextView) layout.findViewById(R.id.news_date_textview);
        final ImageView newsImageView = (ImageView) layout.findViewById(R.id.news_imageview);
        final RelativeLayout spinner = (RelativeLayout)layout.findViewById(R.id.spinner);
        newsImageView.setTag(position);

        tvBigCategoryTitle.setText(StringBigCat[position].toString());




        JSONObject params = new JSONObject();
        String bigCatSlug = NewsCategoryConstants.getSmallCategoriesForBigCategory(StringBigCat[position].toString())[0];
        if(StringBigCat[position].compareTo("extra")==0){ //Extra has a problem
            bigCatSlug = NewsCategoryConstants.getSmallCategoriesForBigCategory(StringBigCat[position].toString())[1];
        }

        try{
            params.put("slug",bigCatSlug);
            params.put("startIndex", 0);
            params.put("endIndex", 0);
        }catch (Exception e) {
            Log.e(getClass().toString(), "Error setting params for communication with server");
            e.printStackTrace();
        }

        Volley volley = Volley.getVolley(context);
        volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL + VolleyConstants.NEWS_FETCH_ROUTE,
                params, new Response.Listener<JSONObject>() {
                    //---------------------Volley------------------//

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray responseArr = null;
                        try {
                            responseArr = response.getJSONArray("newsArr");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < responseArr.length(); i++) {
                            try {
                                JSONObject object = responseArr.getJSONObject(i);
                                String photoURLString = object.getString("photo");
                                String titleString = object.getString("title");
                                String publisherString = object.getString("publisher");
                                String dateString = object.getJSONObject("date").getString("date");
                                String contentString = object.getString("contents");
                                String urlString = object.getString("url");

                             //   news = new News(photoURLString, titleString, publisherString, dateString, contentString,urlString);
                            } catch (Exception e) {
                             //   news = new News("Unknown", "Unknown", "Unknown", "Unknown", "Unknown","Unknown");
                            }

                        }
                        //tv2.setText(news.getNewsDate());
                            tv.setText(news.getNewsHead());

                        //---------------------Image Loading------------------//
                            try {
                                URL url = new URL(news.getNewsPic());
                               imageLoader.loadImage(news.getNewsPic(), new ImageLoadingListener() {
                                 @Override
                                 public void onLoadingStarted(String imageUri, View view) {

                                 }

                                 @Override
                                 public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                                 }

                                 @Override
                                 public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                        newsImageView.setImageBitmap(loadedImage);
                                        spinner.setVisibility(View.GONE);
                                 }

                                 @Override
                                 public void onLoadingCancelled(String imageUri, View view) {


                                 }
                             });
                            }catch(Exception e){
                               newsImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nopic));
                                spinner.setVisibility(View.GONE);

                            }

                    }
                }, new CustomErrorListener("ERROR"));


        layout.setOnClickListener(new View.OnClickListener() {
                Bundle arguments = new Bundle();

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("Position", StringBigCat[position]);

                context.startActivity(intent);
            }
        });
        return layout;
    }



}
