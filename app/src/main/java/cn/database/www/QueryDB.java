package cn.database.www;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 123 on 2017/3/17.
 */

public class QueryDB {
    private Context context;

    private GetSQLiteDatabase getSQLiteDatabase;
    private SQLiteDatabase sdb;
    private Cursor cursor;

    public QueryDB(Context context) {
        this.context = context;
    }

    public Cursor queryStudentByID(String ID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Student",null,"sID="+ID,null,null,null,null);
        return cursor;
    }

    public Cursor queryStudentByThree(String ID,String question,String answer){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Student",null,"sID="+ID+" and question="+"'"+question+"'"+
                " and answer="+"'"+answer+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryTeacherByID(String ID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Teacher",null,"tID="+ID,null,null,null,null);
        return cursor;
    }

    public Cursor queryTeacherByThree(String ID,String question,String answer){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Teacher",null,"tID="+ID+" and question="+"'"+question+"'"+
                " and answer="+"'"+answer+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicClassByPrimay(String className,String userID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("TopicClass",null,"userID="+userID+" and className="+"'"+className+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicClassByUserID(String userID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("TopicClass",null,"userID="+userID,null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicAll(){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Topic",null,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicByClassNameAndUserID(String className,String userID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Topic",null,"className="+"'"+className+"'"+"and userID="+userID,null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicByID(String ID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Topic",null,"toID="+ID,null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicByUserID(String ID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Topic",null,"userID="+ID,null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicClassAll(){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("TopicClass",null,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicClassBySorT(String SorT){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("TopicClass",null,"SorT="+"'"+SorT+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicClassByuserIDAndClassName(String className,String userID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("TopicClass",null,"userID="+"'"+userID+"'"+" and className="+"'"+className+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryTopicClassBySorTSon(String SorT,String sID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("TopicClass",null,"SorT="+"'"+SorT+"'"+" and userID!="+sID,null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordByThree(String sID,String topicClassName,String userID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,"className="+"'"+topicClassName+"'"+
                " and sID="+sID+" and userID="+userID,null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordrResult(String sID,String topicClassName,String userID,String result){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,"className="+"'"+topicClassName+"'"+
                " and sID="+sID+" and userID="+userID+" and result="+"'"+result+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordByToID(String toID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,"toID="+toID,null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordAll(){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordByTwoID(String sID,String toID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,"toID="+toID+" and sID="+sID,null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordrResultByPrimaryKey(String sID,String toID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,"sID="+sID+" and toID="+toID,null,null,null,null);
        return cursor;
    }

    public Cursor queryRecordrResultByUserIDandClassName(String userID,String className){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Record",null,"userID="+userID+" and className="+"'"+className+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryMessageByToID(String toID){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Message",null,"toID="+toID,null,null,null,null);
        return cursor;
    }

    public Cursor queryMessageAll(){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Message",null,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryMessageByUserIDAndTime(String userID,String time){
        getSQLiteDatabase = new GetSQLiteDatabase(context);
        sdb = getSQLiteDatabase.getDB();
        cursor = sdb.query("Message",null,"userID="+userID+" and time="+"'"+time+"'",null,null,null,null);
        return cursor;
    }

    public void closeDB(){
        if(sdb != null){
            sdb.close();
        }
    }

}
