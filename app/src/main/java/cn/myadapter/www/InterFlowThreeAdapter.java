package cn.myadapter.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import java.util.List;

/**
 * Created by 123 on 2017/3/24.
 */

public class InterFlowThreeAdapter extends BaseAdapter {

    private Context context;
    private List<InterFlowThreeBean> list;
    private LayoutInflater inflater;

    public InterFlowThreeAdapter(Context context, List<InterFlowThreeBean> list) {
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
            convertView = inflater.inflate(R.layout.adapter_interflow_three,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.adapter_interflow_three_image);
            viewHolder.textView_time = (TextView)convertView.findViewById(R.id.adapter_interflow_three_time);
            viewHolder.textView_content = (TextView)convertView.findViewById(R.id.adapter_interflow_three_content);
            viewHolder.textView_userName = (TextView)convertView.findViewById(R.id.adapter_interflow_three_userName);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        InterFlowThreeBean bean = list.get(position);
        viewHolder.textView_userName.setText(bean.userName);
        viewHolder.textView_time.setText(bean.time);
        viewHolder.textView_content.setText(bean.content);

        if(bean.SorT.equals("老师")){
            viewHolder.imageView.setImageResource(R.drawable.laoshi);
            viewHolder.textView_userName.setTextColor(context.getResources().getColor(R.color.blue));
        }else {
            viewHolder.imageView.setImageResource(R.drawable.xuesheng);
            viewHolder.textView_userName.setTextColor(context.getResources().getColor(R.color.green));
        }

       return convertView;
    }

    class ViewHolder{
        public TextView textView_userName;
        public TextView textView_time;
        public TextView textView_content;
        public ImageView imageView;
    }

}
