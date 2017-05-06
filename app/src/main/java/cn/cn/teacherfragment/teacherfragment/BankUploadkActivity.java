package cn.cn.teacherfragment.teacherfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.database.www.InsertDB;
import cn.database.www.QueryDB;
import cn.function.www.GetTime;
import cn.teacher.TeacherActivity;

public class BankUploadkActivity extends AppCompatActivity {

    private EditText editText_topicName;
    private EditText editText_topic;
    private EditText editText_rightAnswer;
    private EditText editText_errorAnswer;

    private Button button_yet;
    private Button button_submit;
    private Button button_finish;

    private String topicName;
    private String topic;
    private String rightAnswer;
    private String errorAnswer;
    private String tID;

    private btClick btClick = new btClick();
    private GetTime getTime = new GetTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_uploadk);
        init();
    }

    private void init(){
        editText_errorAnswer = (EditText)findViewById(R.id.activity_bank_upload_cuowudaan);
        editText_rightAnswer = (EditText)findViewById(R.id.activity_bank_upload_zhengquedaan);
        editText_topic = (EditText)findViewById(R.id.activity_bank_upload_timu);
        editText_topicName = (EditText)findViewById(R.id.activity_bank_upload_topicname);

        button_submit = (Button)findViewById(R.id.activity_bank_upload_tijiaobenti);
        button_finish = (Button)findViewById(R.id.activity_bank_upload_wanchengdaoru);
        button_yet = (Button)findViewById(R.id.activity_bank_upload_chakanyijiaotimu);

        button_yet.setOnClickListener(btClick);
        button_finish.setOnClickListener(btClick);
        button_submit.setOnClickListener(btClick);

        tID = TeacherActivity.getID();
    }

    class btClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.activity_bank_upload_chakanyijiaotimu:
                    doYet();
                    break;
                case R.id.activity_bank_upload_tijiaobenti:
                    doSubmit();
                    break;
                case R.id.activity_bank_upload_wanchengdaoru:
                    doFinish();
                    break;
            }
        }
    }

    private void doYet(){
        if(editText_topicName.getText().toString().equals("")){
            Toast.makeText(this,"题集名称为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this,BankAmendSonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userID",tID);
        bundle.putString("topicClassName",editText_topicName.getText().toString());
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }

    private void doSubmit(){
        getStringValues();
        if(topicName.equals("")||topic.equals("")||rightAnswer.equals("")||errorAnswer.equals("")){
            Toast.makeText(this,"有一项或多项为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        trimString();
        insertTopicAndTopicClass();
    }

    private void doFinish(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("完成题目导入");
        builder.setMessage("确定完成本题集的题目导入并退出？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void getStringValues(){
        topicName = editText_topicName.getText().toString();
        topic = editText_topic.getText().toString();
        rightAnswer = editText_rightAnswer.getText().toString();
        errorAnswer = editText_errorAnswer.getText().toString();
    }

    private void trimString(){
        topicName.trim();
        topic.trim();
        rightAnswer.trim();
        errorAnswer.trim();
    }

    private void insertTopicAndTopicClass(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryTeacherByID(tID);
        Cursor cursor1;
        cursor.moveToFirst();
        String userName = cursor.getString(cursor.getColumnIndex("tName"));

        String time = getTime.getNowTimeSecond();
        String []values1 = {time+tID,topic,tID,userName,topicName,"老师",rightAnswer,errorAnswer};
        String []values2 = {tID,topicName,userName,"老师"};

        InsertDB insertDB = new InsertDB(this);

        insertDB.insertTopic(values1);
        cursor1 = queryDB.queryTopicClassByPrimay(topicName,tID);
        if(!cursor1.moveToFirst()){
            insertDB.insertTopicClass(values2);
        }

        clearEditText();
        Toast.makeText(this,"题目录入成功！",Toast.LENGTH_SHORT).show();
    }

    private void clearEditText(){
        editText_topic.setText("");
        editText_rightAnswer.setText("");
        editText_errorAnswer.setText("");
    }

}
