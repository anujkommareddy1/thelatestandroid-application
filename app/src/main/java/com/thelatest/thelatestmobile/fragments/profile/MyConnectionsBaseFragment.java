package com.thelatest.thelatestmobile.fragments.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.fragments.profile.myconnections.AddConnectionsFragment;
import com.thelatest.thelatestmobile.fragments.profile.myconnections.MyConnectionsFragment;

/**
 * Created by Jesse on 10/13/15.
 */
public class MyConnectionsBaseFragment extends Fragment {

    private View rootView;
    private Button myConnectionsButton;
    private Button addConnectionsButton;

    public MyConnectionsBaseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.widget_with_segmentedtogglebutton, container, false);

        myConnectionsButton = (Button)rootView.findViewById(R.id.left_button);
        addConnectionsButton = (Button)rootView.findViewById(R.id.right_button);
        myConnectionsButton.setText("My Connections");
        addConnectionsButton.setText("Add Connections");

        myConnectionsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                leftButtonClicked();
                return true;
            }
        });

        addConnectionsButton.setOnTouchListener(new View.OnTouchListener() {
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
        myConnectionsButton.setPressed(true);
        addConnectionsButton.setPressed(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.sub_window, new MyConnectionsFragment())
                .commit();
    }

    private void rightButtonClicked(){
        addConnectionsButton.setPressed(true);
        myConnectionsButton.setPressed(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.sub_window, new AddConnectionsFragment())
                .commit();
    }
}
