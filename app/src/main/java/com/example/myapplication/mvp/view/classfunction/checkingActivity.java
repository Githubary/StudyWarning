package com.example.myapplication.mvp.view.classfunction;


import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class checkingActivity extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_checking;
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
