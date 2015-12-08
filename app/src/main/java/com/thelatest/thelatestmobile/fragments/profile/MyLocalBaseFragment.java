package com.thelatest.thelatestmobile.fragments.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.fragments.profile.mylocal.AddCityFragment;
import com.thelatest.thelatestmobile.fragments.profile.mylocal.MyListFragment;

/**
 * Created by Jesse on 10/13/15.
 */
public class MyLocalBaseFragment extends Fragment {

    private View rootView;
    private Button myListButton;
    private Button addCityButton;

    public MyLocalBaseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.widget_with_segmentedtogglebutton, container, false);

        myListButton = (Button)rootView.findViewById(R.id.left_button);
        addCityButton = (Button)rootView.findViewById(R.id.right_button);
        myListButton.setText("My List");
        addCityButton.setText("Add List");

        myListButton.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                leftButtonClicked();
                return true;
            }
        });

        addCityButton.setOnTouchListener(new View.OnTouchListener(){
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
        myListButton.setPressed(true);
        addCityButton.setPressed(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.sub_window, new MyListFragment())
                .commit();
    }

    private void rightButtonClicked(){
        addCityButton.setPressed(true);
        myListButton.setPressed(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.sub_window, new AddCityFragment())
                .commit();
    }
}
