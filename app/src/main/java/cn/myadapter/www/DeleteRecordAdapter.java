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
 * Created by 123 on 2017/3/23.
 */

public class DeleteRecordAdapter extends BaseAdapter{

    private Context context;
    private List<DeleteRecordBean> list;
    private LayoutInflater inflater;

    private DeleteRecordListener listener;

    public DeleteRecordAdapter(Context context, List<DeleteRecordBean> list) {
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
            convertView = inflater.inflate(R.layout.adapter_delete_record,null);
            viewHolder = new ViewHolder();
            viewHolder.textView_sName = (TextView)convertView.findViewById(R.id.adapter_delete_record_sname);
            viewHolder.textView_zhengquelv = (TextView)convertView.findViewById(R.id.adapter_delete_record_zhengquelv);
            viewHolder.button_delete = (Button)convertView.findViewById(R.id.adapter_delete_record_button);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        DeleteRecordBean bean = list.get(position);
        viewHolder.textView_sName.setText(bean.sName);
        viewHolder.textView_zhengquelv.setText(bean.zhengquelv);

        listener = new DeleteRecordListener(context,bean.sID,bean.topicClassName,bean.userID,this,position,bean.sName);
        viewHolder.button_delete.setOnClickListener(listener);

        return convertView;
    }

    class ViewHolder{
        public TextView textView_sName;
        public TextView textView_zhengquelv;
        public Button button_delete;
    }

    public void refresh(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

}
