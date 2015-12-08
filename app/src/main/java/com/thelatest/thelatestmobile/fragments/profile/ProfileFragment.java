package com.thelatest.thelatestmobile.fragments.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.example.MeExample;
import com.thelatest.thelatestmobile.objects.User;

/**
 * Created by Jesse on 10/13/15.
 */
public class ProfileFragment extends Fragment {

    private View rootView;

    private ImageView userPicImageView;
    private TextView userNameTextView;

    private ImageView myLocalIcon;
    private ImageView mySavedNewsIcon;
    private ImageView myContributorsIcon;
    private ImageView myConnectionsIcon;

    public ProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        User me = getMeFromServer();

        userPicImageView = (ImageView)rootView.findViewById(R.id.user_pic);
        userPicImageView.setImageDrawable(me.getPic());

        userNameTextView = (TextView)rootView.findViewById(R.id.user_name);
        userNameTextView.setText("Hi, " + me.getName());

        myLocalIcon = (ImageView)rootView.findViewById(R.id.my_local_icon);
        myLocalIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction()
                                    .replace(R.id.main_fragment_view, new MyLocalBaseFragment())
                                    .commit();
            }
        });

        mySavedNewsIcon = (ImageView)rootView.findViewById(R.id.my_saved_news_icon);
        mySavedNewsIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction()
                                    .replace(R.id.main_fragment_view, new MySavedNewsBaseFragment())
                                    .commit();
            }
        });

        myContributorsIcon = (ImageView)rootView.findViewById(R.id.my_contributions_icon);
        myContributorsIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction()
                                    .replace(R.id.main_fragment_view, new MyContributorsBaseFragment())
                                    .commit();
            }
        });

        myConnectionsIcon = (ImageView)rootView.findViewById(R.id.my_connections_icon);
        myConnectionsIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction()
                                    .replace(R.id.main_fragment_view, new MyConnectionsBaseFragment())
                                    .commit();
            }
        });

        return rootView;
    }

    private User getMeFromServer(){
        return MeExample.getMe(rootView.getContext());
    }
}
