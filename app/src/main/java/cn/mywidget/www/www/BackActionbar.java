package cn.mywidget.www.www;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

/**
 * Created by 123 on 2017/3/16.
 */

public class BackActionbar extends LinearLayout{
    private ImageButton button_back;
    private ImageButton button_more;

    public BackActionbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.back_actionbar,this);

        button_back = (ImageButton)findViewById(R.id.back_actionbar_back);
//        button_more = (ImageButton)findViewById(R.id.back_actionbar_more);

        btOnClick b = new btOnClick();

        button_back.setOnClickListener(b);
//        button_more.setOnClickListener(b);
    }

    class btOnClick implements OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_actionbar_back:
                    titleBack();
                    break;
//                case  R.id.back_actionbar_more:
//                    titleMore();
//                    break;
            }
        }
    }

    public void titleBack(){
        ((Activity)getContext()).finish();
    }

    public void titleMore(){

    }
}
