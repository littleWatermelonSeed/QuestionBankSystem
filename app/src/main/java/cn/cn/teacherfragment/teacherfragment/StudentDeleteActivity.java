package cn.cn.teacherfragment.teacherfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.database.www.DeleteDB;
import cn.database.www.QueryDB;
import cn.myadapter.www.DeleteRecordAdapter;
import cn.myadapter.www.DeleteRecordBean;

public class StudentDeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private ListView listView;
    private Button button;
    private TextView textView_title;

    private boolean hasStudent = true;
    private String topicClassName;
    private String userID;
    private double topicNum;
    private List<String> list_sID = new ArrayList<String>();
    private List<DeleteRecordBean> list_adapter= new ArrayList<DeleteRecordBean>();
    private DeleteRecordBean bean;
    private DeleteRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_student_delete);
        init();
    }

    private void init(){
        textView = (TextView)findViewById(R.id.activity_fragment_student_delete_textview);
        listView = (ListView)findViewById(R.id.activity_fragment_student_delete_listview);
        button = (Button)findViewById(R.id.activity_fragment_student_delete_deleteall);
        textView_title = (TextView)findViewById(R.id.activity_fragment_student_delete_title);

        button.setOnClickListener(this);

        getMessage();
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
            bean = new DeleteRecordBean(sName,zhengquelv,list_sID.get(i),topicClassName,userID);
            list_adapter.add(bean);
        }
        adapter = new DeleteRecordAdapter(this,list_adapter);
        listView.setAdapter(adapter);
    }

    private Boolean getListStudentID(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor = queryDB.queryRecordrResultByUserIDandClassName(userID,topicClassName);
        if(!cursor.moveToFirst()){
            textView.setText("还没有学生作答哟~");
            hasStudent = false;
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
        Bundle bundle = intent.getBundleExtra("bundle");
        topicClassName = bundle.getString("topicClassName");
        userID = bundle.getString("userID");
        topicNum = Double.parseDouble(bundle.getString("topicNum"));
        textView_title.setText("《"+topicClassName+"》");
    }

    @Override
    public void onClick(View v) {
        if(!hasStudent){
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除答题记录");
        builder.setMessage("确定删除题集：《"+topicClassName+"》所有学生的答题记录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteDB deleteDB = new DeleteDB(StudentDeleteActivity.this);
                deleteDB.deleteRecord(topicClassName,userID);
                list_adapter.clear();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}
