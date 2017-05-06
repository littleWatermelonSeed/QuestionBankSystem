package cn.myadapter.www;

/**
 * Created by 123 on 2017/3/21.
 */

public class QueryStudentTopicBean {

    public String topicClassName;
    public String topicNum;
    public String studentName;
    public String studentID;

    public QueryStudentTopicBean(String topicClassName, String topicNum, String studentName, String studentID) {
        this.topicClassName = topicClassName;
        this.topicNum = topicNum;
        this.studentName = studentName;
        this.studentID = studentID;
    }
}
