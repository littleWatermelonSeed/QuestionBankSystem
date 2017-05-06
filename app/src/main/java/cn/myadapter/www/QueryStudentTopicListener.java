package cn.myadapter.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.cn.teacherfragment.teacherfragment.BankQuerySonActivity;

/**
 * Created by 123 on 2017/3/21.
 */

public class QueryStudentTopicListener implements View.OnClickListener{

    private Context context;
    private String topicClassName;
    private String studentID;

    public QueryStudentTopicListener(Context context, String topicClassName, String studentID) {
        this.context = context;
        this.topicClassName = topicClassName;
        this.studentID = studentID;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, BankQuerySonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("topicClassName",topicClassName);
        bundle.putString("studentID",studentID);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

}
