package cn.myadapter.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.List;

/**
 * Created by 123 on 2017/3/20.
 */

public class AnswerTopicAdapter extends BaseAdapter {

    private Context context;
    private List<AnswerTopicBean> list;
    private LayoutInflater inflater;

    public AnswerTopicAdapter(Context context, List<AnswerTopicBean> list) {
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
            convertView = inflater.inflate(R.layout.adapter_answer_topic,null);
            viewHolder.textView_content = (TextView)convertView.findViewById(R.id.adapter_answer_topic_content);
            viewHolder.textView_rightAnswer = (TextView)convertView.findViewById(R.id.adapter_answer_topic_rightanswer);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        AnswerTopicBean bean = list.get(position);
        viewHolder.textView_content.setText(bean.content);
        viewHolder.textView_rightAnswer.setText(bean.rightAnswer);
        return convertView;
    }

    class ViewHolder{
        public TextView textView_content;
        public TextView textView_rightAnswer;
    }

}
