package cn.studentfragment.www;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

import cn.cn.teacherfragment.teacherfragment.BankAmendActivity;
import cn.cn.teacherfragment.teacherfragment.BankDeleteActivity;
import cn.student.www.StudentActivity;

public class UploadingFragment extends Fragment implements View.OnClickListener{

    private View view;

    private ImageView imageView_upload;
    private ImageView imageView_amend;
    private ImageView imageView_delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_uploading,null);
        init();
        return view;
    }

    private void init(){
        imageView_upload = (ImageView)view.findViewById(R.id.fragment_upload_daoru);
        imageView_amend = (ImageView)view.findViewById(R.id.fragment_upload_xiugai);
        imageView_delete = (ImageView)view.findViewById(R.id.fragment_upload_delete);

        imageView_delete.setOnClickListener(this);
        imageView_amend.setOnClickListener(this);
        imageView_upload.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_upload_daoru:
                Intent intent1 = new Intent(getActivity(),UploadingUpActivity.class);
                startActivity(intent1);
                break;
            case R.id.fragment_upload_xiugai:
                Intent intent2 = new Intent(getActivity(),BankAmendActivity.class);
                intent2.putExtra("userID",StudentActivity.getStudentID());
                startActivity(intent2);
                break;
            case R.id.fragment_upload_delete:
                Intent intent3 = new Intent(getActivity(),BankDeleteActivity.class);
                intent3.putExtra("userID", StudentActivity.getStudentID());
                startActivity(intent3);
                break;
        }
    }
}
