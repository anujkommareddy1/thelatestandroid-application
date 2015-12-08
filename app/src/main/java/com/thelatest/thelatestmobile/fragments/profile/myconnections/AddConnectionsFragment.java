package com.thelatest.thelatestmobile.fragments.profile.myconnections;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.AddUserListAdapter;
import com.thelatest.thelatestmobile.example.AddConnectionsExample;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/17/15.
 */
public class AddConnectionsFragment extends Fragment {

    private View rootView;
    private TextView textView;
    private ListView listView;
    private AddUserListAdapter adapter;

    public AddConnectionsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile_myconnections_addconnections, container, false);
        listView = (ListView)rootView.findViewById(R.id.listview);
        textView = (TextView)rootView.findViewById(R.id.edittext_searchbox);
        textView.setHint("Search Connection");

        ArrayList<User> notMyConnections = getNotMyConnectionsFromServer();
        adapter = new AddUserListAdapter(getActivity().getBaseContext(), R.layout.listviewitem_addconnections, notMyConnections);
        listView.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<User> getNotMyConnectionsFromServer(){
        return AddConnectionsExample.getNotMyConnections(rootView.getContext());
    }
}
