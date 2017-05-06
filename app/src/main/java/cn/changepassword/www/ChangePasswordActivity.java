package cn.changepassword.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.database.www.UpdateDB;
import cn.student.www.StudentActivity;
import cn.teacher.TeacherActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText editText_ps1, editText_ps2;
    private Button button_commit;
    private String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initWidget();
    }

    private void initWidget() {
        editText_ps1 = (EditText) findViewById(R.id.activity_change_password_password1);
        editText_ps2 = (EditText) findViewById(R.id.activity_change_password_password2);
        button_commit = (Button) findViewById(R.id.activity_change_password_commit);
        button_commit.setOnClickListener(new btClick());
        getActivityName();
    }

    private void getActivityName() {
        Intent intent = getIntent();
        activityName = intent.getStringExtra("activityName");
        Log.i("activityName",activityName);
    }

    class btClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            UpdateDB updateDB = new UpdateDB(ChangePasswordActivity.this);
            String ID;
            String ps1 = editText_ps1.getText().toString();
            String ps2 = editText_ps2.getText().toString();
            if (!ps1.equals(ps2)||ps1.equals("")) {
                Toast.makeText(ChangePasswordActivity.this,"输入的两个密码不同或密码为空！",Toast.LENGTH_LONG).show();
                return;
            } else {
                if (activityName.equals("cn.teacher.TeacherActivity")) {
                    ID = TeacherActivity.getID();
                    updateDB.upDateTeacherPS(ps1,ID);
                }else if(activityName.equals("cn.student.www.StudentActivity")){
                    StudentActivity studentActivity = new StudentActivity();
                    ID = studentActivity.getStudentID();
                    updateDB.upDateStudentPS(ps1,ID);
                }
            }
            Toast.makeText(ChangePasswordActivity.this,"密码设置成功！",Toast.LENGTH_LONG).show();
            editText_ps1.setText("");
            editText_ps2.setText("");
        }
    }
}
