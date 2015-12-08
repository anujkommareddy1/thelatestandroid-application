package com.thelatest.thelatestmobile.fragments.profile.myconnections;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.example.MyConnectionsExample;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/17/15.
 */
public class MyConnectionsFragment extends Fragment {

    private View rootView;
    private LinearLayout horizontalScrollViewLayout;

    public MyConnectionsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile_myconnections_myconnections, container, false);
        horizontalScrollViewLayout = (LinearLayout)rootView.findViewById(R.id.horizontalscrollview_layout);

        drawHorizontalCityList();

        return rootView;
    }

    private void drawHorizontalCityList(){
        ArrayList<User> myConnections = getMyConnectionsFromServer();

        for(User user : myConnections){
            Drawable userPic = user.getPic();
            String userName = user.getName();

            View view = View.inflate(rootView.getContext(), R.layout.item_user_pic_name, null);
            ImageView userPicImageView = (ImageView)view.findViewById(R.id.user_pic);
            TextView userNameTextView = (TextView)view.findViewById(R.id.user_name);
            userPicImageView.setImageDrawable(userPic);
            userNameTextView.setText(userName);

            horizontalScrollViewLayout.addView(view);
        }
    }

    private class UserClick implements View.OnClickListener{
        private User user;

        public UserClick(User user){
            this.user = user;
        }

        @Override
        public void onClick(View v){
            Toast.makeText(rootView.getContext(), user.getName() + " Chatting will show", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<User> getMyConnectionsFromServer(){
        return MyConnectionsExample.getMyConnections(rootView.getContext());
    }
}
