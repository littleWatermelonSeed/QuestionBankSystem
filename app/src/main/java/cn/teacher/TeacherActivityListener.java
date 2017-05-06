package cn.teacher;

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

import cn.cn.teacherfragment.teacherfragment.BankManageFragment;
import cn.cn.teacherfragment.teacherfragment.QueryMessageFragment;
import cn.cn.teacherfragment.teacherfragment.StudentRecordFragment;
import cn.database.www.QueryDB;
import cn.setting.www.SettingActivity;
import cn.share.www.DoCreate;
import cn.share.www.SyncData;

/**
 * Created by 123 on 2017/3/16.
 */

public class TeacherActivityListener {
    private Context context;
    private ImageView imageView_people;
    private ImageView imageView_manger;
    private ImageView imageView_messag;
    private ImageView imageView_forum;
    private DrawerLayout drawerLayout;
    private TextView textView_name;
    private LinearLayout linearLayout_set;
    private LinearLayout linearLayout_create;
    private LinearLayout linearLayout_sync;

    private MyClickListener myClickListener = new MyClickListener();
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private String tID;

    private BankManageFragment manageFragment;
    private StudentRecordFragment messageSentFragment;
    private QueryMessageFragment forumFragment;

    public TeacherActivityListener(Context context, ImageView imageView_people,
                                   ImageView imageView_manger, ImageView imageView_messag,
                                   ImageView imageView_forum, DrawerLayout drawerLayout, TextView textView_name,
                                   LinearLayout linearLayout_set, LinearLayout linearLayout_create,
                                   LinearLayout linearLayout_sync) {
        this.context = context;
        this.imageView_people = imageView_people;
        this.imageView_manger = imageView_manger;
        this.imageView_messag = imageView_messag;
        this.imageView_forum = imageView_forum;
        this.drawerLayout = drawerLayout;
        this.textView_name = textView_name;
        this.linearLayout_set = linearLayout_set;
        this.linearLayout_create = linearLayout_create;
        this.linearLayout_sync = linearLayout_sync;
        fm = ((Activity) context).getFragmentManager();
        transaction = fm.beginTransaction();
    }

    public void setWidgetListener() {
        imageView_people.setOnClickListener(myClickListener);
        imageView_manger.setOnClickListener(myClickListener);
        imageView_messag.setOnClickListener(myClickListener);
        imageView_forum.setOnClickListener(myClickListener);
        linearLayout_set.setOnClickListener(myClickListener);
        linearLayout_create.setOnClickListener(myClickListener);
        linearLayout_sync.setOnClickListener(myClickListener);
        setFragment(R.id.activity_manager_bottomlayout_image1, transaction);
        setNameText();
    }

    class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction1 = fm.beginTransaction();
            hideFragment(transaction1);
            switch (v.getId()) {
                case R.id.activity_manager_gerenxinxi:
                    drawerLayout.openDrawer(Gravity.LEFT);
                    break;
                case R.id.activity_manager_bottomlayout_image1:
                    setFragment(v.getId(), transaction1);
                    break;
                case R.id.activity_manager_bottomlayout_image2:
                    setFragment(v.getId(), transaction1);
                    break;
                case R.id.activity_manager_bottomlayout_image3:
                    setFragment(v.getId(), transaction1);
                    break;
                case R.id.activity_manager_leftlayout_set:
                    Intent intent = new Intent(context, SettingActivity.class);
                    intent.putExtra("setting", ((Activity) context).getLocalClassName());
                    context.startActivity(intent);
                    break;
                case R.id.activity_manager_leftlayout_create:
                    saveData();
                    break;
                case R.id.activity_manager_leftlayout_sync:
                    syncData();
                    break;
            }
        }
    }

    private void setFragment(int id, FragmentTransaction transaction) {
        switch (id) {
            case R.id.activity_manager_bottomlayout_image1:
                if (manageFragment == null) {
                    manageFragment = new BankManageFragment();
                    transaction.add(R.id.activity_manager_body, manageFragment);
                } else {
                    transaction.show(manageFragment);
                }
                imageView_manger.setImageResource(R.drawable.guanli2);
                imageView_messag.setImageResource(R.drawable.xiaoxifabu1);
                imageView_forum.setImageResource(R.drawable.xiaoba1);
                if (forumFragment != null) {
                    transaction.remove(forumFragment);
                }
                break;
            case R.id.activity_manager_bottomlayout_image2:
                if (messageSentFragment == null) {
                    messageSentFragment = new StudentRecordFragment();
                    transaction.add(R.id.activity_manager_body, messageSentFragment);
                } else {
                    transaction.show(messageSentFragment);
                }
                imageView_manger.setImageResource(R.drawable.guanli1);
                imageView_messag.setImageResource(R.drawable.xiaoxifabu2);
                imageView_forum.setImageResource(R.drawable.xiaoba1);
                if (forumFragment != null) {
                    transaction.remove(forumFragment);
                }
                break;
            case R.id.activity_manager_bottomlayout_image3:
//                if(forumFragment == null){
                forumFragment = new QueryMessageFragment();
                transaction.add(R.id.activity_manager_body, forumFragment);
//                }else {
//                    transaction.show(forumFragment);
//                }
                imageView_manger.setImageResource(R.drawable.guanli1);
                imageView_messag.setImageResource(R.drawable.xiaoxifabu1);
                imageView_forum.setImageResource(R.drawable.xiaoba2);
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (manageFragment != null) {
            transaction.hide(manageFragment);
        }
        if (messageSentFragment != null) {
            transaction.hide(messageSentFragment);
        }
        if (forumFragment != null) {
            transaction.hide(forumFragment);
        }

    }

    public Context getContext() {
        return context;
    }

    public void setNameText() {
        String ID = TeacherActivity.getID();
        QueryDB queryDB = new QueryDB(context);
        Cursor cursor = queryDB.queryTeacherByID(ID);
        cursor.moveToFirst();
        textView_name.setText(cursor.getString(cursor.getColumnIndex("tName")));
        queryDB.closeDB();
    }

    private void saveData() {
        tID = TeacherActivity.getID();
        DoCreate doCreate = new DoCreate(context,tID);
        doCreate.saveData();
    }

    private void syncData() {
        SyncData syncData = new SyncData(context);
        syncData.syncData();
    }

}
