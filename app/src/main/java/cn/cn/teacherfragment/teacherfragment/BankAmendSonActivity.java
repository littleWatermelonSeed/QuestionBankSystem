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
import cn.myadapter.www.AmendTopicAdapter;
import cn.myadapter.www.AmendTopicBean;

public class BankAmendSonActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;

    private String topicClassName;
    private List<AmendTopicBean> list = new ArrayList<AmendTopicBean>();
    private AmendTopicBean bean;
    private AmendTopicAdapter adapter;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_amend_son);
        init();
    }

    private void init(){
        textView = (TextView)findViewById(R.id.activity_bank_amend_son_textview);
        listView = (ListView)findViewById(R.id.activity_bank_amend_son_listview);
        getMessage();
        setAdapter();
    }

    private void setAdapter(){
        getList();
        adapter = new AmendTopicAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName,userID);

        if(!cursor_topic.moveToFirst()){
            textView.setText("本题集还有没有题目哟~");
            return;
        }

        AmendTopicBean bean;
        cursor_topic.moveToPosition(-1);
        while (cursor_topic.moveToNext()){
            bean = new AmendTopicBean(cursor_topic.getString(cursor_topic.getColumnIndex("toID")),
                    cursor_topic.getString(cursor_topic.getColumnIndex("toContent")));
            list.add(bean);
        }
        queryDB.closeDB();
    }

    private void getMessage(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName");
        userID = bundle.getString("userID");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list.clear();
        adapter.notifyDataSetChanged();
        setAdapter();
    }

}
