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
 * Created by 123 on 2017/3/22.
 */

public class ParticularMessageAdapter extends BaseAdapter {

    private Context context;
    private List<ParticularMessageBean> list;
    private LayoutInflater inflater;

    public ParticularMessageAdapter(Context context, List<ParticularMessageBean> list) {
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

        ViewHoldr viewHoldr;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.adapter_particular_record,null);
            viewHoldr = new ViewHoldr();
            viewHoldr.textView_content = (TextView)convertView.findViewById(R.id.adapter_particular_record_content);
            viewHoldr.textView_rightAnswer = (TextView)convertView.findViewById(R.id.adapter_particular_record_rightanswer);
            viewHoldr.textView_result = (TextView)convertView.findViewById(R.id.adapter_particular_record_result);
            convertView.setTag(viewHoldr);
        }else {
            viewHoldr = (ViewHoldr)convertView.getTag();
        }
        ParticularMessageBean bean = list.get(position);
        viewHoldr.textView_content.setText(bean.content);
        viewHoldr.textView_rightAnswer.setText(bean.rightAnswer);
        viewHoldr.textView_result.setText(bean.result);

        if(bean.result.equals("正确")){
            viewHoldr.textView_result.setTextColor(context.getResources().getColor(R.color.green));
        }else {
            viewHoldr.textView_result.setTextColor(context.getResources().getColor(R.color.red));
        }

        return convertView;
    }

    class ViewHoldr{
        public TextView textView_content;
        public TextView textView_rightAnswer;
        public TextView textView_result;
    }

}
