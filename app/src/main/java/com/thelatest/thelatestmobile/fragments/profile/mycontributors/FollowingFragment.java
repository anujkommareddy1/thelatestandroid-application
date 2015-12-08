package com.thelatest.thelatestmobile.fragments.profile.mycontributors;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.example.FollowingExample;
import com.thelatest.thelatestmobile.objects.Thinking;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/17/15.
 */
public class FollowingFragment extends Fragment {

    private View rootView;
    private LinearLayout horizontalScrollViewLayout;
    private LinearLayout thinkingsLayout;
    private ScrollView thinkingsScrollView;

    public FollowingFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile_mycontributors_following, container, false);
        horizontalScrollViewLayout = (LinearLayout)rootView.findViewById(R.id.horizontalscrollview_layout);
        thinkingsLayout = (LinearLayout)rootView.findViewById(R.id.thinkings_layout);
        thinkingsScrollView = (ScrollView)rootView.findViewById(R.id.thinkingsScrollView);

        drawHorizontalContributorsView();

        return rootView;
    }

    private void drawHorizontalContributorsView(){
        ArrayList<User> myContributors = getMyFollowingsFromServer();

        for(User user : myContributors){
            Drawable userPic = user.getPic();
            String userName = user.getName();

            View view = View.inflate(rootView.getContext(), R.layout.item_user_pic_name, null);
            ImageView userPicImageView = (ImageView)view.findViewById(R.id.user_pic);
            TextView userNameTextView = (TextView)view.findViewById(R.id.user_name);
            userPicImageView.setImageDrawable(userPic);
            userNameTextView.setText(userName);

            horizontalScrollViewLayout.addView(view);

            view.setOnClickListener(new FollowingClick(user));
        }
    }

    private class FollowingClick implements View.OnClickListener{
        private User user;

        public FollowingClick(User user){
            this.user = user;
        }

        @Override
        public void onClick(View v){
            String userName = user.getName();

            TextView biographyTitle = (TextView)rootView.findViewById(R.id.biography_title);
            biographyTitle.setText(userName + " Biography");

            TextView thinkingTitle = (TextView)rootView.findViewById(R.id.thinking_title);
            thinkingTitle.setText(userName + " Thinking");

            String bio = getBioFromServer(user);
            TextView biographyContents = (TextView)rootView.findViewById(R.id.biography_contents);
            biographyContents.setText(bio);

            thinkingsLayout.removeAllViews();

            for(Thinking thinking : getThinkingsFromServer(user)){
                View thinkingLayout = View.inflate(rootView.getContext(), R.layout.viewpageritem_thinking, null);

                ImageView thinkingPicImageView = (ImageView)thinkingLayout.findViewById(R.id.thinking_pic);
                TextView thinkingTitleTextView = (TextView)thinkingLayout.findViewById(R.id.thinking_title);
                TextView thinkingSummaryTextView = (TextView)thinkingLayout.findViewById(R.id.thinking_summary);
                TextView thinkingInfoTextView = (TextView)thinkingLayout.findViewById(R.id.thinking_info);

                thinkingPicImageView.setImageDrawable(thinking.getNewsPic());
                thinkingTitleTextView.setText(thinking.getTitle());
                thinkingSummaryTextView.setText(thinking.getSummary());
                thinkingInfoTextView.setText(thinking.getDate());

                thinkingsLayout.addView(thinkingLayout);
            }

            thinkingsScrollView.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    private ArrayList<User> getMyFollowingsFromServer(){
        return FollowingExample.getMyFollowers(rootView.getContext());
    }

    private String getBioFromServer(User user){
        return FollowingExample.getBio(user.getID());
    }

    private ArrayList<Thinking> getThinkingsFromServer(User user){
        return FollowingExample.getThinkings(rootView.getContext(), user.getID());
    }
}
