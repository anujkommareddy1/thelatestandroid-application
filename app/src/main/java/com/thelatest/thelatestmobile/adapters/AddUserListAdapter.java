package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/17/15.
 */
public class AddUserListAdapter extends ArrayAdapter<User> {

    private int resource;

    public AddUserListAdapter(Context context, int resource, ArrayList<User> users){
        super(context, resource, users);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RelativeLayout rowView;
        User person = getItem(position);

        if(convertView == null){
            rowView = new RelativeLayout(getContext());
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(resource, rowView, true);
        }
        else{
            rowView = (RelativeLayout)convertView;
        }

        ImageView userPicImageView = (ImageView)rowView.findViewById(R.id.user_pic);
        TextView userNameTextView = (TextView)rowView.findViewById(R.id.user_name);

        userPicImageView.setImageDrawable(person.getPic());
        userNameTextView.setText(person.getName());

        return rowView;
    }
}
