package com.thelatest.thelatestmobile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.adapters.SmallNewsListViewAdapter;

import java.net.URL;
import java.security.Key;

public class NewsActivity extends AppCompatActivity {

    private ImageLoader imageLoader;
    private ImageView newsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String newsTitle = getIntent().getStringExtra(KeyConstants.NEWS_TITLE);
        String newsDate = getIntent().getStringExtra(KeyConstants.NEWS_DATE);
        String newsFrom = getIntent().getStringExtra(KeyConstants.NEWS_FROM);
        String newsContents = getIntent().getStringExtra(KeyConstants.NEWS_CONTENTS);
        String newsPic = getIntent().getStringExtra(KeyConstants.NEWS_PIC);

        TextView newsTitleTextView = (TextView)findViewById(R.id.title);
        TextView newsDateTextView = (TextView)findViewById(R.id.date);
        TextView newsFromTextView = (TextView)findViewById(R.id.from);
        TextView newsContentTextView = (TextView)findViewById(R.id.contents);
        newsImageView = (ImageView)findViewById(R.id.pic);

        newsTitleTextView.setText(newsTitle);
        newsDateTextView.setText(newsDate);
        newsFromTextView.setText(newsFrom);
        newsContentTextView.setText(newsContents);

        this.imageLoader = ImageLoader.getInstance();
        if(!this.imageLoader.isInited()) {  this.imageLoader.init(ImageLoaderConfiguration.createDefault(this));  }

        try {
            URL url = new URL(newsPic);
            imageLoader.loadImage(newsPic, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    newsImageView.setImageBitmap(loadedImage);
                }
            });
        }catch(Exception e){
            newsImageView.setImageDrawable(getResources().getDrawable(R.drawable.nopic));
        }
    }

}
