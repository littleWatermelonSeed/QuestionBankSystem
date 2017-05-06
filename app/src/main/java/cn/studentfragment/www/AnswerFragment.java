package cn.studentfragment.www;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sayhellototheworld.littlewatermelon.questionbanksystem.R;

public class AnswerFragment extends Fragment {

    private View view;

    private ImageView imageView_student;
    private ImageView imageView_teacher;

    private imClick imClick = new imClick();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_answer,null);
        init();
        return view;
    }

    private void init(){
        imageView_teacher = (ImageView)view.findViewById(R.id.fragment_answer_teacher);
        imageView_student = (ImageView)view.findViewById(R.id.fragment_answer_student);
        imageView_student.setOnClickListener(imClick);
        imageView_teacher.setOnClickListener(imClick);
    }

    class imClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.fragment_answer_student:
                    intent = new Intent(getActivity(),AnswerStudentTopicActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fragment_answer_teacher:
                    intent = new Intent(getActivity(),AnswerTeacherTopicActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
