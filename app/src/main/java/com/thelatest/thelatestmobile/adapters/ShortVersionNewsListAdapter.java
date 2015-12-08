package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/18/15.
 */
public class ShortVersionNewsListAdapter extends ArrayAdapter<News> {

    private int resource;

    public ShortVersionNewsListAdapter(Context context, int resource, ArrayList<News> newsArr){
        super(context, resource, newsArr);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout rowView;
        News news = getItem(position);

        if(convertView == null){
            rowView = new LinearLayout(getContext());
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(resource, rowView, true);
        }
        else{
            rowView = (LinearLayout)convertView;
        }

        ImageView newsPicImageView = (ImageView)rowView.findViewById(R.id.news_pic_imageview);
        TextView newsHeadTextView = (TextView)rowView.findViewById(R.id.news_head_textview);
        TextView newsOriginDateTextView = (TextView)rowView.findViewById(R.id.news_origin_date_textview);
        TextView newsContentTextView = (TextView)rowView.findViewById(R.id.news_content_textview);

        newsPicImageView.setImageDrawable(news.getNewsPic());
        newsHeadTextView.setText(news.getNewsHead());
        newsOriginDateTextView.setText(news.getNewsFrom() + " | " + news.getNewsDate());

        String newsContent = news.getNewsContent() + " <font color=#A80000>Read More...</font>";
        newsContentTextView.setText(Html.fromHtml(newsContent));

        return rowView;
    }
}
