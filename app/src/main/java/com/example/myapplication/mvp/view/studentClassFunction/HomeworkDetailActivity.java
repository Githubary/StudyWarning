package com.example.myapplication.mvp.view.studentClassFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class HomeworkDetailActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_homework_detail;
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
