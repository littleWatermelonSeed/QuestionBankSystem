package cn.share.www;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cn.database.www.GetFileDirectory;

/**
 * Created by 123 on 2017/4/3.
 */

public class GetTxtData {

    private GetFileDirectory getFileDirectory = new GetFileDirectory();
    private File dir;

    public GetTxtData() {
        dir = getFileDirectory.getDir_otherDate();
    }

    public String getTopic(){
        String data = getData("shareData_topic");
        return data;
    }

    public String getRecord(){
        String data = getData("shareData_Record");
        return data;
    }

    public String getMessage(){
        String data = getData("shareData_Message");
        return data;
    }

    public String getTopicClass(){
        String data = getData("shareData_topicClass");
        return data;
    }

    public boolean topicExist(){
        File shareData = new File(dir,"shareData_topic.txt");
        if(shareData.exists()){
            return true;
        }
        return false;
    }

    public boolean recordExist(){
        File shareData = new File(dir,"shareData_Record.txt");
        if(shareData.exists()){
            return true;
        }
        return false;
    }

    public boolean messageExist(){
        File shareData = new File(dir,"shareData_Message.txt");
        if(shareData.exists()){
            return true;
        }
        return false;
    }

    public boolean topicClassExist(){
        File shareData = new File(dir,"shareData_topicClass.txt");
        if(shareData.exists()){
            return true;
        }
        return false;
    }

    private String getData(String txtName){
        File shareData = new File(dir,txtName+".txt");
        BufferedReader reader = null;
        String data = "";
        try {
            String ln = "";
            reader = new BufferedReader(new FileReader(shareData));
            while ((ln = reader.readLine())!=null){
                data = data + ln;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(shareData.exists()){
                shareData.delete();
            }

        }
        return data;
    }

}
