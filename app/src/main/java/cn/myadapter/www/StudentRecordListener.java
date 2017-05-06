package cn.myadapter.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.cn.teacherfragment.teacherfragment.StudentParticularActivity;

/**
 * Created by 123 on 2017/3/22.
 */

public class StudentRecordListener implements View.OnClickListener{

    private Context context;
    private String sName;
    private String zhengquelv;
    private String topicClassName;
    private String userID;
    private String sID;

    public StudentRecordListener(Context context, String topicClassName, String userID, String sID,String sName,
                                 String zhengquelv) {
        this.context = context;
        this.topicClassName = topicClassName;
        this.userID = userID;
        this.sID = sID;
        this.sName = sName;
        this.zhengquelv = zhengquelv;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, StudentParticularActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("topicClassName",topicClassName);
        bundle.putString("userID",userID);
        bundle.putString("sID",sID);
        bundle.putString("sName",sName);
        bundle.putString("zhengquelv",zhengquelv);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }
}
