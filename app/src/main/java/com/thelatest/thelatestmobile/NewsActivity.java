package com.thelatest.thelatestmobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.fragments.BrowserFragment;

import java.net.URL;

public class NewsActivity extends AppCompatActivity {

    private ImageLoader imageLoader;
    private ImageView newsImageView;
    private WebView mWebview ;
    private String url;

    public void finish(View view){
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Button share = (Button) findViewById(R.id.share_browser);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, url);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        android.app.Fragment news_fragment =null;
        android.app.FragmentManager fragmentManager = getFragmentManager();
        news_fragment = new BrowserFragment();
        url = getIntent().getStringExtra(KeyConstants.NEWS_URL);
        TextView urlBrowser = (TextView) findViewById(R.id.browser_url);

        urlBrowser.setText(url);


      fragmentManager.beginTransaction().replace(R.id.news_fragment,news_fragment).commit();

       /* mWebview  = new WebView(this);
        //mWebview = (WebView) findViewById(R.id.web_view);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        */


        /*
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
        }*/

        //mWebview.loadUrl("http://www.google.com");


    }

}
