package cn.student.www;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.database.www.QueryDB;
import cn.setting.www.SettingActivity;
import cn.share.www.DoCreate;
import cn.share.www.SyncData;
import cn.studentfragment.www.AnswerFragment;
import cn.studentfragment.www.InterflowFragment;
import cn.studentfragment.www.UploadingFragment;

/**
 * Created by 123 on 2017/3/17.
 */

public class StudentActivityListener {
    private Context context;
    private ImageView imageView_people;
    private ImageView imageView_gongneng;
    private ImageView imageView_forum;
    private ImageView imageView_web;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout_set;
    private LinearLayout linearLayout_create;
    private LinearLayout linearLayout_sync;
    private TextView textView_name;
    private String studentID;

    private imClickListener imClickListener = new imClickListener();
    private FragmentManager fm;
    private FragmentTransaction transaction;


    private AnswerFragment stuFunctionFragment;
    private UploadingFragment forumFragment;
    private InterflowFragment stuWebkFragment;

    public StudentActivityListener(Context context, ImageView imageView_people,
                                   ImageView imageView_gongneng,
                                   ImageView imageView_forum, ImageView imageView_web,
                                   DrawerLayout drawerLayout, TextView textView_name,
                                   LinearLayout linearLayout_set,LinearLayout linearLayout_create,
                                   LinearLayout linearLayout_sync) {
        this.context = context;
        this.imageView_people = imageView_people;
        this.imageView_gongneng = imageView_gongneng;
        this.imageView_forum = imageView_forum;
        this.imageView_web = imageView_web;
        this.drawerLayout = drawerLayout;
        this.textView_name = textView_name;
        this.linearLayout_set = linearLayout_set;
        this.linearLayout_create = linearLayout_create;
        this.linearLayout_sync = linearLayout_sync;

        fm = ((Activity)context).getFragmentManager();
        transaction = fm.beginTransaction();

        studentID = StudentActivity.getStudentID();
    }

    public void init(){
        setNameText();
        setFragment(R.id.activity_student_bottomlayout_image1,transaction);
        imageView_people.setOnClickListener(imClickListener);
        imageView_gongneng.setOnClickListener(imClickListener);
        imageView_forum.setOnClickListener(imClickListener);
        imageView_web.setOnClickListener(imClickListener);
        linearLayout_set.setOnClickListener(imClickListener);
        linearLayout_create.setOnClickListener(imClickListener);
        linearLayout_sync.setOnClickListener(imClickListener);
    }


    class imClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            FragmentTransaction transaction1 = fm.beginTransaction();
            hideFragment(transaction1);

            switch (v.getId()){
                case R.id.activity_student_gerenxinxi:
                    drawerLayout.openDrawer(Gravity.LEFT);
                    break;
                case R.id.activity_student_bottomlayout_image1:
                    setFragment(v.getId(),transaction1);
                    break;
                case R.id.activity_student_bottomlayout_image3:
                    setFragment(v.getId(),transaction1);
                    break;
                case R.id.activity_student_bottomlayout_image4:
                    setFragment(v.getId(),transaction1);
                    break;
                case R.id.activity_student_leftlayout_set:
                    Intent intent = new Intent(context, SettingActivity.class);
                    intent.putExtra("setting",((Activity)context).getLocalClassName());
                    context.startActivity(intent);
                    break;
                case R.id.activity_student_leftlayout_create:
                    saveData();
                    break;
                case R.id.activity_student_leftlayout_sync:
                    syncData();
                    break;
            }
        }
    }

    private void setFragment(int id,FragmentTransaction transaction){
        switch (id){
            case R.id.activity_student_bottomlayout_image1:
                if(stuFunctionFragment == null){
                    stuFunctionFragment = new AnswerFragment();
                    transaction.add(R.id.activity_student_body,stuFunctionFragment);
                }else {
                    transaction.show(stuFunctionFragment);
                }
                imageView_gongneng.setImageResource(R.drawable.dati);
                imageView_forum.setImageResource(R.drawable.shangchuan2);
                imageView_web.setImageResource(R.drawable.jiaoliu2);
                if(stuWebkFragment != null){
                    transaction.remove(stuWebkFragment);
                }
                break;
            case R.id.activity_student_bottomlayout_image3:
                if(forumFragment == null){
                    forumFragment = new UploadingFragment();
                    transaction.add(R.id.activity_student_body,forumFragment);
                }else {
                    transaction.show(forumFragment);
                }
                imageView_gongneng.setImageResource(R.drawable.dati2);
                imageView_forum.setImageResource(R.drawable.shangchuan);
                imageView_web.setImageResource(R.drawable.jiaoliu2);
                if(stuWebkFragment != null){
                    transaction.remove(stuWebkFragment);
                }
                break;
            case R.id.activity_student_bottomlayout_image4:
//                if(stuWebkFragment == null || stuWebkFragment.isRemoving()){
                    stuWebkFragment = new InterflowFragment();
                    transaction.add(R.id.activity_student_body,stuWebkFragment);
//                }else {
//                    transaction.show(stuWebkFragment);
//                }
                imageView_gongneng.setImageResource(R.drawable.dati2);
                imageView_forum.setImageResource(R.drawable.shangchuan2);
                imageView_web.setImageResource(R.drawable.jiaoliu);
                break;

        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction){
        if(stuFunctionFragment != null){
            transaction.hide(stuFunctionFragment);
        }
        if(forumFragment != null){
            transaction.hide(forumFragment);
        }
        if(stuWebkFragment != null){
            transaction.hide(stuWebkFragment);
        }

    }

    private void setNameText(){
        QueryDB queryDB = new QueryDB(context);
        Cursor cursor = queryDB.queryStudentByID(studentID);
        cursor.moveToFirst();
        textView_name.setText(cursor.getString(cursor.getColumnIndex("sName")));
        queryDB.closeDB();
    }

    private void saveData(){
        DoCreate doCreate = new DoCreate(context,studentID);
        doCreate.saveData();

    }

    private void syncData(){
        SyncData syncData = new SyncData(context);
        syncData.syncData();
    }

}
