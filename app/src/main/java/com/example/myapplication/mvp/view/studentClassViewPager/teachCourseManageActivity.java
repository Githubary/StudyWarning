package com.example.myapplication.mvp.view.studentClassViewPager;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.classfunction.teachProgramActivity;
import com.example.myapplication.mvp.view.classfunction.workCorrectionActivity;
import com.example.myapplication.mvp.view.classfunction.workpublishActivity;

public class teachCourseManageActivity extends BaseActivity implements View.OnClickListener{


    private TextView workpublish;
    private TextView checkingin;
    private TextView workcorrection;
    private TextView teachprogram;


    @Override
    protected int initLayout() {
        return R.layout.activity_teach_course_manage;
    }

    @Override
    protected void initView() {
        workpublish=findViewById(R.id.workpublish);
        checkingin=findViewById(R.id.checkingin);
        workcorrection=findViewById(R.id.workcorrection);
        teachprogram=findViewById(R.id.teachprogram);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        workpublish.setOnClickListener(this);
        checkingin.setOnClickListener(this);
        workcorrection.setOnClickListener(this);
        teachprogram.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.workpublish:
                changeActivity(workpublishActivity.class);
                break;
            case R.id.checkingin:
                break;
            case R.id.workcorrection:
                changeActivity(workCorrectionActivity.class);
                break;
            case R.id.teachprogram:
                 changeActivity(teachProgramActivity.class);
                break;
        }
    }
}
