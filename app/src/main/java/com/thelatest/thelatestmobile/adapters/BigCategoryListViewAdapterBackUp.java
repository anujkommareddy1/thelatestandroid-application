package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Pedro on 7/12/2016.
 */
public class BigCategoryListViewAdapterBackUp extends BaseAdapter{

    private Context context;
    private String[] smallCatsList;
    private News news;
    private ImageLoader imageLoader;

     static class myViewHolder{
         TextView smallCatName;
         RelativeLayout spinner;
         ImageView newsImageView;
         TextView newsTitle;
         TextView newsContent;


    }

    public BigCategoryListViewAdapterBackUp(Context context, String[] smallCatsList){
        this.context = context;
        this.smallCatsList = smallCatsList;
        this.imageLoader = ImageLoader.getInstance();
        if(!this.imageLoader.isInited()) {  this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));}
    }

    @Override
    public int getCount() {
        return smallCatsList.length;
    }

    @Override
    public Object getItem(int position) {
        return smallCatsList[position];
    }

    @Override
    public long getItemId(int position) {return 0;} //modificar aqui



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final myViewHolder mvh;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);



           if(convertView ==null) {
               convertView = inflater.inflate(R.layout.bigcat_listview, null);

               mvh = new myViewHolder();
               mvh.smallCatName = (TextView) convertView.findViewById(R.id.bigcat_viewpager_smallcat);
               mvh.spinner = (RelativeLayout) convertView.findViewById(R.id.spinner);
               mvh.newsImageView = (ImageView) convertView.findViewById(R.id.bigcat_viewpager_imageView);
               mvh.newsTitle = (TextView) convertView.findViewById(R.id.bigcat_viewpager_news_title);
               mvh.newsContent = (TextView) convertView.findViewById(R.id.bigcat_viewpager_content);
               convertView.setTag(mvh);

           }else { mvh = (myViewHolder) convertView.getTag();}



       /* TextView smallCatName = (TextView) convertView.findViewById(R.id.bigcat_viewpager_smallcat);
        final RelativeLayout spinner = (RelativeLayout) convertView.findViewById(R.id.spinner);
        final ImageView newsImageView = (ImageView) convertView.findViewById(R.id.bigcat_viewpager_imageView);
        final TextView newsTitle = (TextView) convertView.findViewById(R.id.bigcat_viewpager_news_title);
        final TextView newsContent = (TextView) convertView.findViewById(R.id.bigcat_viewpager_content);
            */


        mvh.smallCatName.setText(smallCatsList[position]);

        JSONObject params = new JSONObject();
        try{
            params.put("slug",smallCatsList[position]);
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

                              //  news = new News(photoURLString, titleString, publisherString, dateString, contentString,urlString);
                            } catch (Exception e) {
                            //    news = new News("Unknown", "Unknown", "Unknown", "Unknown", "Unknown","Unknown");
                            }




                        }
                        //tv2.setText(news.getNewsDate());

                            mvh.newsContent.setText(news.getNewsContent());
                            mvh.newsTitle.setText(news.getNewsHead());

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
                                   mvh.newsImageView.setImageBitmap(loadedImage);
                                    mvh.spinner.setVisibility(View.GONE);
                                }

                                @Override
                                public void onLoadingCancelled(String imageUri, View view) {


                                }
                            });
                        }catch(Exception e){
                           mvh.newsImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nopic));
                           mvh.spinner.setVisibility(View.GONE);

                        }


                    }
                }, new CustomErrorListener("ERROR"));

       return convertView;
    }


}
