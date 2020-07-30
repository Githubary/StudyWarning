package com.example.myapplication.mvp.view.QuetionAndHomework;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class teacherHomeworkActivity1 extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_teacher_homework1;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);
    }


}
