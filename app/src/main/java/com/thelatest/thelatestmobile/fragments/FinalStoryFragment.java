package com.thelatest.thelatestmobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.NewsActivity;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.TileAdapterRecycle;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;


public class FinalStoryFragment extends Fragment {
    private Context context;
    public FinalStoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_final_story, container, false);
        this.context = layout.getContext();
        ImageLoader imageLoader = ImageLoader.getInstance();
        final ImageView mImageView = (ImageView) layout.findViewById(R.id.finalstory_imageView);
        TextView contentTextView = (TextView) layout.findViewById(R.id.finalstory_content);
        TextView titleTextView = (TextView) layout.findViewById(R.id.finalstory_news_title);
        TextView dateTextView = (TextView) layout.findViewById(R.id.finalstory_date);
        final RelativeLayout spinner = (RelativeLayout) layout.findViewById(R.id.spinner);
        final RelativeLayout spinner2 = (RelativeLayout) layout.findViewById(R.id.source_spinner);
        final TextView sourcesTextView = (TextView) layout.findViewById(R.id.finalstory_sourceid);

        TextView readFull = (TextView) layout.findViewById(R.id.finalstory_readfull);

        final LinearLayout sourceLayout = (LinearLayout) layout.findViewById(R.id.finalstory_source_layout);

        Bundle mBundle = getArguments();

        String dateReduced = mBundle.getString(KeyConstants.NEWS_DATE).substring(0,19);

        contentTextView.setText(mBundle.getString(KeyConstants.NEWS_CONTENTS));
        titleTextView.setText(mBundle.getString(KeyConstants.NEWS_TITLE));
        dateTextView.setText(dateReduced);

       // layout.setOnClickListener(new CustomClickListener(mBundle.getString(KeyConstants.NEWS_URL)));
        readFull.setOnClickListener(new CustomClickListener(mBundle.getString(KeyConstants.NEWS_URL)));


        try{
            imageLoader.loadImage(mBundle.getString(KeyConstants.NEWS_PIC),new myImageLoader(spinner,mImageView));
        }catch(Exception e){
            mImageView.setImageDrawable(getResources().getDrawable(R.drawable.nopic));
            spinner.setVisibility(View.GONE);
        }


        final JSONObject params = new JSONObject();
        try {
            params.put("newsId", mBundle.getString(KeyConstants.NEWS_ID));
        }catch (Exception e) {
            Log.e(getClass().toString(), "Error setting params for communication with server");
            e.printStackTrace();
        }

        Volley volley = Volley.getVolley(layout.getContext());
        volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL+"story", params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                JSONArray responseArr = null;
                try {
                    responseArr = response.getJSONObject("story").getJSONArray("newsRelated");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int countButton=0;
                sourceLayout.setWeightSum((float)responseArr.length());
                for(int i=1;i<responseArr.length();i++){
                    try {

                        JSONObject object = responseArr.getJSONObject(i);
                        String urlSource = object.getString("url");
                        String nameSource = object.getString("source");

                        Button buttonSource = new Button(context);
                        countButton++;
                        buttonSource.setSingleLine(true);
                        buttonSource.setEllipsize(TextUtils.TruncateAt.END);
                        buttonSource.setText(nameSource);
                        buttonSource.setOnClickListener(new CustomClickListener(urlSource));
                        sourceLayout.addView(buttonSource);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(countButton==0){sourcesTextView.setText("More sources unavailable.");};
                spinner2.setVisibility(View.GONE);

            }}, new CustomErrorListener("ERROR"));



        return layout;

    }

    public class myImageLoader implements ImageLoadingListener{

        private ImageView mImageView;
        private RelativeLayout spinner;
        public myImageLoader(RelativeLayout spin, ImageView imageView){

            this.spinner = spin;
            this.mImageView = imageView;
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            mImageView.setImageBitmap(loadedImage);
            spinner.setVisibility(View.GONE);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }
    }

    public class myFragmentVolley implements Response.Listener{

        @Override
        public void onResponse(Object response) {

        }
    }

    public class CustomClickListener implements View.OnClickListener{

        String NEWS_URL;
        public CustomClickListener(String url){
            this.NEWS_URL = url;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NewsActivity.class);
            intent.putExtra(KeyConstants.NEWS_URL,NEWS_URL);
            context.startActivity(intent);
        }
    }


}
