package com.thelatest.thelatestmobile.example;

import android.content.Context;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class AddContributorsExample {

    public static ArrayList<User> getNotMyContributors(Context context){
        ArrayList<User> notMyContributors = new ArrayList<User>();

        //=======================================================================================================================================
        // USER 1
        User user1 = new User(
                4,
                "Mark Zuckerberg",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
        //=======================================================================================================================================
        // USER 2
        User user2 = new User(
                5,
                "Bela Lugosi",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
        //=======================================================================================================================================
        // USER 3
        User user3 = new User(
                6,
                "William Shakespear",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
        //=======================================================================================================================================
        notMyContributors.add(user1);
        notMyContributors.add(user2);
        notMyContributors.add(user3);

        return notMyContributors;
    }
}
