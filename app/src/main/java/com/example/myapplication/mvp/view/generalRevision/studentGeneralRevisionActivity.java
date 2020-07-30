package com.example.myapplication.mvp.view.generalRevision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.teacherClassFunction.createClassQuestionActivity;
import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;

public class studentGeneralRevisionActivity extends BaseActivity implements View.OnClickListener {

    private TextView anwser4;
    private TextView changeState;

    @Override
    protected int initLayout() {
        return R.layout.activity_student_general_revision;
    }

    @Override
    protected void initView() {
        anwser4=findViewById(R.id.answer4);
        changeState=findViewById(R.id.changeState);
    }

    @Override
    protected void onResume() {
        SharedPreferences pref = getSharedPreferences("answerChangeState",MODE_PRIVATE);
        System.out.println(pref.getString("state",""));
        if(pref.getString("state","").equals("1")){
            changeState.setBackground(getResources().getDrawable(R.drawable.ic_safe));
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);


        SharedPreferences pref = getSharedPreferences("answerChangeState",MODE_PRIVATE);
        System.out.println(pref.getString("state",""));
        if(pref.getString("state","").equals("1")){
            changeState.setBackground(getResources().getDrawable(R.drawable.ic_safe));
        }

        anwser4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(studentStartAnwserActivity.class);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
