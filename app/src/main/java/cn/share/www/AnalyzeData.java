package cn.share.www;

/**
 * Created by 123 on 2017/4/3.
 */

public class AnalyzeData {

    private GetTxtData getTxtData = new GetTxtData();

    public String [][]analyzeTopic(){

        if(!getTxtData.topicExist()){
            return null;
        }

        String s = getTxtData.getTopic();
        String []data1 = s.split("\\|&%&\\|");
        String [][]data2 = new String[data1.length][8];
        for(int i = 0;i < data1.length;i++){
            data2[i] = data1[i].split("~！@");
        }
        return data2;
    }

    public String [][]analyzeRecord(){

        if(!getTxtData.recordExist()){
            return null;
        }

        String s = getTxtData.getRecord();
        String []data1 = s.split("\\|&%&\\|");
        String [][]data2 = new String[data1.length][6];
        for(int i = 0;i < data1.length;i++){
            data2[i] = data1[i].split("~！@");
        }
        return data2;
    }

    public String [][]analyzeMessage(){

        if(!getTxtData.messageExist()){
            return null;
        }

        String s = getTxtData.getMessage();
        String []data1 = s.split("\\|&%&\\|");
        String [][]data2 = new String[data1.length][7];
        for(int i = 0;i < data1.length;i++){
            data2[i] = data1[i].split("~！@");
        }
        return data2;
    }

    public String [][]analyzeTopicClass(){

        if(!getTxtData.topicClassExist()){
            return null;
        }

        String s = getTxtData.getTopicClass();
        String []data1 = s.split("\\|&%&\\|");
        String [][]data2 = new String[data1.length][4];
        for(int i = 0;i < data1.length;i++){
            data2[i] = data1[i].split("~！@");
        }
        return data2;
    }

}
