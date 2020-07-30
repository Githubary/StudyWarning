package com.example.myapplication.mvp.view.teacherClassFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class checkResultActivity extends BaseActivity {



    @Override
    protected int initLayout() {
        return R.layout.activity_check_result;
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
