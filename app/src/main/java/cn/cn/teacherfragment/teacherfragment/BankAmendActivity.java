package cn.cn.teacherfragment.teacherfragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.QueryDB;
import cn.myadapter.www.TopicClassAdapter;
import cn.myadapter.www.TopicClassBean;
import cn.teacher.TeacherActivity;

public class BankAmendActivity extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private List<TopicClassBean> list = new ArrayList<TopicClassBean>();
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_amend);
        init();
    }

    private void init(){
        textView = (TextView)findViewById(R.id.activity_bank_amend_textview);
        listView = (ListView)findViewById(R.id.activity_bank_amend_listview);
        userID = TeacherActivity.getID();
        getUserID();
        setAdapter();
    }

    private void setAdapter(){
        getList();
        TopicClassAdapter adapter = new TopicClassAdapter(this,list,"T");
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topicClass;
        Cursor cursor_topic;
        TopicClassBean bean;

        cursor_topicClass = queryDB.queryTopicClassByUserID(userID);
        if(!cursor_topicClass.moveToFirst()){
            textView.setText("你还没有录入题集哟~");
            return;
        }
        cursor_topicClass.moveToPosition(-1);
        while (cursor_topicClass.moveToNext()){
            String topicClassName;
            int n = 0;

            topicClassName = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("className"));
            cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName,userID);
            while (cursor_topic.moveToNext()){
                n++;
            }

            bean = new TopicClassBean(topicClassName,String.valueOf(n),userID);
            list.add(bean);
        }
        textView.setText("");
        queryDB.closeDB();
    }

    private void getUserID(){
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }

}
