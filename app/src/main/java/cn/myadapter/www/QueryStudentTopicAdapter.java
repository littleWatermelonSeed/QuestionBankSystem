package cn.myadapter.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.List;

/**
 * Created by 123 on 2017/3/21.
 */

public class QueryStudentTopicAdapter extends BaseAdapter {

    private Context context;
    private List<QueryStudentTopicBean> list;
    private LayoutInflater inflater;

    private QueryStudentTopicListener listener;

    public QueryStudentTopicAdapter(Context context, List<QueryStudentTopicBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_query_student_topic,null);
            viewHolder.textView_topicClass = (TextView)convertView.findViewById(R.id.adapter_query_studnet_topic_topicname);
            viewHolder.textView_num = (TextView)convertView.findViewById(R.id.adapter_query_studnet_topic_num);
            viewHolder.textView_studentName = (TextView)convertView.findViewById(R.id.adapter_query_studnet_topic_studentname);
            viewHolder.layout = (LinearLayout)convertView.findViewById(R.id.adapter_query_studnet_topic_layout);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        QueryStudentTopicBean bean = list.get(position);
        viewHolder.textView_topicClass.setText(bean.topicClassName);
        viewHolder.textView_num.setText(bean.topicNum);
        viewHolder.textView_studentName.setText(bean.studentName);

        listener = new QueryStudentTopicListener(context,bean.topicClassName,bean.studentID);
        viewHolder.layout.setOnClickListener(listener);

        return convertView;
    }

    class ViewHolder{
        public LinearLayout layout;
        public TextView textView_topicClass;
        public TextView textView_num;
        public TextView textView_studentName;
    }

}
