package com.thelatest.thelatestmobile.fragments.profile.mycontributors;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.AddUserListAdapter;
import com.thelatest.thelatestmobile.example.AddContributorsExample;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/17/15.
 */
public class AddContributorsFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private TextView textView;
    private ArrayList<User> notMyContributors;
    private AddUserListAdapter adapter;

    public AddContributorsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile_mycontributors_addcontributors, container, false);
        listView = (ListView)rootView.findViewById(R.id.listview);
        textView = (TextView)rootView.findViewById(R.id.edittext_searchbox);
        textView.setHint("Search Contributor");

        notMyContributors = AddContributorsExample.getNotMyContributors(rootView.getContext());
        adapter = new AddUserListAdapter(getActivity().getBaseContext(), R.layout.listviewitem_addcontributors, notMyContributors);
        listView.setAdapter(adapter);

        return rootView;
    }
}
