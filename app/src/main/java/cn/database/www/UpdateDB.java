package cn.database.www;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 123 on 2017/3/17.
 */

public class UpdateDB {
    private Context context;

    private GetSQLiteDatabase getSQLiteDatabase;
    private SQLiteDatabase sdb;
    private Cursor cursor;

    public UpdateDB(Context context) {
        this.context = context;
    }

    public void upDateTeacherPS(String newID,String ID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        ContentValues value = new ContentValues();
        value.put("tPassword",newID);

        sdb.update("Teacher",value,"tID="+ID,null);
        sdb.close();
    }

    public void upDateStudentPS(String newID,String ID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        ContentValues value = new ContentValues();
        value.put("sPassword",newID);

        sdb.update("Student",value,"sID="+ID,null);
        sdb.close();
    }

    public void upDateTopic(String toID,String[] values){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();

        String []key = {"toContent","rAnswer","eAnswer"};

        ContentValues value = new ContentValues();
        for(int i = 0;i < 3;i++){
            value.put(key[i],values[i]);
        }

        sdb.update("Topic",value,"toID="+toID,null);
        sdb.close();
    }

}
