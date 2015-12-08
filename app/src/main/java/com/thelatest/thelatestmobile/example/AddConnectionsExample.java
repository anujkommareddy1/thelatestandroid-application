package com.thelatest.thelatestmobile.example;

import android.content.Context;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class AddConnectionsExample {

    public static ArrayList<User> getNotMyConnections(Context context){

        ArrayList<User> notMyConnections = new ArrayList<User>();

        //=======================================================================================================================================
        // USER 1
        User user1 = new User(
                1,
                "Author Conan Doyle",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
        //=======================================================================================================================================
        // USER 2
        User user2 = new User(
                2,
                "Evelyn Waugh",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
        //=======================================================================================================================================
        User user3 = new User(
                3,
                "Robert Frost",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
        //=======================================================================================================================================
        notMyConnections.add(user1);
        notMyConnections.add(user2);
        notMyConnections.add(user3);

        return notMyConnections;
    }
}
