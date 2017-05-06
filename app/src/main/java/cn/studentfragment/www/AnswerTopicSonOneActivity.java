package cn.studentfragment.www;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cn.database.www.InsertDB;
import cn.database.www.QueryDB;
import cn.student.www.StudentActivity;

public class AnswerTopicSonOneActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView textView_title;
    private TextView textView_num;
    private TextView textView_content;
    private RadioGroup radioGroup;
    private Button button_last;
    private Button button_next;
    private Button button_submit;

    private List<String> list_toID = new ArrayList<String>();
    private List<String> list_content = new ArrayList<String>();
    private List<String> list_rAnswer = new ArrayList<String>();
    private List<String> list_eAnswer = new ArrayList<String>();
    private RadioButton[] radioButtons;
    private String[] errorAnswer;
    private String[] result;
    private String[] toID;
    private Boolean[] hasAnswer;
    private String topicClassName;
    private String userID;
    private String sID;
    private String sName;
    private String[] chooseAnswer;
    private int topicNum = 0;
    private int nowTopicNum = 0;
    private String[] message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_topic_son_one);
        init();
    }

    private void init() {
        textView_title = (TextView) findViewById(R.id.activity_answer_topic_son_title);
        textView_num = (TextView) findViewById(R.id.activity_answer_topic_son_num);
        textView_content = (TextView) findViewById(R.id.activity_answer_topic_son_content);
        radioGroup = (RadioGroup) findViewById(R.id.activity_answer_topic_son_radiogroup);
        button_last = (Button) findViewById(R.id.activity_answer_topic_son_last);
        button_next = (Button) findViewById(R.id.activity_answer_topic_son_next);
        button_submit = (Button) findViewById(R.id.activity_answer_topic_son_submit);

        button_submit.setOnClickListener(this);
        button_next.setOnClickListener(this);
        button_last.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

        getPrimaryKey();
        getList();
        getStudentMessage();
        initTopic(0);

        textView_title.setText(topicClassName);
        message = new String[]{sID, topicClassName, sName, userID};
    }

    private void getPrimaryKey() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName");
        userID = bundle.getString("userID");
    }

    private void getList() {
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryTopicByClassNameAndUserID(topicClassName, userID);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            list_toID.add(cursor.getString(cursor.getColumnIndex("toID")));
            list_content.add(cursor.getString(cursor.getColumnIndex("toContent")));
            list_rAnswer.add(cursor.getString(cursor.getColumnIndex("rAnswer")));
            list_eAnswer.add(cursor.getString(cursor.getColumnIndex("eAnswer")));
            topicNum++;
        }
        cursor.close();
        queryDB.closeDB();
        toID = new String[topicNum];
        result = new String[topicNum];

        hasAnswer = new Boolean[topicNum];
        for (int i = 0; i < topicNum; i++) {
            hasAnswer[i] = false;
        }

        chooseAnswer = new String[topicNum];
        for (int i = 0; i < topicNum; i++) {
            chooseAnswer[i] = " ";
        }
    }

    private void getStudentMessage() {
        sID = StudentActivity.getStudentID();
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryStudentByID(sID);
        cursor.moveToFirst();
        sName = cursor.getString(cursor.getColumnIndex("sName"));
    }

    private void initTopic(int num) {
        nowTopicNum = num;
        radioGroup.removeAllViews();
        textView_num.setText(String.valueOf(num + 1));
        textView_content.setText(list_content.get(num));
        String []rdAnswer = randomAnswer(num);
        radioButtons = new RadioButton[rdAnswer.length];
        for (int i = 0; i < rdAnswer.length; i++) {
            radioButtons[i] = new RadioButton(this);
            radioButtons[i].setText(rdAnswer[i]);
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioGroup.addView(radioButtons[i], params);
            if (chooseAnswer[nowTopicNum].equals(rdAnswer[i])) {
                radioButtons[i].setChecked(true);
            }
        }
    }

    private String[] randomAnswer(int num){
        errorAnswer = analyzeErrorAnswer(list_eAnswer.get(num));
        String []rdAnswer = new String[errorAnswer.length + 1];
        for(int i = 0;i < errorAnswer.length;i++){
            rdAnswer[i] = errorAnswer[i];
        }
        rdAnswer[rdAnswer.length - 1] = list_rAnswer.get(num);

        String ex;
        int a;
        int b;
        int len = rdAnswer.length;
        Random rd = new Random();
        for(int i = 0;i < len;i++){
            a = rd.nextInt(len);
            b = rd.nextInt(len);
            ex = rdAnswer[a];
            rdAnswer[a] = rdAnswer[b];
            rdAnswer[b] = ex;
        }

        return rdAnswer;
    }

    private String[] analyzeErrorAnswer(String eAnswer) {
        eAnswer.trim();
        List<String> list = new ArrayList<String>();
        int index = eAnswer.indexOf(" ");
        String s;
        while (index != -1) {
            s = eAnswer.substring(0, index);
            if (!s.equals("")) {
                list.add(s);
            }
            eAnswer = eAnswer.substring(index + 1, eAnswer.length());
            index = eAnswer.indexOf(" ");
            eAnswer.trim();
        }
        if (!eAnswer.equals("")) {
            list.add(eAnswer);
        }

        String[] f = new String[list.size()];
        list.toArray(f);
        return f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_answer_topic_son_last:
                doLast();
                break;
            case R.id.activity_answer_topic_son_next:
                doNext();
                break;
            case R.id.activity_answer_topic_son_submit:
                doSubimt();
                break;
        }
    }

    private void doLast() {
        nowTopicNum--;
        if (nowTopicNum < 0) {
            final Toast toast = Toast.makeText(this, "前面米有题咯！", Toast.LENGTH_SHORT);
            toast.show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 100);
            nowTopicNum++;
            return;
        }
        initTopic(nowTopicNum);
    }

    private void doNext() {
        nowTopicNum++;
        if (nowTopicNum == topicNum) {
            final Toast toast = Toast.makeText(this, "后面米有题咯！", Toast.LENGTH_SHORT);
            toast.show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 100);
            nowTopicNum--;
            return;
        }
        initTopic(nowTopicNum);
    }

    private void doSubimt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提交答案");
        builder.setMessage("确定提交答案？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < topicNum; i++) {
                    if (!hasAnswer[i]) {
                        Toast.makeText(AnswerTopicSonOneActivity.this, "题还没答完，不能提交", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                InsertDB insertDB = new InsertDB(AnswerTopicSonOneActivity.this);
                insertDB.insertRecord(result, toID, message);
                Toast.makeText(AnswerTopicSonOneActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton radioButton = (RadioButton) findViewById(checkedId);
        String s = radioButton.getText().toString();

        chooseAnswer[nowTopicNum] = s;
        hasAnswer[nowTopicNum] = true;
        if (s.equals(list_rAnswer.get(nowTopicNum))) {
            result[nowTopicNum] = "正确";
        } else {
            result[nowTopicNum] = "错误";
        }

        toID[nowTopicNum] = list_toID.get(nowTopicNum);

        hasAnswer[nowTopicNum] = true;

    }

}
