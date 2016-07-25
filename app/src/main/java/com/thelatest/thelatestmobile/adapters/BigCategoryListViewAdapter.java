package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.MainActivity;
import com.thelatest.thelatestmobile.NewsActivitySources;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.fragments.BigCategoryFragments;
import com.thelatest.thelatestmobile.fragments.FinalStoryFragment;
import com.thelatest.thelatestmobile.fragments.SmallNewsFragment;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pedro on 7/12/2016.
 */
public class BigCategoryListViewAdapter extends ArrayAdapter<News>{

    final DisplayImageOptions options = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .cacheInMemory(true)
            .build();

    private Context context;
    private String[] smallCatsList;
    private ArrayList<News> news;
    private ImageLoader imageLoader;
    private String bigCat;

    static class myViewHolder{
        TextView smallCatName;
        RelativeLayout spinner;
        ImageView newsImageView;
        TextView newsTitle;
        TextView newsContent;

    }

    public BigCategoryListViewAdapter(Context context, int resource,ArrayList<News> l,String[] smallCatsList, String bigCat){
        super(context,resource,l);
        this.bigCat = bigCat;
        this.news = l;
        this.context = context;
        this.smallCatsList = smallCatsList;
        this.imageLoader = ImageLoader.getInstance();
        if(!this.imageLoader.isInited()) {  this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));}
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public long getItemId(int position) {return position;} //modificar aqui



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final myViewHolder mvh;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        News news = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.main_view_bigcat, null);
            mvh = new myViewHolder();
            mvh.smallCatName = (TextView) convertView.findViewById(R.id.bigcat_viewpager_smallcat);
            mvh.spinner = (RelativeLayout) convertView.findViewById(R.id.spinner);
            mvh.newsImageView = (ImageView) convertView.findViewById(R.id.bigcat_viewpager_imageView);
            mvh.newsTitle = (TextView) convertView.findViewById(R.id.bigcat_viewpager_news_title);
            mvh.newsContent = (TextView) convertView.findViewById(R.id.bigcat_viewpager_content);
            convertView.setTag(mvh);


        } else {
            mvh = (myViewHolder) convertView.getTag();
        }

        try{
            imageLoader.loadImage(news.getNewsPic(),options, new ImageLoadingListener() {
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

        convertView.setOnClickListener(new CustomClickListener(position,news.getNewsHead(), news.getNewsDate(), news.getNewsFrom(), news.getNewsContent(), news.getNewsPic(),news.getNewsUrl(),news.getNewsId()));






       /* TextView smallCatName = (TextView) convertView.findViewById(R.id.bigcat_viewpager_smallcat);
        final RelativeLayout spinner = (RelativeLayout) convertView.findViewById(R.id.spinner);
        final ImageView newsImageView = (ImageView) convertView.findViewById(R.id.bigcat_viewpager_imageView);
        final TextView newsTitle = (TextView) convertView.findViewById(R.id.bigcat_viewpager_news_title);
        final TextView newsContent = (TextView) convertView.findViewById(R.id.bigcat_viewpager_content);
            */

               mvh.smallCatName.setText(smallCatsList[position]);
               mvh.newsTitle.setText(news.getNewsHead());
               mvh.newsContent.setText(news.getNewsContent());


        return convertView;
    }

    public class CustomClickListener implements View.OnClickListener
    {
        private String newsTitle;
        private String newsDate;
        private String newsFrom;
        private String newsContent;
        private String newsPic;
        private String newsId;
        private String newsUrl;

        private int position;
        public CustomClickListener(int pos, String newsTitle, String newsDate, String newsFrom, String newsContent,
                                   String newsPic,String newsUrl,String newsId){

            this.position = pos;
            this.newsTitle = newsTitle;
            this.newsDate = newsDate;
            this.newsFrom = newsFrom;
            this.newsContent = newsContent;
            this.newsPic = newsPic;
            this.newsUrl = newsUrl;
            this.newsId = newsId;
        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(context, MainActivity.class);
            Fragment mFragment = new FinalStoryFragment();
            Bundle arguments = new Bundle();
           // arguments.putString(KeyConstants.BIG_CATEGORY, bigCat);
         //   arguments.putString(KeyConstants.SMALL_CATEGORY, smallCatsList[position]);
            arguments.putString(KeyConstants.NEWS_TITLE, newsTitle);
            arguments.putString(KeyConstants.NEWS_DATE, newsDate);
            arguments.putString(KeyConstants.NEWS_FROM, newsFrom);
            arguments.putString(KeyConstants.NEWS_CONTENTS, newsContent);
            arguments.putString(KeyConstants.NEWS_PIC, newsPic);
            arguments.putString(KeyConstants.NEWS_URL, newsUrl);
            arguments.putString(KeyConstants.NEWS_ID,newsId);
            mFragment.setArguments(arguments);
            FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_fragment_view,mFragment);
            ft.commit();

        }
    }






}
