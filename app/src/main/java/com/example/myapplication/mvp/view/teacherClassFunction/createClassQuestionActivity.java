package com.example.myapplication.mvp.view.teacherClassFunction;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class createClassQuestionActivity extends BaseActivity {

    private TextView finish_create_homework;

    @Override
    protected int initLayout() {
        return R.layout.activity_create_class_question;
    }

    @Override
    protected void initView() {
        finish_create_homework=findViewById(R.id.finish_create_homework);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);


        finish_create_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(finishCreateHomeworkActivity.class);
            }
        });

    }


}
