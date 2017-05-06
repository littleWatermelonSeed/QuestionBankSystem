package cn.login.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.createuser.www.CreateUserActivity;
import cn.database.www.QueryDB;
import cn.forgetpassword.www.ForgetPasswordActivity;
import cn.student.www.StudentActivity;
import cn.teacher.TeacherActivity;

/**
 * Created by 123 on 2017/3/17.
 */

public class LoginListener {
    private boolean remember = false;
    private EditText editText_ID, editText_password;
    private Intent intent;
    private Bundle bundle;
    private String whichGo = "student";
    private Context context;
    private String userID;
    private String userPassword;

    OnClickListener onClickListener = new OnClickListener();
    CheckBoxChangedListener checkBoxChangedListener = new CheckBoxChangedListener();
    RadioGroupChangedListener radioGroupChangedListener = new RadioGroupChangedListener();

    public LoginListener(Context context, EditText editText_ID, EditText editText_password) {
        this.context = context;
        this.editText_ID = editText_ID;
        this.editText_password = editText_password;
    }

    public void setOnClickListener(Button button) {
        button.setOnClickListener(onClickListener);
    }

    public void setOnClickListener(TextView textView) {
        textView.setOnClickListener(onClickListener);
    }

    public void setCheckBoxListener(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(checkBoxChangedListener);
    }

    public void setRadioGroupListener(RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(radioGroupChangedListener);
    }


    private void loginDo() {
        if (!verifyID()) {
            return;
        }
        QueryDB queryDB = new QueryDB(context);
        Cursor cursor;
        if (whichGo.equals("student")) {
            cursor = queryDB.queryStudentByID(userID);
            if (!cursor.moveToFirst()) {
                Toast.makeText(context, "账号或密码不正确！", Toast.LENGTH_SHORT).show();
                return;
            } else if (userPassword.equals(cursor.getString(cursor.getColumnIndex("sPassword")))) {
                intent.setClass(context, StudentActivity.class);
                bundle = new Bundle();
                bundle.putString("ID", userID);
                intent.putExtra("login", bundle);
                context.startActivity(intent);
                ((Activity) context).finish();
                if (remember) {
                    shared();
                }
            } else {
                Toast.makeText(context, "账号或密码不正确！", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (whichGo.equals("teacher")) {
            cursor = queryDB.queryTeacherByID(userID);
            if (!cursor.moveToFirst()) {
                Toast.makeText(context, "账号或密码不正确！", Toast.LENGTH_SHORT).show();
                return;
            } else if (userPassword.equals(cursor.getString(cursor.getColumnIndex("tPassword")))) {
                intent.setClass(context, TeacherActivity.class);
                bundle = new Bundle();
                bundle.putString("ID", userID);
                intent.putExtra("login", bundle);
                context.startActivity(intent);
                ((Activity) context).finish();
                if (remember) {
                    shared();
                }
            } else {
                Toast.makeText(context, "账号或密码不正确！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private boolean verifyID() {
        userID = editText_ID.getText().toString();
        userPassword = editText_password.getText().toString();

        if (userID.equals("") || userPassword.equals("")) {
            Toast.makeText(context, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    private void shared() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", userID);
        editor.putString("password", userPassword);
        editor.commit();
    }

    class OnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            intent = new Intent();
            switch (v.getId()) {
                case R.id.activity_login_denglu:
                    loginDo();
                    break;
                case R.id.activity_login_xinjian:
                    intent.setClass(context, CreateUserActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.activity_login_forget:
                    intent.setClass(context, ForgetPasswordActivity.class);
                    context.startActivity(intent);
                    break;
            }
        }
    }

    class CheckBoxChangedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                remember = true;
            } else {
                remember = false;
            }
        }
    }

    class RadioGroupChangedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.activity_login_student) {
                whichGo = "student";
            } else if (checkedId == R.id.activity_login_teacher) {
                whichGo = "teacher";
            }
        }
    }

}
