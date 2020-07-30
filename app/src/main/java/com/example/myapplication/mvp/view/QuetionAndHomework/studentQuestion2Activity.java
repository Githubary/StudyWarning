package com.example.myapplication.mvp.view.QuetionAndHomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class studentQuestion2Activity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_student_question2;
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
