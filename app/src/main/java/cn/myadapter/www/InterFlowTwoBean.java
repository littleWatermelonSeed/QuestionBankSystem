package cn.myadapter.www;

/**
 * Created by 123 on 2017/3/23.
 */

public class InterFlowTwoBean {

    public String content;
    public String toID;
    public String myID;
    public String myName;
    public String SorT;
    public String topicClassName;
    public String rightAnswer;
    public String userName;

    public InterFlowTwoBean(String content, String myID, String myName,String SorT,
                            String topicClassName,String rightAnswer, String toID,String userName) {
        this.toID = toID;
        this.myID = myID;
        this.myName = myName;
        this.SorT = SorT;
        this.topicClassName = topicClassName;
        this.content = content;
        this.rightAnswer = rightAnswer;
        this.userName = userName;
    }
}
