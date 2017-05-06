package cn.myadapter.www;

/**
 * Created by 123 on 2017/3/23.
 */

public class InterFlowOneBean {
    public String userID;
    public String userName;
    public String topicClassName;
    public String topicNum;
    public String SorT;

    public InterFlowOneBean(String userID, String userName, String topicClassName, String SorT,
                            String topicNum) {
        this.userID = userID;
        this.userName = userName;
        this.topicClassName = topicClassName;
        this.SorT = SorT;
        this.topicNum = topicNum;
    }
}
