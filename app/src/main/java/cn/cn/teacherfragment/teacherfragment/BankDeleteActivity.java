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
import cn.myadapter.www.DeleteTopicAdapter;
import cn.myadapter.www.DeleteTopicBean;

public class BankDeleteActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;

    private List<DeleteTopicBean> list = new ArrayList<DeleteTopicBean>();
    private DeleteTopicAdapter adapter;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_delete);
        init();
    }

    private void init(){
        textView = (TextView)findViewById(R.id.activty_bank_delete_textview);
        listView = (ListView)findViewById(R.id.activty_bank_delete_listview);
        getuserID();
        setAdapter();
    }

    private void setAdapter(){
        getList();
        adapter = new DeleteTopicAdapter(this,list,userID);
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_topicClass;
        DeleteTopicBean bean;

        cursor_topicClass = queryDB.queryTopicClassByUserID(userID);
        if(!cursor_topicClass.moveToFirst()){
            textView.setText("你还没有录入题集哟~");
            return;
        }
        cursor_topicClass.moveToPosition(-1);
        while (cursor_topicClass.moveToNext()){
            String topicClassName;
            topicClassName = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("className"));
            bean = new DeleteTopicBean(topicClassName);
            list.add(bean);
        }
        textView.setText("");
        queryDB.closeDB();
    }

    private void getuserID(){
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }

}
