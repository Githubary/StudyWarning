package com.example.myapplication.mvp.view.myMessageFragmentFunction;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class my_wallet_Activity extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_my_wallet;
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
