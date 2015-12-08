package com.thelatest.thelatestmobile.example;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.Thinking;
import com.thelatest.thelatestmobile.objects.User;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class FollowingExample {

    public static ArrayList<User> getMyFollowers(Context context){
        ArrayList<User> myFollowers = new ArrayList<User>();

        User user1 = new User(
                1,
                "Author Conan Doyle",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );

        User user2 = new User(
                2,
                "Evelyn Waugh",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );

        User user3 = new User(
                3,
                "Robert Frost",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );

        myFollowers.add(user1);
        myFollowers.add(user2);
        myFollowers.add(user3);

        return myFollowers;
    }

    public static String getBio(int userIndex){
        String bio = null;

        switch(userIndex){
            case 1:
                bio = "Author Arthur Conan Doyle wrote 60 mystery stories featuring the wildly popular detective character Sherlock Holmes and his loyal assistant Watson.";
                break;
            case 2:
                bio = "English writer Evelyn Waugh is regarded by many as the most brilliant satirical novelist of his day. His works include 'The Loved One' and 'Brideshead Revisited.'";
                break;
            case 3:
                bio = "\"A four-time Pulitzer Prize winner in poetry, American Robert Frost depicted realistic New England life through language and situations familiar to the common man.\"";
                break;
        }

        return bio;
    }

    public static ArrayList<Thinking> getThinkings(Context context, int userIndex){
        ArrayList<Thinking> thinkings = new ArrayList<Thinking>();
        Thinking thinking = null;
        Drawable newsPic = context.getResources().getDrawable(R.drawable.news);

        switch(userIndex){
            case 1:
                thinking = new Thinking(
                        newsPic, "Title 1_1", "Summary 1_1", "Contents 1_1", "Category 1_1", "Date 1_1"
                );
                thinkings.add(thinking);

                thinking = new Thinking(
                        newsPic, "Title 1_2", "Summary 1_2", "Contents 1_2", "Category 1_2", "Date 1_2"
                );
                thinkings.add(thinking);
                break;
            case 2:
                thinking = new Thinking(
                        newsPic, "Title 2_1", "Summary 2_1", "Contents 2_1", "Category 2_1", "Date 2_1"
                );
                thinkings.add(thinking);

                thinking = new Thinking(
                        newsPic, "Title 2_2", "Summary 2_2", "Contents 2_2", "Category 2_2", "Date 2_2"
                );
                thinkings.add(thinking);
                break;
            case 3:
                thinking = new Thinking(
                        newsPic, "Title 3_1", "Summary 3_1", "Contents 3_1", "Category 3_1", "Date 3_1"
                );
                thinkings.add(thinking);

                thinking = new Thinking(
                        newsPic, "Title 3_2", "Summary 3_2", "Contents 3_2", "Category 3_2", "Date 3_2"
                );
                thinkings.add(thinking);
                break;
        }

        return thinkings;
    }
}
