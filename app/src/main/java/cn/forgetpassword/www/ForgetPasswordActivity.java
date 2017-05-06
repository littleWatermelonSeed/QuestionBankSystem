package cn.forgetpassword.www;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.database.www.QueryDB;
import cn.database.www.UpdateDB;

public class ForgetPasswordActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    private EditText editText_ID;
    private EditText editText_password;
    private EditText editText_againPassword;
    private EditText editText_question;
    private EditText editText_answer;
    private RadioGroup radioGroup;
    private Button button_find;

    private String userID;
    private String password;
    private String againPassword;
    private String question;
    private String answer;
    private String SorT = "学生";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        init();
    }

    private void init(){
        editText_ID = (EditText)findViewById(R.id.activity_forget_password_id);
        editText_password = (EditText)findViewById(R.id.activity_forget_password_newPS);
        editText_againPassword = (EditText)findViewById(R.id.activity_forget_password_againPS);
        editText_question = (EditText)findViewById(R.id.activity_forget_password_question);
        editText_answer = (EditText)findViewById(R.id.activity_forget_password_answer);
        radioGroup = (RadioGroup)findViewById(R.id.activity_forget_password_radiogroup);
        button_find = (Button)findViewById(R.id.activity_forget_password_findBack);

        radioGroup.setOnCheckedChangeListener(this);
        button_find.setOnClickListener(this);

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

        if(!isRight()){
            return;
        }

        UpdateDB updateDB = new UpdateDB(this);
        if(SorT.equals("学生")){
            updateDB.upDateStudentPS(password,userID);
        }else {
            updateDB.upDateTeacherPS(password,userID);
        }

        Toast.makeText(this,"密码重置成功！",Toast.LENGTH_SHORT).show();
        finish();
    }

    private void getMessage(){
        userID = editText_ID.getText().toString();
        password = editText_password.getText().toString();
        againPassword = editText_againPassword.getText().toString();
        question = editText_question.getText().toString();
        answer = editText_answer.getText().toString();
    }

    private boolean hasEmpty(){
        if(userID.equals("")||password.equals("")||againPassword.equals("")||question.equals("")||
                answer.equals("")){
            Toast.makeText(this,"所有项都为必填项！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean getIDandPasswordLen(){
        if(password.length() < 7){
            Toast.makeText(this,"密码长度都不能小于7位",Toast.LENGTH_SHORT).show();
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

    private boolean isRight(){
        QueryDB queryDB = new QueryDB(this);

        Cursor cursor;
        if(SorT.equals("学生")){
            cursor = queryDB.queryStudentByThree(userID,question,answer);
        }else {
            cursor = queryDB.queryTeacherByThree(userID,question,answer);
        }

        if(!cursor.moveToFirst()){
            Toast.makeText(this,"你输入的ID或密保不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.activity_forget_password_radiobutton1:
                SorT = "学生";
                break;
            case R.id.activity_forget_password_radiobutton2:
                SorT = "老师";
                break;
        }
    }
}
