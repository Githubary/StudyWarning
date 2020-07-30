package com.example.myapplication.mvp.view.teacherClassFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class finishCreateHomeworkActivity extends BaseActivity {



    @Override
    protected int initLayout() {
        return R.layout.activity_finish_create_homework;
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
