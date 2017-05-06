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
 * Created by 123 on 2017/3/19.
 */

public class TopicClassAdapter extends BaseAdapter {

    private List<TopicClassBean> list;
    private Context context;
    private LayoutInflater inflater;
    private String SorT;
    private String goTo = "";
    private TopicClassListener listener;

    public TopicClassAdapter( Context context, List<TopicClassBean> list,String sorT, String goTo) {
        this.list = list;
        this.context = context;
        SorT = sorT;
        this.goTo = goTo;
        inflater = LayoutInflater.from(context);
    }

    public TopicClassAdapter(Context context, List<TopicClassBean> list, String SorT) {
        this.list = list;
        this.context = context;
        this.SorT = SorT;
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
            convertView = inflater.inflate(R.layout.adapter_topicclass,null);
            viewHolder.textView_topicClassName = (TextView)convertView.findViewById(R.id.adapter_topicclass_name);
            viewHolder.textView_topicNum = (TextView)convertView.findViewById(R.id.adapter_topicclass_num);
            viewHolder.linearLayout = (LinearLayout)convertView.findViewById(R.id.adapter_topicclass_layout);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        TopicClassBean bean = list.get(position);
        viewHolder.textView_topicClassName.setText(bean.topicClassName);
        viewHolder.textView_topicNum.setText(bean.topicNum);

        listener = new TopicClassListener(context,SorT,bean.topicClassName,bean.userID,bean.topicNum,goTo);
        viewHolder.linearLayout.setOnClickListener(listener);
        return convertView;
    }

    class ViewHolder{
        public TextView textView_topicClassName;
        public TextView textView_topicNum;
        public LinearLayout linearLayout;
    }

}
