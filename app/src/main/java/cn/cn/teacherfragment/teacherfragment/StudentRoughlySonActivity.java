package cn.cn.teacherfragment.teacherfragment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.database.www.QueryDB;
import cn.myadapter.www.StudentRecordAdapter;
import cn.myadapter.www.StudentRecordBean;

public class StudentRoughlySonActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;

    private List<String> list_sID = new ArrayList<String>();
    private double topicNum;
    private String topicClassName;
    private String userID;

    private List<StudentRecordBean> list_adapter = new ArrayList<StudentRecordBean>();
    private StudentRecordBean bean;
    private StudentRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_roughly_son);
        init();
    }

    private void init(){
        listView = (ListView)findViewById(R.id.activity_student_roughly_son_listview);
        textView = (TextView)findViewById(R.id.activity_student_roughly_son_textview);
        getMessage();
        textView.setText("《"+topicClassName+"》");
        setAdapter();
    }

    private void setAdapter(){
        if(!getListStudentID()){
            return;
        }
        Log.i("niyuanjie1",list_sID.size()+"");
        for(int i = 0;i < list_sID.size();i++){
            String sName = "";
            String zhengquelv;
            QueryDB queryDB = new QueryDB(this);
            Cursor cursor = queryDB.queryRecordrResult(list_sID.get(i),topicClassName,userID,"正确");
            double n = 0;
            while (cursor.moveToNext()){
                n++;
                sName = cursor.getString(cursor.getColumnIndex("sName"));
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.0000");
            zhengquelv = String.valueOf(Double.parseDouble(decimalFormat.format(n/topicNum))*100)+"%";
            Log.i("niyuanjie",zhengquelv+" "+sName+"/");
            bean = new StudentRecordBean(sName,zhengquelv,list_sID.get(i),userID,topicClassName);
            list_adapter.add(bean);
        }
        adapter = new StudentRecordAdapter(this,list_adapter);
        listView.setAdapter(adapter);
    }

    private Boolean getListStudentID(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryRecordrResultByUserIDandClassName(userID,topicClassName);
        if(!cursor.moveToFirst()){
            textView.setText("还没有学生作答哟~");
            Log.i("niyuanjie","getListStudentID_FALSE");
            return false;
        }
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            String s = cursor.getString(cursor.getColumnIndex("sID"));
            if(!list_sID.contains(s)){
                list_sID.add(s);
            }
        }

        queryDB.closeDB();
        cursor.close();
        return true;
    }

    private void getMessage(){
        Intent intent = getIntent();
        Bundle bundle =intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName");
        userID = bundle.getString("userID");
        topicNum = Double.parseDouble(bundle.getString("topicNum"));
    }

}
