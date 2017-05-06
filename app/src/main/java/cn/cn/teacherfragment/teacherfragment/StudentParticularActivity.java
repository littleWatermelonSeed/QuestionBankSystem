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
import cn.myadapter.www.ParticularMessageAdapter;
import cn.myadapter.www.ParticularMessageBean;

public class StudentParticularActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_sName;
    private TextView textView_zhengquelv;
    private ListView listView;

    private String sName;
    private String zhengquelv;
    private String topicClassName;
    private String userID;
    private String sID;

    private List<ParticularMessageBean> list = new ArrayList<ParticularMessageBean>();
    private ParticularMessageBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_student_particular);
        init();
    }

    private void init() {
        textView_title = (TextView) findViewById(R.id.activity_fragment_student_particular_title);
        textView_sName = (TextView) findViewById(R.id.activity_fragment_student_particular_sname);
        textView_zhengquelv = (TextView) findViewById(R.id.activity_fragment_student_particular_zhengquelv);
        listView = (ListView) findViewById(R.id.activity_fragment_student_particular_listview);
        getMessage();
        setMessage();
        setAdapter();
    }

    private void setAdapter() {
        getList();
        ParticularMessageAdapter adapter = new ParticularMessageAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void setMessage(){
        textView_title.setText(topicClassName);
        textView_sName.setText(sName);
        textView_zhengquelv.setText(zhengquelv);
    }

    private void getList() {
        String content;
        String rightAnswer;
        String result;
        String toID;

        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topic;
        Cursor cursor_topicClass;

        cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName, userID);
        while (cursor_topic.moveToNext()) {
            toID = cursor_topic.getString(cursor_topic.getColumnIndex("toID"));
            rightAnswer = cursor_topic.getString(cursor_topic.getColumnIndex("rAnswer"));
            content = cursor_topic.getString(cursor_topic.getColumnIndex("toContent"));

            cursor_topicClass = queryDB.queryRecordrResultByPrimaryKey(sID,toID);
            cursor_topicClass.moveToFirst();
            result = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("result"));
            bean = new ParticularMessageBean(content,rightAnswer,result);
            list.add(bean);
        }
        queryDB.closeDB();
        cursor_topic.close();
    }

    private void getMessage() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName", topicClassName);
        userID = bundle.getString("userID", userID);
        sID = bundle.getString("sID", sID);
        sName = bundle.getString("sName", sName);
        zhengquelv = bundle.getString("zhengquelv", zhengquelv);
    }

}
