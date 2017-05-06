package cn.share.www;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.InsertDB;
import cn.database.www.QueryDB;
import cn.function.www.GetTime;
import cn.myadapter.www.InterFlowThreeAdapter;
import cn.myadapter.www.InterFlowThreeBean;

public class InterFlowThreeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView_content;
    private TextView textView_rightAnswer;
    private TextView textView_noMessage;
    private TextView textView_title;
    private TextView textView_SorT;
    private TextView textView_userName;
    private ListView listView;
    private EditText editText;
    private Button button_send;

    private String content;
    private String toID;
    private String myID;
    private String myName;
    private String mySorT;
    private String SorT;
    private String userName;
    private String topicClassName;
    private String rightAnswer;

    private List<InterFlowThreeBean> list = new ArrayList<InterFlowThreeBean>();
    private InterFlowThreeBean bean;
    private InterFlowThreeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_flow_three);
        init();
        
    }

    private void init(){
        textView_content = (TextView)findViewById(R.id.activity_inter_flow_three_content);
        textView_rightAnswer = (TextView)findViewById(R.id.activity_inter_flow_three_rightAnswer);
        textView_noMessage = (TextView)findViewById(R.id.activity_inter_flow_three_noMessage);
        textView_title = (TextView)findViewById(R.id.activity_inter_flow_three_title);
        textView_SorT = (TextView)findViewById(R.id.activity_inter_flow_three_SorT);
        textView_userName = (TextView)findViewById(R.id.activity_inter_flow_three_userName);
        listView = (ListView)findViewById(R.id.activity_inter_flow_three_listView);
        editText = (EditText)findViewById(R.id.activity_inter_flow_three_message);
        button_send = (Button)findViewById(R.id.activity_inter_flow_three_send);

        button_send.setOnClickListener(this);

        getMessage();
        getMySorT();
        setText();
        setAdapter();
    }

    private void setText(){
        textView_content.setText(content);
        textView_rightAnswer.setText(rightAnswer);
        textView_title.setText("《"+topicClassName+"》");
        textView_userName.setText(userName);
        textView_SorT.setText(SorT);
        if(SorT.equals("老师")){
            textView_SorT.setTextColor(getResources().getColor(R.color.blue));
        }else {
            textView_SorT.setTextColor(getResources().getColor(R.color.green));
        }
    }

    private void getMessage(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        content = bundle.getString("content");
        toID = bundle.getString("toID");
        myID = bundle.getString("myID");
        myName = bundle.getString("myName");
        SorT = bundle.getString("SorT");
        topicClassName = bundle.getString("topicClassName");
        rightAnswer = bundle.getString("rightAnswer");
        userName = bundle.getString("userName");
    }

    private void setAdapter(){
        getList();
        adapter = new InterFlowThreeAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryMessageByToID(toID);
        if(!cursor.moveToFirst()){
            textView_noMessage.setText("还没有人发表留言哟");
            return;
        }

        String messaageContent;
        String userName;
        String time;
        String SorT;
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            messaageContent = cursor.getString(cursor.getColumnIndex("content"));
            userName = cursor.getString(cursor.getColumnIndex("userName"));
            time = cursor.getString(cursor.getColumnIndex("time"));
            SorT = cursor.getString(cursor.getColumnIndex("SorT"));
            bean = new InterFlowThreeBean(messaageContent,userName,time,SorT);
            list.add(bean);
        }

    }

    @Override
    public void onClick(View v) {
        String messageContent = editText.getText().toString();
        if(messageContent.equals("")){
            Toast.makeText(this,"留言不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        GetTime getTime = new GetTime();
        String time = getTime.getNowTime();

        String []values = {toID,myID,myName,time,messageContent,mySorT,topicClassName};
        InsertDB insertDB = new InsertDB(this);
        insertDB.insertMessage(values);

        bean = new InterFlowThreeBean(messageContent,myName,time,mySorT);
        list.add(bean);
        adapter.notifyDataSetChanged();
        textView_noMessage.setText("");
        editText.setText("");
        listView.setSelection(adapter.getCount() - 1);
    }

    private void getMySorT(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_student = queryDB.queryStudentByID(myID);

        if(cursor_student.moveToFirst()){
            mySorT = cursor_student.getString(cursor_student.getColumnIndex("className"));
            return;
        }

        Cursor cursor_teacher = queryDB.queryTeacherByID(myID);
        cursor_teacher.moveToFirst();
        mySorT = cursor_teacher.getString(cursor_student.getColumnIndex("className"));
    }

}
