package cn.database.www;

import android.os.Environment;

import java.io.File;

/**
 * Created by 123 on 2017/4/1.
 */

public class GetFileDirectory {

    File dir_myData;
    File dir_otherDate;
    File dir;

    public File getDir_myData(){

        dir = new File(Environment.getExternalStorageDirectory(),".1aQuestionBank");
        if(!dir.exists()){
            dir.mkdir();
        }

        dir_myData = new File(dir,"myData");

        if(!dir_myData.exists()){
            dir_myData.mkdir();
        }
        return dir_myData;

    }

    public File getDir_otherDate(){

        dir = new File(Environment.getExternalStorageDirectory(),"tencent");
        if(!dir.exists()){
            dir.mkdir();
        }

        dir_otherDate = new File(dir,"QQfile_recv");

        if(!dir_otherDate.exists()){
            dir_otherDate.mkdir();
        }
        return dir_otherDate;
    }

}
