package cn.myadapter.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.List;

/**
 * Created by 123 on 2017/3/19.
 */

public class AmendTopicAdapter extends BaseAdapter {

    private Context context;
    private List<AmendTopicBean> list;
    private LayoutInflater inflater;

    private AmendTopicListener listener;

    public AmendTopicAdapter(Context context, List<AmendTopicBean> list) {
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
            convertView = inflater.inflate(R.layout.adapter_amend_topic,null);
            viewHolder.textView_content = (TextView)convertView.findViewById(R.id.adapter_amend_topic_textview);
            viewHolder.button_amend = (Button)convertView.findViewById(R.id.adapter_amend_topic_button);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        AmendTopicBean bean = list.get(position);

        viewHolder.textView_content.setText(bean.toContent);

        listener = new AmendTopicListener(context,bean.toID);
        viewHolder.button_amend.setOnClickListener(listener);
        return convertView;
    }

    class ViewHolder{
        public TextView textView_content;
        public Button button_amend;
    }

}
