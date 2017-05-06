package cn.share.www;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.database.www.GetFileDirectory;

/**
 * Created by 123 on 2017/4/1.
 */

public class CreateTxt {
    private GetFileDirectory directory = new GetFileDirectory();
    private File dir;

    public CreateTxt(){
        dir = directory.getDir_myData();
    }

    public boolean saveDataTopic(String data){

        File shareData = new File(dir,"shareData_topic.txt");
        if(shareData.exists()){
            shareData.delete();
        }

        shareData(shareData,data);

        return true;
    }

    public boolean isTopicExist(){
        File shareData = new File(dir,"shareData_topic.txt");
        if(shareData.exists()){
            return true;
        }
        return false;
    }

    public boolean saveDataRecord(String data){

        File shareData = new File(dir,"shareData_Record.txt");
        if(shareData.exists()){
            shareData.delete();
        }

        shareData(shareData,data);

        return true;
    }

    public boolean saveDataMessage(String data){

        File shareData = new File(dir,"shareData_Message.txt");
        if(shareData.exists()){
            shareData.delete();
        }

        shareData(shareData,data);

        return true;
    }

    public boolean saveDataTopicClass(String data){

        File shareData = new File(dir,"shareData_topicClass.txt");
        if(shareData.exists()){
            shareData.delete();
        }

        shareData(shareData,data);

        return true;
    }

    private void shareData(File shareData,String data){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(shareData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter writer = new BufferedWriter(fileWriter);
        try {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
