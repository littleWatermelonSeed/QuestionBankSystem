package cn.database.www;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by 123 on 2017/3/18.
 */

public class DeleteDB {
    private Context context;

    private GetSQLiteDatabase getSQLiteDatabase;
    private SQLiteDatabase sdb;

    public DeleteDB(Context context) {
        this.context = context;
    }

    public void deleteTopicGather(String topicClassName, String userID) {
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        sdb.delete("Topic", "className=" + "'" + topicClassName + "'" + " and userID=" + userID, null);
        closeDB();
    }

    public void deleteTopicClass(String topicClassName, String userID) {
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        sdb.delete("TopicClass", "className=" + "'" + topicClassName + "'" + " and userID=" + userID, null);
        closeDB();
    }

    public void deleteRecord(String topicClassName, String userID) {
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        sdb.delete("Record", "className=" + "'" + topicClassName + "'" + " and userID=" + userID, null);
        closeDB();
    }

    public void deleteRecordOne(String sID, String topicClassName, String userID) {
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        sdb.delete("Record", "className=" + "'" + topicClassName + "'" + " and userID=" + userID + " and sID=" + sID, null);
        closeDB();
    }

    public void deleteMessage(List<String> toID) {
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        for (int i = 0; i < toID.size(); i++) {
            sdb.delete("Message", "toID=" + toID.get(i), null);
        }
        closeDB();
    }

    private void closeDB() {
        if (sdb != null) {
            sdb.close();
        }
    }

}
