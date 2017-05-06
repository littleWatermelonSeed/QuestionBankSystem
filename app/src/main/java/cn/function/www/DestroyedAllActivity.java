package cn.function.www;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2017/3/16.
 */

public class DestroyedAllActivity {
    private static List<Activity> list = new ArrayList<Activity>();

    private static DestroyedAllActivity destroyedAllActivity = null;

    private DestroyedAllActivity(){

    }

    public static DestroyedAllActivity getDestoryed(){
        if(destroyedAllActivity == null){
            destroyedAllActivity = new DestroyedAllActivity();
            return destroyedAllActivity;
        }else {
            return destroyedAllActivity;
        }
    }

    public void addActivity(Activity activity){
        list.add(activity);
    }

    public void destroyedActivity(){
        for (Activity activity:list){
            activity.finish();
        }
        list.clear();
    }
}
