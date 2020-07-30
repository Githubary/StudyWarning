package com.example.myapplication.mvp.view.classfunction;


import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

import java.util.Objects;

public class workpublishActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_workpublish;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);
//        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
    }


}
