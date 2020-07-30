package com.example.myapplication.mvp.view.warningReason;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class studentWarningReasonActivity1 extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_student_warning_reason1;
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
