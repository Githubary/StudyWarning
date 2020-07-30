package com.example.myapplication.mvp.view.warningReason;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.QuetionAndHomework.teacherHomeworkActivity1;
import com.example.myapplication.mvp.view.QuetionAndHomework.teacherQuestionActivity1;
import com.example.myapplication.utils.Chart.PieChartManagger;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class teacherWarningReasonActivity1 extends BaseActivity implements View.OnClickListener{

    private PieChart pieChart_checkIn;
    private PieChart pieChart_question;
    private PieChart pieChart_homework;
    private LinearLayout questionTitle;
    private LinearLayout homeworkTitle;

    @Override
    protected int initLayout() {
        return R.layout.activity_teacher_warning_reason1;
    }

    @Override
    protected void initView() {
        pieChart_checkIn=findViewById(R.id.pieChart_checkIn);
        pieChart_question=findViewById(R.id.pieChart_question);
        pieChart_homework=findViewById(R.id.pieChart_homework);
        questionTitle=findViewById(R.id.question_title);
        homeworkTitle=findViewById(R.id.homework_title);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        questionTitle.setOnClickListener(this);
        homeworkTitle.setOnClickListener(this);

        showCheckInPieChart();
        showQuestionPieChart();
        showHomeworkPieChart();
    }



    private void showCheckInPieChart() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(1.0f, "请假"));
        yvals.add(new PieEntry(8.0f, "到课"));
        yvals.add(new PieEntry(1.0f, "缺勤"));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FFD700"));
        colors.add(Color.parseColor("#4EEE94"));
        colors.add(Color.parseColor("#FF6A6A"));

        PieChartManagger pieChartManagger=new PieChartManagger(pieChart_checkIn);
        pieChartManagger.showSolidPieChart(yvals,colors);

    }
    private void showQuestionPieChart() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(6.6f, "基本掌握"));
        yvals.add(new PieEntry(2.1f, "掌握一般"));
        yvals.add(new PieEntry(1.3f, "错误较多"));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#4EEE94"));
        colors.add(Color.parseColor("#FFD700"));
        colors.add(Color.parseColor("#FF6A6A"));

        PieChartManagger pieChartManagger=new PieChartManagger(pieChart_homework);
        pieChartManagger.showRingPieChart(yvals,colors);

    }


    private void showHomeworkPieChart() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(6.8f, "正确完成"));
        yvals.add(new PieEntry(2.1f, "未完成"));
        yvals.add(new PieEntry(1.1f, "掌握一般"));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#4EEE94"));
        colors.add(Color.parseColor("#FFD700"));
        colors.add(Color.parseColor("#FF6A6A"));

        PieChartManagger pieChartManagger=new PieChartManagger(pieChart_question);
        pieChartManagger.showRingPieChart(yvals,colors);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_title:
                changeActivity(teacherQuestionActivity1.class);
                break;
            case R.id.homework_title:
                changeActivity(teacherHomeworkActivity1.class);
                break;
        }
    }
}
