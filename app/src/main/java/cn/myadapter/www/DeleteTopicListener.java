package cn.myadapter.www;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.DeleteDB;
import cn.database.www.QueryDB;

/**
 * Created by 123 on 2017/3/19.
 */

public class DeleteTopicListener implements View.OnClickListener{

    private Context context;
    private String topicClassName;
    private int position;
    private DeleteTopicAdapter adapter;
    private String userID;

    public DeleteTopicListener(Context context,String topicClassName,int position,DeleteTopicAdapter adapter,
                               String userID) {
        this.context = context;
        this.topicClassName = topicClassName;
        this.position = position;
        this.adapter = adapter;
        this.userID = userID;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除题集");
        builder.setMessage("确定删除题集："+topicClassName+"?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteDB deleteDB = new DeleteDB(context);
                deleteMessage(deleteDB);
                deleteDB.deleteTopicClass(topicClassName,userID);
                deleteDB.deleteTopicGather(topicClassName,userID);
                deleteDB.deleteRecord(topicClassName,userID);
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

    private void deleteMessage(DeleteDB deleteDB){
        QueryDB queryDB = new QueryDB(context);
        Cursor cursor = queryDB.queryTopicByClassNameAndUserID(topicClassName,userID);
        Log.i("niyuanjie",topicClassName+" "+userID+"  "+cursor.moveToFirst());
        List<String> list_toID = new ArrayList<String>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            String toID = String.valueOf(cursor.getString(cursor.getColumnIndex("toID")));
            list_toID.add(toID);
            Log.i("niyuanjie",toID);
        }
        for(int i = 0;i < list_toID.size();i++){
            Log.i("niyuanjie",list_toID.get(i));
        }
        deleteDB.deleteMessage(list_toID);
    }

}
