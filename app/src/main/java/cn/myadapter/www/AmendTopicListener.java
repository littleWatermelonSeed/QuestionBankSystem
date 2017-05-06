package cn.myadapter.www;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.share.www.AmendTopicActivity;

/**
 * Created by 123 on 2017/3/19.
 */

public class AmendTopicListener implements View.OnClickListener {

    private String toID;
    private Context context;

    public AmendTopicListener( Context context,String toID) {
        this.toID = toID;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AmendTopicActivity.class);
        intent.putExtra("toID",toID);
        context.startActivity(intent);
    }


}
