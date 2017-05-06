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

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * Created by 123 on 2017/3/23.
 */

public class InterFlowOneAdapter extends BaseAdapter{

    private Context context;
    private List<InterFlowOneBean> list;
    private String myID;
    private String myName;
    private LayoutInflater inflater;

    private InterFlowOneListener listener;

    public InterFlowOneAdapter(Context context, List<InterFlowOneBean> list,String myID,String myName) {
        this.context = context;
        this.list = list;
        this.myID = myID;
        this.myName = myName;
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
            convertView = inflater.inflate(R.layout.adapter_interflow_one,null);
            viewHolder = new ViewHolder();
            viewHolder.textView_topicClassName = (TextView)convertView.findViewById(R.id.adapter_interflow_one_topicclassname);
            viewHolder.textView_SorT = (TextView)convertView.findViewById(R.id.adapter_interflow_one_SorT);
            viewHolder.textView_userName = (TextView)convertView.findViewById(R.id.adapter_interflow_one_userName);
            viewHolder.textView_topicNum = (TextView)convertView.findViewById(R.id.adapter_interflow_one_topicNum);
            viewHolder.layout = (LinearLayout)convertView.findViewById(R.id.adapter_interflow_one_layout);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        InterFlowOneBean bean = list.get(position);
        viewHolder.textView_topicClassName.setText(bean.topicClassName);
        viewHolder.textView_SorT.setText(bean.SorT);
        viewHolder.textView_userName.setText(bean.userName);
        viewHolder.textView_topicNum.setText(bean.topicNum);

        listener = new InterFlowOneListener(context,bean.topicClassName,bean.userID,bean.userName,
                bean.SorT,myID,myName);
        viewHolder.layout.setOnClickListener(listener);

        if(bean.SorT.equals("老师")){
            viewHolder.textView_SorT.setTextColor(context.getResources().getColor(R.color.blue));
        }else if(bean.SorT.equals("学生")){
            viewHolder.textView_SorT.setTextColor(context.getResources().getColor(R.color.green));
        }

        return convertView;
    }

    class ViewHolder{
        public TextView textView_topicClassName;
        public TextView textView_SorT;
        public TextView textView_userName;
        public TextView textView_topicNum;
        public LinearLayout layout;
    }

}
