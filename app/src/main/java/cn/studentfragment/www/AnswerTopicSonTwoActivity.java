package cn.studentfragment.www;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.QueryDB;
import cn.myadapter.www.AnswerTopicAdapter;
import cn.myadapter.www.AnswerTopicBean;
import cn.student.www.StudentActivity;

public class AnswerTopicSonTwoActivity extends AppCompatActivity {

    private TextView textView_num;
    private TextView textView_rightNum;
    private TextView textView_errorNum;
    private ListView listView_rightTopic;
    private ListView listView_errorTopic;
    private TextView textView_rNo;
    private TextView textView_eNo;

    private String topicClassName;
    private String userID;
    private String sID;

    private int totalNum = 0;
    private int rNum = 0;
    private int eNum = 0;
    private List<AnswerTopicBean> list_right = new ArrayList<AnswerTopicBean>();
    private List<AnswerTopicBean> list_error = new ArrayList<AnswerTopicBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_topic_son_two);
        init();
    }

    private void init() {
        textView_num = (TextView) findViewById(R.id.activity_answer_topic_son_two_num);
        textView_rightNum = (TextView) findViewById(R.id.activity_answer_topic_son_two_right);
        textView_errorNum = (TextView) findViewById(R.id.activity_answer_topic_son_two_error);
        textView_rNo = (TextView) findViewById(R.id.activity_answer_topic_son_two_rNo);
        textView_eNo = (TextView) findViewById(R.id.activity_answer_topic_son_two_eNo);
        listView_rightTopic = (ListView) findViewById(R.id.activity_answer_topic_son_two_rightlistvie);
        listView_errorTopic = (ListView) findViewById(R.id.activity_answer_topic_son_two_errorlistvie);

        getPrimary();
        setNum();
        setAdapter();
    }

    private void getPrimary() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        topicClassName = bundle.getString("topicClassName");
        userID = bundle.getString("userID");
        sID = StudentActivity.getStudentID();
    }

    private void setNum() {
        getNum();
        textView_num.setText(String.valueOf(totalNum));
        textView_rightNum.setText(String.valueOf(rNum));
        textView_errorNum.setText(String.valueOf(eNum));
    }

    private void getNum() {
        QueryDB queryDB = new QueryDB(this);

        Cursor cursor_total = queryDB.queryTopicByClassNameAndUserID(topicClassName, userID);
        while (cursor_total.moveToNext()) {
            totalNum++;
        }

        Cursor cursor_right = queryDB.queryRecordrResult(sID, topicClassName, userID, "正确");
        while (cursor_right.moveToNext()) {
            rNum++;
        }

        Cursor cursor_error = queryDB.queryRecordrResult(sID, topicClassName, userID, "错误");
        while (cursor_error.moveToNext()) {
            eNum++;
        }
    }

    private void setAdapter() {
        getList();
        AnswerTopicAdapter adapter_r = new AnswerTopicAdapter(this, list_right);
        AnswerTopicAdapter adapter_e = new AnswerTopicAdapter(this, list_error);
        if(list_right.size() == 0){
            textView_rNo.setVisibility(View.VISIBLE);
        }
        if(list_error.size() == 0){
            textView_eNo.setVisibility(View.VISIBLE);
        }
        listView_rightTopic.setAdapter(adapter_r);
        listView_errorTopic.setAdapter(adapter_e);
    }

    private void getList() {
        String content;
        String rightAnswer;
        String result;
        String toID;

        AnswerTopicBean bean;
        QueryDB queryDB = new QueryDB(this);

        Cursor cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName, userID);
        while (cursor_topic.moveToNext()) {
            content = cursor_topic.getString(cursor_topic.getColumnIndex("toContent"));
            rightAnswer = cursor_topic.getString(cursor_topic.getColumnIndex("rAnswer"));
            toID = cursor_topic.getString(cursor_topic.getColumnIndex("toID"));

            Cursor cursor_record = queryDB.queryRecordrResultByPrimaryKey(sID, toID);
            cursor_record.moveToFirst();
            result = cursor_record.getString(cursor_record.getColumnIndex("result"));

            bean = new AnswerTopicBean("题目："+content, rightAnswer);
            if (result.equals("正确")) {
                list_right.add(bean);
            } else {
                list_error.add(bean);
            }
        }

        queryDB.closeDB();
        cursor_topic.close();

    }

}
