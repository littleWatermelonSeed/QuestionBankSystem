package cn.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.function.www.DestroyedAllActivity;

public class TeacherActivity extends AppCompatActivity {

    private ImageView imageView_people;
    private ImageView imageView_manger;
    private ImageView imageView_messag;
    private ImageView imageView_forum;
    private DrawerLayout drawerLayout;
    private TextView textView_name;
    private LinearLayout linearLayout_create;
    private LinearLayout linearLayout_sync;
    private LinearLayout linearLayout_set;
    private static String userID;

    private TeacherActivityListener listener;
    private DestroyedAllActivity destroyedAllActivity = DestroyedAllActivity.getDestoryed();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        initWidget();
        getUserID();
        listener = new TeacherActivityListener(TeacherActivity.this,imageView_people,imageView_manger,
                imageView_messag,imageView_forum,drawerLayout,textView_name,linearLayout_set,linearLayout_create,linearLayout_sync);
        listener.setWidgetListener();
    }

    private void initWidget(){
        destroyedAllActivity.addActivity(this);
        imageView_people = (ImageView)findViewById(R.id.activity_manager_gerenxinxi);
        imageView_messag = (ImageView)findViewById(R.id.activity_manager_bottomlayout_image2);
        imageView_manger = (ImageView)findViewById(R.id.activity_manager_bottomlayout_image1);
        imageView_forum = (ImageView)findViewById(R.id.activity_manager_bottomlayout_image3);
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_manager_drawerlayout);
        textView_name = (TextView)findViewById(R.id.activity_manager_leftlayout_nametextview);
        linearLayout_set = (LinearLayout)findViewById(R.id.activity_manager_leftlayout_set);
        linearLayout_create = (LinearLayout)findViewById(R.id.activity_manager_leftlayout_create);
        linearLayout_sync = (LinearLayout)findViewById(R.id.activity_manager_leftlayout_sync);
    }

    private void getUserID(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("login");
        userID = bundle.getString("ID");
    }

    public static String getID(){
        return userID;
    }

}
