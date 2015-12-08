package com.thelatest.thelatestmobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.volley.Volley;

/**
 * Created by Jesse on 10/12/15.
 */
public class SignInFragment extends Fragment {

    private View rootView;
    private TextView usernameTextView;
    private TextView passwordTextView;

    public SignInFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_signin, container, false);

        usernameTextView = (TextView)rootView.findViewById(R.id.id_textfield);
        passwordTextView = (TextView)rootView.findViewById(R.id.pw_textfield);

        Button signInButton = (Button)rootView.findViewById(R.id.signin_button);
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
            }
        });
        return rootView;
    }
}
