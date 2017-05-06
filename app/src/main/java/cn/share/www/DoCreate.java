package cn.share.www;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import cn.database.www.QueryDB;

/**
 * Created by 123 on 2017/4/3.
 */

public class DoCreate {

    private Context context;

    private CreateTxt createTxt;
    private QueryDB queryDB;
    private String[] toIDArr;

    public DoCreate(Context context, String userID) {
        this.context = context;

    }

    public void saveData() {
        createTxt = new CreateTxt();
        queryDB = new QueryDB(context);

        Cursor cursor = queryDB.queryTopicAll();
        if (!cursor.moveToFirst()) {
            Toast.makeText(context, "没有题集可以分享", Toast.LENGTH_SHORT).show();
            return;
        }

        if (createTxt.isTopicExist()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("共享文件已经存在，是否重新生成新文件覆盖之前的文件？");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    saveDataTopic();
                    saveDataRecord();
                    saveDataMessge();
                    saveDataTopicClass();
                    Toast.makeText(context, "题集文件生成成功", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            builder.setCancelable(false);
            builder.show();
        } else {
            saveDataTopic();
            saveDataRecord();
            saveDataMessge();
            saveDataTopicClass();
            Toast.makeText(context, "题集文件生成成功", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean saveDataTopic() {
        Cursor cursor = queryDB.queryTopicAll();
        int n = 0;
        String data = "";

        cursor.moveToPosition(-1);
        toIDArr = new String[cursor.getCount()];
        while (cursor.moveToNext()) {
            String toID = cursor.getString(cursor.getColumnIndex("toID"));
            String toContent = cursor.getString(cursor.getColumnIndex("toContent"));
            String userID = cursor.getString(cursor.getColumnIndex("userID"));
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            String className = cursor.getString(cursor.getColumnIndex("className"));
            String SorT = cursor.getString(cursor.getColumnIndex("SorT"));
            String rAnswer = cursor.getString(cursor.getColumnIndex("rAnswer"));
            String eAnswer = cursor.getString(cursor.getColumnIndex("eAnswer"));
            data = data + toID + "~！@" + toContent + "~！@" + userID + "~！@" + userName + "~！@" +
                    className + "~！@" + SorT + "~！@" + rAnswer + "~！@" + eAnswer + "|&%&|";
            toIDArr[n] = toID;
            n++;
        }

        createTxt.saveDataTopic(data);
        return true;

    }

    private boolean saveDataRecord() {
        Cursor cursor;
        String data = "";

        cursor = queryDB.queryRecordAll();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            String sID = cursor.getString(cursor.getColumnIndex("sID"));
            String toID = cursor.getString(cursor.getColumnIndex("toID"));
            String result = cursor.getString(cursor.getColumnIndex("result"));
            String className = cursor.getString(cursor.getColumnIndex("className"));
            String sName = cursor.getString(cursor.getColumnIndex("sName"));
            String userID = cursor.getString(cursor.getColumnIndex("userID"));
            data = data + sID + "~！@" + toID + "~！@" + result + "~！@" + className + "~！@" + sName +
                    "~！@" + userID + "|&%&|";
        }

        createTxt.saveDataRecord(data);
        return true;
    }

    private boolean saveDataMessge() {
        Cursor cursor;
        String data = "";

//        for (int i = 0; i < toIDArr.length; i++) {
//            cursor = queryDB.queryMessageByToID(toIDArr[i]);
//            if (!cursor.moveToFirst()) {
//                continue;
//            }

        cursor = queryDB.queryMessageAll();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            String toID = cursor.getString(cursor.getColumnIndex("toID"));
            String userID = cursor.getString(cursor.getColumnIndex("userID"));
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String SorT = cursor.getString(cursor.getColumnIndex("SorT"));
            String className = cursor.getString(cursor.getColumnIndex("className"));
            data = data + toID + "~！@" + userID + "~！@" + userName + "~！@" + time
                    + "~！@" + content + "~！@" + SorT + "~！@" +className
                    + "|&%&|";
        }

        createTxt.saveDataMessage(data);

        return true;
    }

    private boolean saveDataTopicClass() {
        Cursor cursor = queryDB.queryTopicClassAll();
        String data = "";

        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            String userID = cursor.getString(cursor.getColumnIndex("userID"));
            String className = cursor.getString(cursor.getColumnIndex("className"));
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            String SorT = cursor.getString(cursor.getColumnIndex("SorT"));
            data = data + userID + "~！@" + className + "~！@" + userName + "~！@" + SorT + "|&%&|";
        }

        createTxt.saveDataTopicClass(data);

        return true;
    }

}
