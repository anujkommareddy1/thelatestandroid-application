package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Pedro on 7/2/2016.
 */
public class BigCategoryRecyclerAdapter extends RecyclerView.Adapter<BigCategoryRecyclerAdapter.MyViewHolder> {
    private Context context;
    private String[] StringBigCat;
    private News news;
    private ImageLoader imageLoader;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView newTitle,newsContent,newsTitle,smallCatName;
        public ImageView newsImageView,tvBigCatTitle;
        public RelativeLayout spinner;

        public MyViewHolder(View view){
                super(view);
            newsTitle = (TextView)  view.findViewById(R.id.bigcat_viewpager_news_title);
            newsImageView = (ImageView) view.findViewById(R.id.bigcat_viewpager_imageView);
            smallCatName = (TextView) view.findViewById(R.id.bigcat_viewpager_smallcat);
            newsImageView = (ImageView) view.findViewById(R.id.bigcat_viewpager_imageView);
            newsTitle = (TextView) view.findViewById(R.id.bigcat_viewpager_news_title);
            newsContent = (TextView) view.findViewById(R.id.bigcat_viewpager_content);
            spinner = (RelativeLayout)view.findViewById(R.id.spinner);

        }



    }


    public BigCategoryRecyclerAdapter(String[] n, Context c){
        this.StringBigCat = n;
        this.context = c;
        this.imageLoader = ImageLoader.getInstance();
        if (!this.imageLoader.isInited()) {
            this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        }
    }

    @Override
    public BigCategoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.bigcat_listview,parent,false);

        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final BigCategoryRecyclerAdapter.MyViewHolder holder, final int position) {


            holder.smallCatName.setText(StringBigCat[position]);
            JSONObject params = new JSONObject();
            try {
                params.put("slug", StringBigCat[position]);
                params.put("startIndex", 0);
                params.put("endIndex", 0);
            } catch (Exception e) {
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


                                  //  news = new News(photoURLString, titleString, publisherString, dateString, contentString, urlString);
                                } catch (Exception e) {
                                  //  news = new News("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown");
                                }

                            }
                            //holder.tv2.setText(news.getNewsDate());
                            //holder.tv.setText(news.getNewsHead());
                            holder.newsTitle.setText(news.getNewsHead());
                            if (news.getNewsContent().length() >= 150) {
                                holder.newsContent.setText(news.getNewsContent().substring(0, 150) + "...Read More");
                            } else
                                holder.newsContent.setText(news.getNewsContent() + "...Read More");


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
                            } catch (Exception e) {
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
