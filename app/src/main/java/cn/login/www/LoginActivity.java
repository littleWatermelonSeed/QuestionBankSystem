package cn.login.www;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editText_zhanghao;
    private EditText editText_miama;
    private RadioGroup radioGroup;
    private Button button_login;
    private TextView textView_xinjian;
    private CheckBox checkBox_jizhuwo;
    private TextView textView_forgrt;

    private String zhanghao;
    private String mima;

    private SharedPreferences sharedPreferences;
    private LoginListener loginListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
        getShare();
        setWidgetListener();
    }

    private void initWidget(){
        editText_zhanghao = (EditText)findViewById(R.id.activity_login_zhanghao);
        editText_miama = (EditText)findViewById(R.id.activity_login_mima);
        radioGroup = (RadioGroup)findViewById(R.id.activity_login_radiogroup);
        button_login = (Button)findViewById(R.id.activity_login_denglu);
        textView_xinjian = (TextView)findViewById(R.id.activity_login_xinjian);
        checkBox_jizhuwo = (CheckBox)findViewById(R.id.activity_login_jizhuwo);
        textView_forgrt = (TextView)findViewById(R.id.activity_login_forget);
    }

    private void setWidgetListener(){
        loginListener = new LoginListener(LoginActivity.this,editText_zhanghao,editText_miama);

        loginListener.setOnClickListener(button_login);
        loginListener.setOnClickListener(textView_forgrt);
        loginListener.setOnClickListener(textView_xinjian);
        loginListener.setCheckBoxListener(checkBox_jizhuwo);
        loginListener.setRadioGroupListener(radioGroup);
    }

    private void getShare(){
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        zhanghao = sharedPreferences.getString("ID","");
        mima = sharedPreferences.getString("password","");
        editText_zhanghao.setText(zhanghao);
        editText_miama.setText(mima);
    }

}
