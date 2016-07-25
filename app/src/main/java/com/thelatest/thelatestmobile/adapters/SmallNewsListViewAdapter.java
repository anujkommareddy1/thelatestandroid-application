package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.NewsActivity;
import com.thelatest.thelatestmobile.NewsActivitySources;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.fragments.FinalStoryFragment;
import com.thelatest.thelatestmobile.objects.News;

import org.w3c.dom.Text;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by Logan on 12/14/15.
 */
public class SmallNewsListViewAdapter extends ArrayAdapter<News> {

    private Context context;
    private ImageLoader imageLoader;
    final DisplayImageOptions options = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .cacheInMemory(true)
            .build();


    public SmallNewsListViewAdapter(Context context, int resource, ArrayList<News> newsArr){
        super(context, resource, newsArr);
        this.context = context;

        this.imageLoader = ImageLoader.getInstance();
        if(!this.imageLoader.isInited()) {  this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));  }
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent){
        News news = getItem(position);

        if(itemView == null){
            itemView = View.inflate(context, R.layout.bigcat_listview, null);
        }
        //Button b1 = (Button) itemView.findViewById(R.id.button_source);

        final TextView newsTitleTextView = (TextView)itemView.findViewById(R.id.bigcat_viewpager_news_title);
        final TextView newsContent = (TextView) itemView.findViewById(R.id.bigcat_viewpager_content);
        //TextView newsDateTextView = (TextView)itemView.findViewById(R.id.news_date_textview);
        ImageView newsImageView = (ImageView)itemView.findViewById(R.id.bigcat_viewpager_imageView);
        newsImageView.setTag(position);
        RelativeLayout spinner = (RelativeLayout)itemView.findViewById(R.id.spinner);

        //newsContent.setText(news.getNewsContent());

        newsTitleTextView.setText(news.getNewsHead());
        //newsDateTextView.setText(news.getNewsDate());

        try {
            URL url = new URL(news.getNewsPic());
            imageLoader.loadImage(news.getNewsPic(),options, new CustomSimpleImageLoadingListener(newsImageView, spinner));
        }catch(Exception e){
            newsImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nopic));
            spinner.setVisibility(View.GONE);
        }

       // b1.setOnClickListener(new CustomB1Listener(news.getNewsHead(),news.getNewsId()));


        itemView.setOnClickListener(new CustomSingleNewsOnClickListener(news.getNewsHead(), news.getNewsDate(), news.getNewsFrom(), news.getNewsContent(), news.getNewsPic(),news.getNewsUrl(),news.getNewsId()));

        return itemView;
    }

    public class CustomB1Listener implements View.OnClickListener{
        private String title,id;
        public CustomB1Listener(String title, String id){
            this.title = title;
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NewsActivitySources.class);
            intent.putExtra(KeyConstants.NEWS_TITLE, title);
            intent.putExtra(KeyConstants.NEWS_ID,id);
            context.startActivity(intent);
        }
    }


    public class CustomSimpleImageLoadingListener implements ImageLoadingListener
    {
        final private ImageView newsPhotoImageView;
        private RelativeLayout spinner;

        public CustomSimpleImageLoadingListener(ImageView newsPhotoImageView, RelativeLayout spinner){
            this.newsPhotoImageView = newsPhotoImageView;
            this.spinner = spinner;
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {}

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {}

        @Override
        public void onLoadingCancelled(String imageUri, View view) {}

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            newsPhotoImageView.setImageBitmap(loadedImage);
            spinner.setVisibility(View.GONE);
        }
    }

    public class CustomSingleNewsOnClickListener implements View.OnClickListener
    {
        private String newsTitle;
        private String newsDate;
        private String newsFrom;
        private String newsContent;
        private String newsPic;
        private String newsId;
        private String newsUrl;

        public CustomSingleNewsOnClickListener(String newsTitle, String newsDate, String newsFrom, String newsContent,
                                               String newsPic,String newsUrl,String newsId){
            this.newsTitle = newsTitle;
            this.newsDate = newsDate;
            this.newsFrom = newsFrom;
            this.newsContent = newsContent;
            this.newsPic = newsPic;
            this.newsUrl = newsUrl;
            this.newsId = newsId;
        }

        @Override
        public void onClick(View v){
            Intent intent = new Intent(context, FinalStoryFragment.class);
            Fragment mFragment = new FinalStoryFragment();
            Bundle arguments = new Bundle();

            arguments.putString(KeyConstants.NEWS_TITLE, newsTitle);
            arguments.putString(KeyConstants.NEWS_DATE, newsDate);
            arguments.putString(KeyConstants.NEWS_FROM, newsFrom);
            arguments.putString(KeyConstants.NEWS_CONTENTS, newsContent);
            arguments.putString(KeyConstants.NEWS_PIC, newsPic);
            arguments.putString(KeyConstants.NEWS_URL, newsUrl);
            arguments.putString(KeyConstants.NEWS_ID,newsId);
            mFragment.setArguments(arguments);
            FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_view,mFragment);
            ft.commit();
        }
    }
}
