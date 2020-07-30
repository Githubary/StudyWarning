package com.example.myapplication.mvp.view.myMessageFragmentFunction;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class my_set_Activity extends BaseActivity {


    /**在这里定义控件*/


    @Override
    protected int initLayout() {
        return R.layout.activity_my_set;
    }

    /**在这里初始化控件*/
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
