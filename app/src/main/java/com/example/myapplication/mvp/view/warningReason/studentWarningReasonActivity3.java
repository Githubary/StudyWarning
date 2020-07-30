package com.example.myapplication.mvp.view.warningReason;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.QuetionAndHomework.studentQuestion1Activity;
import com.example.myapplication.mvp.view.QuetionAndHomework.studentQuestion2Activity;
import com.example.myapplication.mvp.view.QuetionAndHomework.teacherHomeworkActivity1;
import com.example.myapplication.mvp.view.generalRevision.studentGeneralRevisionActivity;

public class studentWarningReasonActivity3 extends BaseActivity implements View.OnClickListener {


    private TextView question1;
    private TextView question2;
    private TextView question3;
    private TextView student_general_revision;
    private TextView homework1;
    private TextView changeState;
    private TextView watchppt;

    @Override
    protected int initLayout() {
        return R.layout.activity_student_warning_reason3;
    }

    @Override
    protected void initView() {
        question1=findViewById(R.id.question1);
        question2=findViewById(R.id.question2);
        question3=findViewById(R.id.question3);
        homework1=findViewById(R.id.homework1);
        watchppt=findViewById(R.id.watchppt);
        student_general_revision=findViewById(R.id.student_general_revision);
        changeState=findViewById(R.id.changeState2);
    }

    @Override
    protected void onResume() {
        SharedPreferences pref = getSharedPreferences("answerChangeState",MODE_PRIVATE);
        if(pref.getString("state","").equals("1")){
            changeState.setBackground(getResources().getDrawable(R.drawable.ic_safe_small));
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);

        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences("answerChangeState",MODE_PRIVATE);
        if(pref.getString("state","").equals("1")){
            changeState.setBackground(getResources().getDrawable(R.drawable.ic_safe_small));
        }


        question1.setOnClickListener(this);
        question2.setOnClickListener(this);
        question3.setOnClickListener(this);
        homework1.setOnClickListener(this);
        student_general_revision.setOnClickListener(this);
        watchppt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question1:
                changeActivity(studentQuestion1Activity.class);
                break;
            case R.id.question2:
                changeActivity(studentQuestion2Activity.class);
                break;
            case R.id.homework1:
                changeActivity(teacherHomeworkActivity1.class);
                break;
            case R.id.student_general_revision:
                changeActivity(studentGeneralRevisionActivity.class);
                break;
            case R.id.watchppt:
                changeActivity(PPTActivity.class);
                break;
        }
    }
}
