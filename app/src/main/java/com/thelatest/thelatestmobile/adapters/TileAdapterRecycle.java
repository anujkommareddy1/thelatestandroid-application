package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 7/2/2016.
 */
public class TileAdapterRecycle extends RecyclerView.Adapter<TileAdapterRecycle.MyViewHolder> {
    private Context context;
    private String[] StringBigCat;
    private News news;
    private ImageLoader imageLoader;
    private static ClickListener mClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv,tv2,tvBigCategoryTitle;
        public ImageView newsImageView,tvBigCatTitle;
        public RelativeLayout spinner;

        public MyViewHolder(View view){
                super(view);
             tvBigCategoryTitle = (TextView) view.findViewById(R.id.news_big_category_title);
             tv = (TextView)  view.findViewById(R.id.news_title_textview);
             //tv2 = (TextView) view.findViewById(R.id.news_date_textview);
            // tvBigCatTitle = (ImageView) view.findViewById(R.id.news_big_category_title);
             newsImageView = (ImageView) view.findViewById(R.id.news_imageview);

             spinner = (RelativeLayout)view.findViewById(R.id.spinner);
             view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition(),v);
        }

    }
    public void setOnItemClickListener(ClickListener clickListener) {
        TileAdapterRecycle.mClickListener = clickListener;
    }
    public interface ClickListener{
        void onItemClick(int position, View v);

    }

    public TileAdapterRecycle(String[] n, Context c){
        this.StringBigCat = n;
        this.context = c;
        this.imageLoader = ImageLoader.getInstance();
        if (!this.imageLoader.isInited()) {
            this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        }
    }

    @Override
    public TileAdapterRecycle.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewitem_smallnews_tile,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final TileAdapterRecycle.MyViewHolder holder, final int position) {



        JSONObject params = new JSONObject();
        String bigCatSlug = NewsCategoryConstants.getSmallCategoriesForBigCategory(StringBigCat[position].toString())[0];
        if(StringBigCat[position].compareTo("extra")==0){ //Extra has a problem
            bigCatSlug = NewsCategoryConstants.getSmallCategoriesForBigCategory(StringBigCat[position].toString())[1];
        }
      holder.tvBigCategoryTitle.setText(StringBigCat[position]);
        try{
            params.put("slug",bigCatSlug);
            params.put("startIndex", 0);
            params.put("endIndex", 0);
        }catch (Exception e) {
            Log.e(getClass().toString(), "Error setting params for communication with server");
            e.printStackTrace();
        }
        String test;
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


                             //   news = new News(photoURLString, titleString, publisherString, dateString, contentString, urlString);
                            } catch (Exception e) {
                            //    news = new News("Unknown", "Unknown", "Unknown", "Unknown", "Unknown","Unknown");
                            }

                        }
                       //holder.tv2.setText(news.getNewsDate());
                       holder.tv.setText(news.getNewsHead());

                       // if(position==0) {holder.newsImageView.getLayoutParams().height=240;}

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
                                    holder.newsImageView.setImageBitmap(loadedImage);
                                    holder.spinner.setVisibility(View.GONE);
                                }

                                @Override
                                public void onLoadingCancelled(String imageUri, View view) {


                                }
                            });
                        }catch(Exception e){
                             holder.newsImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nopic));
                             holder.spinner.setVisibility(View.GONE);
                        }

                    }
                }, new CustomErrorListener("ERROR"));

    }

    @Override
    public int getItemCount() {
        return StringBigCat.length;
    }




}
