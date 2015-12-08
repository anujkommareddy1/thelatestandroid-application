package com.thelatest.thelatestmobile.example;

import android.content.Context;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class MyConnectionsExample {

    public static ArrayList<User> getMyConnections(Context context){
        ArrayList<User> myConnections = new ArrayList<User>();

        //=======================================================================================================================================
        // USER 1
        User user1 = new User(
                4,
                "Mark Zuckerberg",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );

        // CHATTING for USER 1


        //=======================================================================================================================================
        // USER 2
        User user2 = new User(
                5,
                "Bela Lugosi",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );


        // CHATTING for USER 2


        //=======================================================================================================================================
        // USER 3
        User user3 = new User(
                6,
                "William Shakespear",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );


        // CHATTING for USER 3


        //=======================================================================================================================================
        myConnections.add(user1);
        myConnections.add(user2);
        myConnections.add(user3);

        return myConnections;
    }
}
