package com.example.myapplication.mvp.view.classfunction;


import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class teachProgramActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_teach_program;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(false);
        setState(false);
        super.onCreate(savedInstanceState);
    }
}
