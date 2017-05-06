package cn.myadapter.www;

/**
 * Created by 123 on 2017/3/22.
 */

public class StudentRecordBean {

    public String studentName;
    public String zhengquelv;
    public String sID;
    public String usrID;
    public String topicClassName;

    public StudentRecordBean(String studentName, String zhengquelv,String sID,String usrID,String topicClassName) {
        this.studentName = studentName;
        this.zhengquelv = zhengquelv;
        this.sID = sID;
        this.usrID = usrID;
        this.topicClassName = topicClassName;
    }
}
