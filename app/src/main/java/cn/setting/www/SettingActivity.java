package cn.setting.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.changepassword.www.ChangePasswordActivity;
import cn.function.www.DestroyedAllActivity;
import cn.login.www.LoginActivity;

public class SettingActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Button button;
    private String activityName;
    private DestroyedAllActivity destroyedAllActivity = DestroyedAllActivity.getDestoryed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init(){
        destroyedAllActivity.addActivity(this);
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_setting_password);
        button = (Button)findViewById(R.id.activity_setting_tuichu);

        btClickListener btClickListener = new btClickListener();
        relativeLayout.setOnClickListener(btClickListener);
        button.setOnClickListener(btClickListener);
        getActivityName();
    }

    class btClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()){
                case R.id.activity_setting_password:
                    intent.setClass(SettingActivity.this,ChangePasswordActivity.class);
                    intent.putExtra("activityName",activityName);
                    startActivity(intent);
                    break;
                case R.id.activity_setting_tuichu:
                    intent.setClass(SettingActivity.this,LoginActivity.class);
                    startActivity(intent);
                    destroyedAllActivity.destroyedActivity();
                    break;
            }
        }
    }

    private void getActivityName(){
        Intent intent = getIntent();
        activityName = intent.getStringExtra("setting");
    }

}
