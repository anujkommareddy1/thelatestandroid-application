package com.thelatest.thelatestmobile.example;

import android.content.Context;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.User;

/**
 * Created by Jesse on 10/20/15.
 */
public class MeExample {

    public static User getMe(Context context){
        return new User(
                0,
                "Yoko Song",
                context.getResources().getDrawable(R.drawable.ic_profile)
        );
    }

    public static String getBio(){
        return "Yoko vestibulum feugiat amet sapien. Eros gravida feugiat. Ut torquent neque." +
                "In arcu amet at luctus auctor. Ultirices justo namac tristique sodales lacinia urna " +
                "non convallis class ac. Quisque fusce vestibulum. Nunc nulla augue quis id ac. Nibh ante "+
                "vitae. Fusce sem eleifend. Cras tincidunt cras eleifend netus ligula nibh blandit " +
                "tincidunt pellentesque quam varius eget erat molestie";
    }
}
