package cn.cn.teacherfragment.teacherfragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.teacher.TeacherActivity;


public class BankManageFragment extends Fragment {
    private View view;
    private ImageView imageView_upload;
    private ImageView imageView_amend;
    private ImageView imageView_query;
    private ImageView imageView_delete;

    private imClick imClick = new imClick();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question_bank_manage    ,null);
        initWidget();
        return view;
    }

    private void initWidget(){
        imageView_upload = (ImageView)view.findViewById(R.id.fragment_manage_daoru  );
        imageView_amend = (ImageView)view.findViewById(R.id.fragment_manage_xiugai  );
        imageView_query = (ImageView)view.findViewById(R.id.fragment_manage_chakan    );
        imageView_delete = (ImageView)view.findViewById(R.id.fragment_manage_shanchu     );

        imageView_upload.setOnClickListener(imClick);
        imageView_amend.setOnClickListener(imClick);
        imageView_query.setOnClickListener(imClick);
        imageView_delete.setOnClickListener(imClick);

    }

    class imClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.fragment_manage_daoru:
                    intent = new Intent(getActivity(),BankUploadkActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fragment_manage_xiugai:
                    intent = new Intent(getActivity(),BankAmendActivity.class);
                    intent.putExtra("userID",TeacherActivity.getID());
                    startActivity(intent);
                    break;
                case R.id.fragment_manage_chakan:
                    intent = new Intent(getActivity(),BankQueryActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fragment_manage_shanchu:
                    intent = new Intent(getActivity(),BankDeleteActivity.class);
                    intent.putExtra("userID", TeacherActivity.getID());
                    startActivity(intent);
                    break;
            }
        }
    }

}
