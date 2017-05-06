package cn.myadapter.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.share.www.InterFlowThreeActivity;

/**
 * Created by 123 on 2017/3/23.
 */

public class InterFlowTwoListener implements View.OnClickListener {

    private Context context;
    private InterFlowTwoBean bean;

    public InterFlowTwoListener(Context context, InterFlowTwoBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, InterFlowThreeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("content",bean.content);
        bundle.putString("toID",bean.toID);
        bundle.putString("myID",bean.myID);
        bundle.putString("myName",bean.myName);
        bundle.putString("SorT",bean.SorT);
        bundle.putString("topicClassName",bean.topicClassName);
        bundle.putString("rightAnswer",bean.rightAnswer);
        bundle.putString("userName",bean.userName);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

}
