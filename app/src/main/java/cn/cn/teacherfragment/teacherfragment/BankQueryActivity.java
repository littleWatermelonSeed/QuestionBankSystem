package cn.cn.teacherfragment.teacherfragment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.QueryDB;
import cn.myadapter.www.QueryStudentTopicAdapter;
import cn.myadapter.www.QueryStudentTopicBean;

public class BankQueryActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    private List<QueryStudentTopicBean> list = new ArrayList<QueryStudentTopicBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_query);
        init();
    }

    private void init(){
        listView = (ListView)findViewById(R.id.activity_bank_query_listview);
        textView = (TextView)findViewById(R.id.activity_bank_query_textview);
        setAdapter();
    }

    private void setAdapter(){
        getList();
        QueryStudentTopicAdapter adapter = new QueryStudentTopicAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getList(){
        String topicClassName;
        String topicNum;
        String studentName;
        String studentID;
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryTopicClassBySorT("学生");
        QueryStudentTopicBean bean;

        if(!cursor.moveToFirst()){
            textView.setText("还没有学生分享的题集哟~");
            return;
        }

        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            int n = 0;

            topicClassName = cursor.getString(cursor.getColumnIndex("className"));
            studentName = cursor.getString(cursor.getColumnIndex("userName"));
            studentID = cursor.getString(cursor.getColumnIndex("userID"));

            Cursor cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName,studentID);
            while (cursor_topic.moveToNext()){
                n++;
            }

            topicNum = String.valueOf(n);
            bean = new QueryStudentTopicBean(topicClassName,topicNum,studentName,studentID);
            list.add(bean);
        }
    }

}
