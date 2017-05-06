package cn.share.www;

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
import cn.myadapter.www.InterFlowTwoAdapter;
import cn.myadapter.www.InterFlowTwoBean;

public class InterFlowTwoActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_SorT;
    private TextView textView_userName;
    private ListView listView;

    private String topicClassName;
    private String userID;
    private String userName;
    private String SorT;
    private String myID;
    private String myName;
    private String rightAnswer;
    private String toID;
    private String content;

    private List<InterFlowTwoBean> list = new ArrayList<InterFlowTwoBean>();
    private InterFlowTwoAdapter adapter;
    private InterFlowTwoBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_flow_two);
        init();
    }

    private void init(){
        textView_title = (TextView)findViewById(R.id.activity_inter_flow_two_titl);
        textView_SorT = (TextView)findViewById(R.id.activity_inter_flow_two_SorT);
        textView_userName = (TextView)findViewById(R.id.activity_inter_flow_two_userName);
        listView = (ListView)findViewById(R.id.activity_inter_flow_two_listview);

        getMeaaage();
        setText();
        setAdapter();
    }

    private void setText(){
        textView_title.setText("《"+topicClassName+"》");
        textView_SorT.setText(SorT);
        textView_userName.setText(userName);

        if (SorT.equals("老师")){
            textView_SorT.setTextColor(getResources().getColor(R.color.blue));
        }else if(SorT.equals("学生")){
            textView_SorT.setTextColor(getResources().getColor(R.color.green));
        }
    }

    private void getMeaaage(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName");
        userID = bundle.getString("userID");
        userName = bundle.getString("userName");
        SorT = bundle.getString("SorT");
        myID = bundle.getString("myID");
        myName = bundle.getString("myName");
    }

    private void setAdapter(){
        getList();
        adapter = new InterFlowTwoAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName,userID);

        while (cursor_topic.moveToNext()){
            rightAnswer = cursor_topic.getString(cursor_topic.getColumnIndex("rAnswer"));
            toID = cursor_topic.getString(cursor_topic.getColumnIndex("toID"));
            content = cursor_topic.getString(cursor_topic.getColumnIndex("toContent"));

            bean = new InterFlowTwoBean(content,myID,myName,SorT,topicClassName,rightAnswer,toID,userName);
            list.add(bean);
        }
    }

}
