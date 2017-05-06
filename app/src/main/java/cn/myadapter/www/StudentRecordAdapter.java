package cn.myadapter.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.List;

/**
 * Created by 123 on 2017/3/22.
 */

public class StudentRecordAdapter extends BaseAdapter {

    private Context context;
    private List<StudentRecordBean> list;
    private LayoutInflater inflater;

    private StudentRecordListener listener;

    public StudentRecordAdapter(Context context, List<StudentRecordBean> list) {
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
            convertView = inflater.inflate(R.layout.adapter_student_record,null);
            viewHolder.textView_studentName = (TextView)convertView.findViewById(R.id.adapter_student_record_studentName);
            viewHolder.layout = (RelativeLayout)convertView.findViewById(R.id.adapter_student_record_layout);
            viewHolder.textView_zhengquelv = (TextView)convertView.findViewById(R.id.adapter_student_record_zhengquelv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        StudentRecordBean bean = list.get(position);
        viewHolder.textView_studentName.setText(bean.studentName);
        viewHolder.textView_zhengquelv.setText(bean.zhengquelv);

        listener = new StudentRecordListener(context,bean.topicClassName,bean.usrID,bean.sID,bean.studentName,bean.zhengquelv);
        viewHolder.layout.setOnClickListener(listener);
        return convertView;
    }

    class ViewHolder{
        TextView textView_studentName;
        TextView textView_zhengquelv;
        RelativeLayout layout;
    }

}
