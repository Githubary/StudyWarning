package com.example.myapplication.mvp.view.generalRevision;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.utils.Chart.LineChartManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class generalRevisionFeedbackActivity extends BaseActivity {


    private LineChart lineChart;
    private ScrollView scrollView;

    @Override
    protected int initLayout() {
        return R.layout.activity_general_revision_feedback;
    }

    @Override
    protected void initView() {
        lineChart=findViewById(R.id.lineChart);
        scrollView=findViewById(R.id.feedback_scrollView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        //解决滑动冲突
        lineChart.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                    {
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                }
                return false;
            }
        });

        showLineChart();

    }

    private void showLineChart() {


        //设置x轴数据
        ArrayList<String> xValueList = new ArrayList<>();
        for(int i=0;i<12;i++){
            xValueList.add("第"+i+"课时");
        }
        //设置y轴数据
        ArrayList<ArrayList<Integer>> allYValueList = new ArrayList<>();

           //y轴数据第一条
        ArrayList<Integer> yValueList1=new ArrayList<>();
        yValueList1.add(86);yValueList1.add(82);
        yValueList1.add(79);yValueList1.add(84);
        yValueList1.add(58);yValueList1.add(66);
        yValueList1.add(89);yValueList1.add(74);
        yValueList1.add(64);yValueList1.add(54);
        yValueList1.add(86);yValueList1.add(92);

        ArrayList<Integer> yValueList2=new ArrayList<>();
        yValueList2.add(89);yValueList2.add(83);
        yValueList2.add(82);yValueList2.add(85);
        yValueList2.add(76);yValueList2.add(74);
        yValueList2.add(92);yValueList2.add(72);
        yValueList2.add(76);yValueList2.add(62);
        yValueList2.add(82);yValueList2.add(84);

        allYValueList.add(yValueList1);
        allYValueList.add(yValueList2);

        System.out.println(allYValueList.get(1).get(1));

        //设置名称
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("模拟测试前");
        nameList.add("模拟测试后");


        LineChartManager lineChartManager = new LineChartManager(lineChart);
        lineChartManager.setLegend();
        lineChartManager.initLineChart(xValueList,allYValueList,nameList);
    }
}
