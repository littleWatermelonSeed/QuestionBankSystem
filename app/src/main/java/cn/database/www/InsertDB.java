package cn.database.www;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 123 on 2017/3/18.
 */

public class InsertDB {
    private Context context;

    private GetSQLiteDatabase getSQLiteDatabase;
    private SQLiteDatabase sdb;
    private Cursor cursor;

    public InsertDB(Context context) {
        this.context = context;
    }

    public void insertTopic(String []values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"toID", "toContent","userID","userName","className","SorT","rAnswer","eAnswer"};
        ContentValues value = new ContentValues();
        for(int  i = 0; i < 8 ; i++){
            value.put(key[i],values[i]);
        }

        sdb.insert("Topic",null,value);
        sdb.close();
    }

    public void insertTopicClass(String []values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"userID","className","userName","SorT"};
        ContentValues value = new ContentValues();
        for(int  i = 0; i < 4 ; i++){
            value.put(key[i],values[i]);
        }

        sdb.insert("TopicClass",null,value);
        sdb.close();
    }

    public void insertRecord(String []result,String []toID,String []message){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"sID","className","sName","userID"};
        ContentValues value;
        for(int  i = 0; i < result.length; i++){
            value = new ContentValues();
            value.put("result",result[i]);
            value.put("toID",toID[i]);
            for (int a = 0;a < 4;a++){
                value.put(key[a],message[a]);
            }
            sdb.insert("Record",null,value);
        }

        sdb.close();
    }

    public void insertRecordAll(String []values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"sID","toID","result","className","sName","userID"};
        ContentValues value = new ContentValues();
        for(int  i = 0; i < 6; i++){
            value.put(key[i],values[i]);
        }

        sdb.insert("Record",null,value);
        sdb.close();
    }

    public void insertMessage(String []values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"toID","userID","userName","time","content","SorT","className"};
        ContentValues value = new ContentValues();;
        for(int  i = 0; i < key.length; i++){
            value.put(key[i],values[i]);
        }
        sdb.insert("Message",null,value);
        sdb.close();
    }

    public void insertStudent(String []values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"sID","sPassword","sName","className","question","answer"};
        ContentValues value = new ContentValues();;
        for(int  i = 0; i < key.length; i++){
            value.put(key[i],values[i]);
        }
        sdb.insert("Student",null,value);
        sdb.close();
    }

    public void insertTeacher(String []values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"tID","tPassword","tName","className","question","answer"};
        ContentValues value = new ContentValues();;
        for(int  i = 0; i < key.length; i++){
            value.put(key[i],values[i]);
        }
        sdb.insert("Teacher",null,value);
        sdb.close();
    }

}
