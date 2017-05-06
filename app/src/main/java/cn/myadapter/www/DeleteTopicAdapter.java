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

public class DeleteTopicAdapter extends BaseAdapter{

    private Context context;
    private List<DeleteTopicBean> list;
    private LayoutInflater inflater;
    private String userID;

    private DeleteTopicListener listener;

    public DeleteTopicAdapter(Context context, List<DeleteTopicBean> list,String userID) {
        this.context = context;
        this.list = list;
        this.userID = userID;
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
            convertView = inflater.inflate(R.layout.adapter_topicclass_delete,null);
            viewHolder.textView_topicClassName = (TextView)convertView.findViewById(R.id.adapter_topicclass_delete_text);
            viewHolder.button_delete = (Button)convertView.findViewById(R.id.adapter_topicclass_delete_delte);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        DeleteTopicBean bean = list.get(position);
        viewHolder.textView_topicClassName.setText(bean.className);

        listener = new DeleteTopicListener(context,bean.className,position,this,userID);
        viewHolder.button_delete.setOnClickListener(listener);
        return convertView;
    }

    class ViewHolder{
        public TextView textView_topicClassName;
        public Button button_delete;
    }

    public void refresh(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

}
