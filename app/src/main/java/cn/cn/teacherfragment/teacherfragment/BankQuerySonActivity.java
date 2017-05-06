package cn.cn.teacherfragment.teacherfragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.QueryDB;
import cn.myadapter.www.AnswerTopicAdapter;
import cn.myadapter.www.AnswerTopicBean;

public class BankQuerySonActivity extends AppCompatActivity {

    private ListView listView;

    private String topicClassName;
    private String studentID;

    private List<AnswerTopicBean> list = new ArrayList<AnswerTopicBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_query_son);
        init();
    }

    private void init(){
        listView = (ListView)findViewById(R.id.activity_bank_query_son_listview);
        getMessage();
        setAdapter();
    }

    private void setAdapter(){
        getList();
        AnswerTopicAdapter adapter = new AnswerTopicAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getList(){
        String content;
        String rightAnswer;

        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName, studentID);

        AnswerTopicBean bean;
        while (cursor_topic.moveToNext()){
            content = cursor_topic.getString(cursor_topic.getColumnIndex("toContent"));
            rightAnswer = cursor_topic.getString(cursor_topic.getColumnIndex("rAnswer"));
            bean = new AnswerTopicBean(content,rightAnswer);
            list.add(bean);
        }
    }

    private void getMessage(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName");
        studentID = bundle.getString("studentID");
    }

}
