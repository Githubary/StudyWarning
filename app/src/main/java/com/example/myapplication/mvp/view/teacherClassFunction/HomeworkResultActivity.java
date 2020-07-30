package com.example.myapplication.mvp.view.teacherClassFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class HomeworkResultActivity extends BaseActivity {

    private RelativeLayout homework_student1;

    @Override
    protected int initLayout() {
        return R.layout.activity_homework_result;
    }

    @Override
    protected void initView() {
        homework_student1=findViewById(R.id.homework_student1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        homework_student1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(HomeworkGradeActivity.class);
            }
        });


    }


}
