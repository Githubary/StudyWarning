package com.example.myapplication.mvp.view.warningActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.generalRevision.teacherGeneralRevisionActivity;
import com.example.myapplication.mvp.view.teacherClassViewPager.teacherClassMainActivity;
import com.example.myapplication.mvp.view.warningReason.teacherWarningReasonActivity1;
import com.example.myapplication.mvp.view.warningReason.teacherWarningReasonActivity2;
import com.example.myapplication.mvp.view.warningReason.teacherWarningReasonActivity3;


public class teacher_warning_Activity extends BaseActivity implements View.OnClickListener {


    Toolbar toolbar;
    private TextView teacher_enterClass;
    private TextView teacher_general_revision;
    private TextView lable1;
    private TextView lable4;
    private TextView lable5;

    @Override
    protected int initLayout() {
        return R.layout.activity_teacher_warning2;
    }

    @Override
    protected void initView() {
        toolbar=findViewById(R.id.teacher_warning2_toolbar);
        teacher_enterClass=findViewById(R.id.teacher_enterClass);
        teacher_general_revision=findViewById(R.id.teacher_general_revision);
        lable1=findViewById(R.id.lable1);
        lable4=findViewById(R.id.lable4);
        lable5=findViewById(R.id.lable5);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);
        teacher_enterClass.setOnClickListener(this);
        teacher_general_revision.setOnClickListener(this);
        lable1.setOnClickListener(this);
        lable4.setOnClickListener(this);
        lable5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.teacher_enterClass:
                    changeActivity(teacherClassMainActivity.class);
                    break;
                case R.id.teacher_general_revision:
                    changeActivity(teacherGeneralRevisionActivity.class);
                    break;
                case R.id.lable1:
                    changeActivity(teacherWarningReasonActivity1.class);
                    break;
                case R.id.lable4:
                    changeActivity(teacherWarningReasonActivity2.class);
                    break;
                case R.id.lable5:
                    changeActivity(teacherWarningReasonActivity3.class);
                    break;
            }
    }
}
