package com.thelatest.thelatestmobile.fragments.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.fragments.profile.mycontributors.AddContributorsFragment;
import com.thelatest.thelatestmobile.fragments.profile.mycontributors.FollowingFragment;

/**
 * Created by Jesse on 10/13/15.
 */
public class MyContributorsBaseFragment extends Fragment {

    private View rootView;
    private Button followingButton;
    private Button addContributorsButton;

    public MyContributorsBaseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.widget_with_segmentedtogglebutton, container, false);

        followingButton = (Button)rootView.findViewById(R.id.left_button);
        addContributorsButton = (Button)rootView.findViewById(R.id.right_button);
        followingButton.setText("Following");
        addContributorsButton.setText("Add Contributors");

        followingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                leftButtonClicked();
                return true;
            }
        });

        addContributorsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                rightButtonClicked();
                return true;
            }
        });

        leftButtonClicked();

        return rootView;
    }

    private void leftButtonClicked(){
        followingButton.setPressed(true);
        addContributorsButton.setPressed(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.sub_window, new FollowingFragment())
                .commit();
    }

    private void rightButtonClicked(){
        addContributorsButton.setPressed(true);
        followingButton.setPressed(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.sub_window, new AddContributorsFragment())
                .commit();
    }
}
