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

public class StudentRecordFragment extends Fragment {
    private View view;
    private ImageView imageView_roughly;
    private ImageView imageView_delete;

    private imClick imClick = new imClick();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_record,null);
        initWidget();
        return view;
    }

    private void initWidget(){
        imageView_roughly = (ImageView)view.findViewById(R.id.fragment_teacher_roughly);
        imageView_delete = (ImageView)view.findViewById(R.id.fragment_teacher_delete);

        imageView_roughly.setOnClickListener(imClick);
        imageView_delete.setOnClickListener(imClick);
    }

    class imClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.fragment_teacher_roughly:
                    intent = new Intent(getActivity(),StudentRoughlyActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("goto","R");
                    bundle1.putString("userID", TeacherActivity.getID());
                    intent.putExtra("bundle",bundle1);
                    startActivity(intent);
                    break;
                case R.id.fragment_teacher_delete:
                    intent = new Intent(getActivity(),StudentRoughlyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("goto","D");
                    bundle.putString("userID", TeacherActivity.getID());
                    intent.putExtra("bundle",bundle);
                    startActivity(intent);
                    break;
            }
        }
    }

}
