package cn.cn.teacherfragment.teacherfragment;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.ArrayList;
import java.util.List;

import cn.database.www.QueryDB;
import cn.myadapter.www.InterFlowOneAdapter;
import cn.myadapter.www.InterFlowOneBean;
import cn.teacher.TeacherActivity;

public class QueryMessageFragment extends Fragment {

    private View view;
    private TextView textView;
    private ListView listView;

    private String myID;
    private String myName;

    private List<InterFlowOneBean> list = new ArrayList<InterFlowOneBean>();
    private InterFlowOneAdapter adapter;
    private InterFlowOneBean bean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_interflow,null);
        init();
        return view;
    }

    private void init(){
        textView = (TextView)view.findViewById(R.id.fragment_interflow_text);
        listView = (ListView)view.findViewById(R.id.fragment_interflow_listview);
        getMyMessage();
        setAdapter();
    }

    private void setAdapter(){
        getList();
        adapter = new InterFlowOneAdapter(getActivity(),list,myID,myName);
        listView.setAdapter(adapter);
    }

    private void getList(){
        QueryDB queryDB = new QueryDB(getActivity());
        Cursor cursor_topicClass;
        Cursor cursor_topic;

        String userID;
        String userName;
        String topicClassName;
        String SorT;

        cursor_topicClass = queryDB.queryTopicClassAll();
        if(!cursor_topicClass.moveToFirst()){
            textView.setText("你还没有录入题集哟~");
            return;
        }
        cursor_topicClass.moveToPosition(-1);
        while (cursor_topicClass.moveToNext()){
            int topicNum = 0;

            userID = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("userID"));
            userName = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("userName"));
            topicClassName = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("className"));
            SorT = cursor_topicClass.getString(cursor_topicClass.getColumnIndex("SorT"));

            cursor_topic = queryDB.queryTopicByClassNameAndUserID(topicClassName,userID);
            while (cursor_topic.moveToNext()){
                topicNum++;
            }

            bean = new InterFlowOneBean(userID,userName,topicClassName,SorT,String.valueOf(topicNum));
            list.add(bean);
        }
        textView.setText("");
        queryDB.closeDB();
    }

    private void getMyMessage(){
        myID = TeacherActivity.getID();
        QueryDB queryDB = new QueryDB(getActivity());
        Cursor cursor = queryDB.queryTeacherByID(myID);
        cursor.moveToFirst();
        myName = cursor.getString(cursor.getColumnIndex("tName"));
    }

}
