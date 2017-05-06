package cn.myadapter.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.share.www.InterFlowTwoActivity;

/**
 * Created by 123 on 2017/3/23.
 */

public class InterFlowOneListener implements View.OnClickListener {

    private Context context;
    private String topicClassName;
    private String userID;
    private String userName;
    private String SorT;
    private String myID;
    private String myName;

    public InterFlowOneListener(Context context, String topicClassName, String userID, String userName,
                                String SorT,String myID,String myName) {
        this.context = context;
        this.topicClassName = topicClassName;
        this.userID = userID;
        this.userName = userName;
        this.SorT = SorT;
        this.myID = myID;
        this.myName = myName;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, InterFlowTwoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("topicClassName",topicClassName);
        bundle.putString("userID",userID);
        bundle.putString("userName",userName);
        bundle.putString("SorT",SorT);
        bundle.putString("myID",myID);
        bundle.putString("myName",myName);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }
}
