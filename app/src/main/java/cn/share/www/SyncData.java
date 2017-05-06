package cn.share.www;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import cn.database.www.InsertDB;
import cn.database.www.QueryDB;

/**
 * Created by 123 on 2017/4/3.
 */

public class SyncData {

    private Context context;
    private QueryDB queryDB;
    private InsertDB insertDB;
    private Cursor cursor;
    private AnalyzeData analyzeData = new AnalyzeData();

    public SyncData(Context context) {
        this.context = context;
        queryDB = new QueryDB(context);
        insertDB = new InsertDB(context);
    }

    public void syncData(){
        String s = "";

        if(!syncTopic()){
            s = s + "题库 ";
        }

        if(!syncRecord()){
            s = s + "答题记录 ";
        }

        if(!syncMessage()){
            s = s + "留言 ";
        }

        if(!syncTopicClass()){
            s = s + "题集名称 ";
        }

        if(s.equals("")){
            Toast.makeText(context,"文件同步成功",Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(context,s+"文件为空",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean syncTopic(){
        String [][]data = analyzeData.analyzeTopic();
        if(data == null){
            return false;
        }

        for(int i = 0;i < data.length;i++){
            cursor = queryDB.queryTopicByID(data[i][0]);
            if(cursor.moveToFirst()){
                continue;
            }
            insertDB.insertTopic(data[i]);
        }
        return true;
    }

    private boolean syncTopicClass(){
        String [][]data = analyzeData.analyzeTopicClass();
        if(data == null){
            return false;
        }

        for(int i = 0;i < data.length;i++){
            cursor = queryDB.queryTopicClassByuserIDAndClassName(data[i][0],data[i][1]);
            if(cursor.moveToFirst()){
                continue;
            }
            insertDB.insertTopicClass(data[i]);
        }
        return true;
    }

    private boolean syncRecord(){
        String [][]data = analyzeData.analyzeRecord();
        if(data == null){
            return false;
        }

        for(int i = 0;i < data.length;i++){
            cursor = queryDB.queryRecordByTwoID(data[i][0],data[i][1]);
            if(cursor.moveToFirst()){
                continue;
            }
            insertDB.insertRecordAll(data[i]);
        }
        return true;
    }

    private boolean syncMessage(){
        String [][]data = analyzeData.analyzeMessage();
        if(data == null){
            return false;
        }

        for(int i = 0;i < data.length;i++){
            cursor = queryDB.queryMessageByUserIDAndTime(data[i][1],data[i][3]);
            if(cursor.moveToFirst()){
                continue;
            }
            insertDB.insertMessage(data[i]);
        }
        return true;
    }

}
