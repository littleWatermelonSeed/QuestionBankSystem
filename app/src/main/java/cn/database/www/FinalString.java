package cn.database.www;

/**
 * Created by 123 on 2017/3/16.
 */

public class FinalString {
    final String createStudent = "create table Student(" +
            "sID text primary key," +
            "sPassword text not null," +
            "sName text not null," +
            "className text," +
            "question text," +
            "answer text" +
            ")";

    final String createTeacher = "create table Teacher(" +
            "tID text primary key," +
            "tPassword text," +
            "tName text," +
            "className text," +
            "question text," +
            "answer text" +
            ")";

    final String createTopic = "create table Topic(" +
            "toID text primary key," +
            "toContent text," +
            "userID text," +
            "userName text," +
            "className text," +
            "SorT text," +
            "rAnswer text," +
            "eAnswer text" +
            ")";

    final String createRecord = "create table Record(" +
            "sID text ," +
            "toID text," +
            "result text," +
            "className text," +
            "sName text," +
            "userID text," +
            "primary key(sID,toID)" +
            ")";

    final String createMessage = "create table Message(" +
            "toID text ," +
            "userID text," +
            "userName text," +
            "time text," +
            "content text," +
            "SorT text," +
            "className text" +
            ")";

    final String getCreateTopicClass = "create table TopicClass(" +
            "userID text ," +
            "className text," +
            "userName text," +
            "SorT text," +
            "primary key(userID,className)" +
            ")";
}
