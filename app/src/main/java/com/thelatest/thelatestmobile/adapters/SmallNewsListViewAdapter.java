package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.NewsActivity;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Logan on 12/14/15.
 */
public class SmallNewsListViewAdapter extends ArrayAdapter<News> {

    private Context context;
    private ImageLoader imageLoader;

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
            itemView = View.inflate(context, R.layout.listviewitem_smallnews, null);
        }

        final TextView newsTitleTextView = (TextView)itemView.findViewById(R.id.news_title_textview);
        TextView newsDateTextView = (TextView)itemView.findViewById(R.id.news_date_textview);
        ImageView newsImageView = (ImageView)itemView.findViewById(R.id.news_imageview);
        newsImageView.setTag(position);
        RelativeLayout spinner = (RelativeLayout)itemView.findViewById(R.id.spinner);

        newsTitleTextView.setText(news.getNewsHead());
        newsDateTextView.setText(news.getNewsDate());

        try {
            URL url = new URL(news.getNewsPic());
            imageLoader.loadImage(news.getNewsPic(), new CustomSimpleImageLoadingListener(newsImageView, spinner));
        }catch(Exception e){
            newsImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nopic));
            spinner.setVisibility(View.GONE);
        }

        itemView.setOnClickListener(new CustomSingleNewsOnClickListener(news.getNewsHead(), news.getNewsDate(), news.getNewsFrom(), news.getNewsContent(), news.getNewsPic()));

        return itemView;
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

        public CustomSingleNewsOnClickListener(String newsTitle, String newsDate, String newsFrom, String newsContent, String newsPic){
            this.newsTitle = newsTitle;
            this.newsDate = newsDate;
            this.newsFrom = newsFrom;
            this.newsContent = newsContent;
            this.newsPic = newsPic;
        }

        @Override
        public void onClick(View v){
            Intent intent = new Intent(context, NewsActivity.class);
            intent.putExtra(KeyConstants.NEWS_TITLE, newsTitle);
            intent.putExtra(KeyConstants.NEWS_DATE, newsDate);
            intent.putExtra(KeyConstants.NEWS_FROM, newsFrom);
            intent.putExtra(KeyConstants.NEWS_CONTENTS, newsContent);
            intent.putExtra(KeyConstants.NEWS_PIC, newsPic);

            context.startActivity(intent);
        }
    }
}
