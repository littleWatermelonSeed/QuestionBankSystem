package cn.database.www;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 123 on 2017/3/16.
 */

public class CreateTables {
    FinalString finalString = new FinalString();

    public void createTable(SQLiteDatabase db){
        db.execSQL(finalString.createStudent);
        db.execSQL(finalString.createTeacher);
        db.execSQL(finalString.createTopic);
        db.execSQL(finalString.createRecord);
        db.execSQL(finalString.createMessage);
        db.execSQL(finalString.getCreateTopicClass);
    }
}
