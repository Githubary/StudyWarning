package com.example.myapplication.mvp.view.warningActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.generalRevision.studentGeneralRevisionActivity;
import com.example.myapplication.mvp.view.studentClassViewPager.studentClassMainActivity;
import com.example.myapplication.mvp.view.warningReason.studentWarningReasonActivity1;
import com.example.myapplication.mvp.view.warningReason.studentWarningReasonActivity3;
import com.example.myapplication.mvp.view.warningReason.studentWarningReasonActivity4;


public class student_warning_Activity extends BaseActivity implements View.OnClickListener {


    Toolbar toolbar;
    private RelativeLayout warning_relativeLayout;
    private RelativeLayout warning_relativeLayout2;
    private TextView enterClass;
    private TextView lable1;
    private TextView lable5;
    private TextView lable8;


    @Override
    protected int initLayout() {
        return R.layout.activity_student_warning2;
    }

    @Override
    protected void initView() {
        toolbar=findViewById(R.id.student_warning2_toolbar);
        enterClass=findViewById(R.id.enterClass);
        lable1=findViewById(R.id.lable1);
        lable5=findViewById(R.id.lable5);
        lable8=findViewById(R.id.lable8);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);



        enterClass.setOnClickListener(this);
        lable1.setOnClickListener(this);
        lable5.setOnClickListener(this);
        lable8.setOnClickListener(this);


//
//        warning_relativeLayout.setOnClickListener(this);
//        warning_relativeLayout2.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.student_general_revision:
                changeActivity(studentGeneralRevisionActivity.class);
                break;
            case R.id.enterClass:
                changeActivity(studentClassMainActivity.class);
                break;
            case R.id.lable1:
                changeActivity(studentWarningReasonActivity3.class);
                break;
            case R.id.lable5:
                changeActivity(studentWarningReasonActivity1.class);
                break;
            case R.id.lable8:
                changeActivity(studentWarningReasonActivity4.class);
                break;
        }
    }
}
