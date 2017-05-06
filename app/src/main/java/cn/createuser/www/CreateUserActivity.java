package cn.createuser.www;

import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.database.www.InsertDB;
import cn.database.www.QueryDB;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    private EditText editText_ID;
    private EditText editText_password;
    private EditText editText_againPassword;
    private EditText editText_question;
    private EditText editText_answer;
    private EditText editText_name;
    private RadioGroup radioGroup;
    private Button button_commit;

    private String userID;
    private String password;
    private String againPassword;
    private String question;
    private String answer;
    private String name;
    private String SorT = "学生";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        init();
    }

    private void init(){
        editText_ID = (EditText)findViewById(R.id.activity_create_user_id);
        editText_password = (EditText)findViewById(R.id.activity_create_user_password);
        editText_againPassword = (EditText)findViewById(R.id.activity_create_user_againPassword);
        editText_question = (EditText)findViewById(R.id.activity_create_user_question);
        editText_answer = (EditText)findViewById(R.id.activity_create_user_answer);
        editText_name = (EditText)findViewById(R.id.activity_create_user_name);
        radioGroup = (RadioGroup)findViewById(R.id.activity_create_user_radiogroup);
        button_commit = (Button)findViewById(R.id.activity_create_user_commit);

        radioGroup.setOnCheckedChangeListener(this);
        button_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getMessage();

        if(!hasEmpty()){
            return;
        }

        if(!getIDandPasswordLen()){
            return;
        }

        if(!passwordSame()){
            return;
        }

        if(!idIsExist()){
            return;
        }

        InsertDB insertDB = new InsertDB(this);

        String []values = {userID,password,name,SorT,question,answer};
        if(SorT.equals("学生")){
            insertDB.insertStudent(values);
        }else if(SorT.equals("老师")){
            insertDB.insertTeacher(values);
        }
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        finish();

    }

    private void getMessage(){
        userID = editText_ID.getText().toString();
        password = editText_password.getText().toString();
        againPassword = editText_againPassword.getText().toString();
        question = editText_question.getText().toString();
        answer = editText_answer.getText().toString();
        name = editText_name.getText().toString();
    }

    private boolean hasEmpty(){
        if(userID.equals("")||password.equals("")||againPassword.equals("")||question.equals("")||
                answer.equals("")||name.equals("")){
            Toast.makeText(this,"所有项都为必填项！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean getIDandPasswordLen(){
        if(userID.length() < 7 || password.length() < 7){
            Toast.makeText(this,"账号密码长度都不能小于7位",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean idIsExist(){
        QueryDB queryDB = new QueryDB(this);
        Cursor cursor_s = queryDB.queryStudentByID(userID);
        Cursor cursor_t = queryDB.queryTeacherByID(userID);
        if(cursor_s.moveToFirst() || cursor_t.moveToFirst()){
            Toast.makeText(this,"此ID已存在",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean passwordSame(){
        if(!password.equals(againPassword)){
            Toast.makeText(this,"两次输入的密码不同",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.activity_create_user_radiobutton1:
                SorT = "学生";
                break;
            case R.id.activity_create_user_radiobutton2:
                SorT = "老师";
                break;
        }
    }
}
