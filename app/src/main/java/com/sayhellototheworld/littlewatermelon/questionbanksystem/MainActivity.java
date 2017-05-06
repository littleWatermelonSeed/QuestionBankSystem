package com.sayhellototheworld.littlewatermelon.questionbanksystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import cn.database.www.GetSQLiteDatabase;
import cn.login.www.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(){
            @Override
            public void run() {
                createDB();
            }
        }.start();

        Timer timer = new Timer();
        myTimerTask timerTask = new myTimerTask();
        timer.schedule(timerTask,200);
    }

    class myTimerTask extends TimerTask {
        @Override
        public void run() {
            nextActivity();
        }
    }

    private void nextActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void createDB(){
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("isFirst",true);
        if(!isFirst) return;

        GetSQLiteDatabase getSQLiteDatabase = new GetSQLiteDatabase(this);
        SQLiteDatabase db = getSQLiteDatabase.getDB();
        insertDB(db);
        db.close();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirst",false);
        editor.commit();
    }

    private void insertDB(SQLiteDatabase db){
        ContentValues values1 = new ContentValues();
        values1.put("sID","111111");
        values1.put("sPassword","111111");
        values1.put("sName","张三");
        values1.put("className","学生");
        db.insert("Student",null,values1);

        ContentValues values2 = new ContentValues();
        values2.put("sID","222222");
        values2.put("sPassword","222222");
        values2.put("sName","李四");
        values2.put("className","学生");
        db.insert("Student",null,values2);

        ContentValues values3 = new ContentValues();
        values3.put("tID","11111");
        values3.put("tPassword","11111");
        values3.put("tName","习大大");
        values3.put("className","老师");
        db.insert("Teacher",null,values3);

        ContentValues values4 = new ContentValues();
        values4.put("tID","22222");
        values4.put("tPassword","22222");
        values4.put("tName","江泽");
        values4.put("className","老师");
        db.insert("Teacher",null,values4);
    }

}
