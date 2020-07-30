package com.example.myapplication.mvp.view.warningReason;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class studentWarningReasonActivity4 extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_student_warning_reason4;
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
