package cn.share.www;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.database.www.QueryDB;
import cn.database.www.UpdateDB;

public class AmendTopicActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_content;
    private EditText editText_rAnswer;
    private EditText editText_eAnswer;
    private Button button_submit;

    private String toID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_topic);
        init();
        getToID();
    }

    private void init() {
        editText_content = (EditText) findViewById(R.id.activity_amend_topic_timu);
        editText_rAnswer = (EditText) findViewById(R.id.activity_amend_topic_ranswer);
        editText_eAnswer = (EditText) findViewById(R.id.activity_amend_topic_eanswer);
        button_submit = (Button) findViewById(R.id.activity_amend_topic_submit);
        button_submit.setOnClickListener(this);
        getToID();
        setEditText();
    }

    private void getToID() {
        Intent intent = getIntent();
        toID = intent.getStringExtra("toID");
    }

    private void setEditText() {
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryTopicByID(toID);
        Log.i("niyuanjie",toID+"");
        cursor.moveToFirst();
        editText_content.setText(cursor.getString(cursor.getColumnIndex("toContent")));
        editText_rAnswer.setText(cursor.getString(cursor.getColumnIndex("rAnswer")));
        editText_eAnswer.setText(cursor.getString(cursor.getColumnIndex("eAnswer")));
    }

    @Override
    public void onClick(View v) {
        if (editText_content.getText().toString().equals("") ||
                editText_rAnswer.getText().toString().equals("") ||
                editText_eAnswer.getText().toString().equals("")) {
            Toast.makeText(this,"有一项或多项为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        final String[] amendContent = {editText_content.getText().toString(),
                editText_rAnswer.getText().toString(),
                editText_eAnswer.getText().toString()};
        final UpdateDB updateDB = new UpdateDB(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改题目/答案");
        builder.setMessage("确定完成修改？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDB.upDateTopic(toID, amendContent);
                finish();
                Toast.makeText(AmendTopicActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
