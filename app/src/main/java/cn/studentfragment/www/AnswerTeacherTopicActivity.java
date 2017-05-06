package cn.studentfragment.www;

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
import cn.student.www.StudentActivity;

public class AnswerTeacherTopicActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;

    private List<TopicClassBean> list = new ArrayList<TopicClassBean>();
    TopicClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_teacher_topic);
        init();
    }

    private void init(){
        textView = (TextView)findViewById(R.id.activity_answer_teacher_topic_textview);
        listView = (ListView)findViewById(R.id.activity_answer_teacher_topic_listview);
        setAdapter();
    }

    private void setAdapter(){
        getList();
        adapter = new TopicClassAdapter(this,list,"S");
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topicClass;
        Cursor cursor_topic;
        Cursor cursor_record;
        TopicClassBean bean;

        cursor_topicClass = queryDB.queryTopicClassBySorT("老师");
        if(!cursor_topicClass.moveToFirst()){
            textView.setText("老师还没有录入题集哟~");
            return;
        }
        cursor_topicClass.moveToPosition(-1);
        while (cursor_topicClass.moveToNext()){
            String topicClassName;
            String sID = StudentActivity.getStudentID();
            String hasRecord = "未作答";
            String tName;
            String userID;
            int n = 0;

            tName = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("userName"));
            userID = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("userID"));
            topicClassName = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("className"));

            cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName,userID);
            while (cursor_topic.moveToNext()){
                n++;
            }

            cursor_record = queryDB.queryRecordByThree(sID,topicClassName,userID);
            if(cursor_record.moveToFirst()){
                hasRecord = "已作答";
            }

            bean = new TopicClassBean(topicClassName,String.valueOf(n)+"            发布老师："+tName
                    +"            是否作答："+hasRecord,userID);
            list.add(bean);
        }
        textView.setText("");
        queryDB.closeDB();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list.clear();
        adapter.notifyDataSetChanged();
        setAdapter();
    }
}
