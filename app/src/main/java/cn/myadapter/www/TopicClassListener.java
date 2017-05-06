package cn.myadapter.www;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import cn.cn.teacherfragment.teacherfragment.BankAmendSonActivity;
import cn.cn.teacherfragment.teacherfragment.StudentDeleteActivity;
import cn.cn.teacherfragment.teacherfragment.StudentRoughlySonActivity;
import cn.database.www.QueryDB;
import cn.student.www.StudentActivity;
import cn.studentfragment.www.AnswerTopicSonOneActivity;
import cn.studentfragment.www.AnswerTopicSonTwoActivity;

/**
 * Created by 123 on 2017/3/19.
 */

public class TopicClassListener implements View.OnClickListener {

    private Context context;
    private String SorT;
    private String topicClassName;
    private String userID;
    private String topicNum;
    private String goTo;

    public TopicClassListener(Context context, String SorT, String topicClassName, String userID,
                              String topicNum,String goTo) {
        this.context = context;
        this.SorT = SorT;
        this.topicClassName = topicClassName;
        this.userID = userID;
        this.topicNum =topicNum;
        this.goTo = goTo;
    }

    @Override
    public void onClick(View v) {
        switch (SorT) {
            case "S":
                doS();
                break;
            case "T":
                doT();
                break;
            case "R":
                if(goTo.equals("R")){
                    doR_R();
                }else if(goTo.equals("D")){
                    doR_D();
                }
                break;
        }
    }

    private void doT() {
        Intent intent = new Intent(context, BankAmendSonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("topicClassName", topicClassName);
        bundle.putString("userID", userID);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    private void doS() {
        String sID = StudentActivity.getStudentID();
        QueryDB queryDB = new QueryDB(context);
        Cursor cursor = queryDB.queryRecordByThree(sID, topicClassName, userID);
        Intent intent;

        if (cursor.moveToFirst()) {
            intent = new Intent(context, AnswerTopicSonTwoActivity.class);
        } else {
            intent = new Intent(context, AnswerTopicSonOneActivity.class);
        }

        Bundle bundle = new Bundle();
        bundle.putString("topicClassName", topicClassName);
        bundle.putString("userID", userID);
        intent.putExtra("bundle", bundle);

        context.startActivity(intent);
    }

    private void doR_R(){
        Intent intent = new Intent(context, StudentRoughlySonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("topicClassName", topicClassName);
        bundle.putString("userID", userID);
        bundle.putString("topicNum", topicNum);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    private void doR_D(){
        Intent intent = new Intent(context, StudentDeleteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("topicClassName", topicClassName);
        bundle.putString("userID", userID);
        bundle.putString("topicNum", topicNum);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }
}
