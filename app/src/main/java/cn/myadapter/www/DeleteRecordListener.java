package cn.myadapter.www;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import cn.database.www.DeleteDB;

/**
 * Created by 123 on 2017/3/23.
 */

public class DeleteRecordListener implements View.OnClickListener{

    private Context context;
    private String sID;
    private String sName;
    private String topicClassName;
    private String userID;
    private int position;
    private DeleteRecordAdapter adapter;

    public DeleteRecordListener(Context context,String sID, String topicClassName, String userID,
                                DeleteRecordAdapter adapter,int position,String sName) {
        this.sID = sID;
        this.topicClassName = topicClassName;
        this.userID = userID;
        this.context = context;
        this.adapter = adapter;
        this.position = position;
        this.sName = sName;
    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除记录");
        builder.setMessage("确定删除："+sName+"《"+topicClassName+"》的答题记录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteDB deleteDB = new DeleteDB(context);
                deleteDB.deleteRecordOne(sID,topicClassName,userID);
                adapter.refresh(position);
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
