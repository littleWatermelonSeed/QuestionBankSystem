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
 * Created by 123 on 2017/3/23.
 */

public class InterFlowTwoAdapter extends BaseAdapter {

    private Context context;
    private List<InterFlowTwoBean> list;
    private LayoutInflater inflater;

    private InterFlowTwoListener listener;

    public InterFlowTwoAdapter(Context context, List<InterFlowTwoBean> list) {
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
            convertView = inflater.inflate(R.layout.adapter_interflow_two,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.adapter_interflow_two_content);
            viewHolder.layout = (LinearLayout)convertView.findViewById(R.id.adapter_interflow_two_layout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        InterFlowTwoBean bean = list.get(position);
        viewHolder.textView.setText(bean.content);

        listener = new InterFlowTwoListener(context,bean);
        viewHolder.layout.setOnClickListener(listener);

        return convertView;
    }

    class ViewHolder{
        public TextView textView;
        public LinearLayout layout;
    }

}
