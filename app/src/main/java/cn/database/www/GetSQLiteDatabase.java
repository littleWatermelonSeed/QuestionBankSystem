package cn.database.www;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by 123 on 2017/3/16.
 */

public class GetSQLiteDatabase {
    private Context context;
    File dir_myData;
    File dir_otherDate;
    File dir;

    public GetSQLiteDatabase(Context context) {
        this.context = context;
    }

    private boolean judgeSDState() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return false;
        } else {
            return true;
        }
    }

    public SQLiteDatabase getDB(){
        SQLiteDatabase db = null;
        if(!judgeSDState()){
            return db;
        }
        dir = new File(Environment.getExternalStorageDirectory(),".1aQuestionBank");
        if(!dir.exists()){
            dir.mkdir();
        }

        dir_myData = new File(dir,"myData");
        dir_otherDate = new File(dir,"otherData");
        File dir_data = new File(dir,"data");

        if(!dir_data.exists()){
            dir_data.mkdir();
        }
        if(!dir_myData.exists()){
            dir_myData.mkdir();
        }
        if(!dir_otherDate.exists()){
            dir_otherDate.mkdir();
        }

        MyDBHelper myDBHelper = new MyDBHelper(context,
                dir_data.getAbsolutePath()+File.separator+"QuestionBankSystem.db",null,1);
        db = myDBHelper.getWritableDatabase();
        return db;
    }

}
