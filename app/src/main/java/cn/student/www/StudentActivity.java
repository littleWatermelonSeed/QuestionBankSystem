package cn.student.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.function.www.DestroyedAllActivity;

public class StudentActivity extends AppCompatActivity {

    private ImageView imageView_people;
    private ImageView imageView_gongneng;
    private ImageView imageView_forum;
    private ImageView imageView_web;
    private DrawerLayout drawerLayout;
    private TextView textView_name;
    private LinearLayout linearLayout_set;
    private LinearLayout linearLayout_sync;
    private LinearLayout linearLayout_create;
    private static String studentID;

    private DestroyedAllActivity destroyedAllActivity;
    private StudentActivityListener studentActivityListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
    }

    private void init(){
        receiveID();
        destroyedAllActivity = DestroyedAllActivity.getDestoryed();
        destroyedAllActivity.addActivity(this);

        imageView_people = (ImageView)findViewById(R.id.activity_student_gerenxinxi);
        imageView_gongneng = (ImageView)findViewById(R.id.activity_student_bottomlayout_image1);
        imageView_forum = (ImageView)findViewById(R.id.activity_student_bottomlayout_image3);
        imageView_web = (ImageView)findViewById(R.id.activity_student_bottomlayout_image4);
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_student_drawerlayout);
        textView_name = (TextView)findViewById(R.id.activity_student_leftlayout_nametextview);
        linearLayout_set = (LinearLayout)findViewById(R.id.activity_student_leftlayout_set);
        linearLayout_create = (LinearLayout)findViewById(R.id.activity_student_leftlayout_create);
        linearLayout_sync = (LinearLayout)findViewById(R.id.activity_student_leftlayout_sync);

        studentActivityListener = new StudentActivityListener(StudentActivity.this,imageView_people,
                imageView_gongneng,imageView_forum,
                imageView_web,drawerLayout,textView_name,linearLayout_set,linearLayout_create,linearLayout_sync);
        studentActivityListener.init();
    }

    private void receiveID(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("login");
        studentID = bundle.getString("ID");
    }

    public static String getStudentID(){
        return studentID;
    }

}
