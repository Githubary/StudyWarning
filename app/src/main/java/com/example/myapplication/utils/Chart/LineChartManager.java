package com.example.myapplication.utils.Chart;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class LineChartManager {
    
    public LineChart lineChart;
    
    public LineChartManager(LineChart lineChart){
        this.lineChart=lineChart;
    }


    /**
     * 初始化折线图信息
     */
    public void initLineChart(ArrayList<String> xValueList, ArrayList<ArrayList<Integer>> allYValueList, ArrayList<String> nameList) {

        lineChart.resetTracking();

        //设置描述文本不显示
        lineChart.getDescription().setEnabled(false);
        //没有数据时显示的信息
        lineChart.setNoDataText("未获得数据");
        //设置是否显示表格背景
        lineChart.setDrawGridBackground(false);
        //设置是否可以触摸
        lineChart.setTouchEnabled(true);
        lineChart.setDragDecelerationFrictionCoef(0.9f);
        //设置是否可以拖拽
        lineChart.setDragEnabled(true);
        //设置是否可以缩放
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);
        //如果禁用，可以分别在x轴和y轴上进行缩放。
        lineChart.setPinchZoom(true);
        //设置背景颜色
        lineChart.setBackgroundColor(Color.parseColor("#FFFFFF"));


        setXAxis(xValueList);
        setYAxis();

        setData(xValueList,allYValueList,nameList);



        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = (float) xValueList.size()/(float) 6;
        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        lineChart.zoom(ratio,1f,0,0);


        //设置从X轴出来的动画时间
        lineChart.animateX(1500);
        //设置XY轴动画
        lineChart.animateXY(1500,1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);
    }

    /**
     * 注释的添加
     */
    public void setLegend(){
        //设置图表的注解，只有当数据存在的时候才生效
        Legend legend = lineChart.getLegend();
        //设置图解样式，可以是圆，也可以是线性等的
        legend.setForm(Legend.LegendForm.CIRCLE);
        //设置图解文字颜色
        legend.setTextColor(Color.parseColor("#000000"));
    }

    /**
     * 定义X坐标轴
     */
    public void setXAxis(ArrayList<String> xValueList) {
        //X轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置X轴数值的大小
        xAxis.setTextSize(9f);
        //设置X轴的颜色
        xAxis.setTextColor(Color.BLACK);

        //设置不从X轴发出横线
        xAxis.setDrawGridLines(true);


        // 设置显示X轴
        xAxis.setDrawAxisLine(true);
        // 设置X轴现实的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置最小间隔，防止当放大时出现重复标签
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(6, false);
        //设置标签倾斜
        xAxis.setLabelRotationAngle(35);
        //设置自定义X轴值
        IAxisValueFormatter xValueFormatter =new IndexAxisValueFormatter(xValueList);
        xAxis.setValueFormatter(xValueFormatter);
    }

    /**
     * 定义Y轴
     */
    public void setYAxis(){
        //得到左边的纵坐标
        YAxis yAxis = lineChart.getAxisLeft();
        //得到右边的纵坐标
        YAxis rightAxis =lineChart.getAxisRight();
        //设置Y轴是否显示，这边设置右侧Y轴不显示
        rightAxis.setEnabled(false);
        //设置是否显示Y轴网格线
        yAxis.setDrawGridLines(false);
        //Y轴之间的最小间距
        yAxis.setGranularity(1);
        //设置Y轴的刻度数量，为了好看，可以比X轴多一个长度单位,第二个参数为true,将会画出明确数量（带有小数点）,默认false
        yAxis.setLabelCount( 10,false);
        //设置自定义Y轴值
        yAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });
    }

    int color;

    private void setData(ArrayList<String> xValueList, ArrayList<ArrayList<Integer>> allYValueList, ArrayList<String> nameList) {

        lineChart.resetTracking();

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        for (int z = 0; z < nameList.size(); z++) {
            ArrayList<Entry> values = new ArrayList<>();
            for (int i = 0; i < xValueList.size(); i++) {
                values.add(new Entry(i, allYValueList.get(z).get(i)));
            }
            LineDataSet set = new LineDataSet(values, nameList.get(z));
            set.setDrawIcons(false);//设置图标不显示
            set.setAxisDependency(YAxis.AxisDependency.LEFT);//设置Y值使用左边Y轴的坐标值
            if(z==0){
               color =Color.parseColor("#1296db");
            }else if(z==1){
                color=Color.parseColor("#FF6A6A");
            }

            set.setColor(color);
            set.setCircleColor(color);//设置数据点颜色
            set.setCircleColorHole(color);//设置数据点中间填充颜色
            set.setLineWidth(1f);
            set.setCircleRadius(4f);
            set.setHighlightEnabled(true);//设置是否显示十字线
            set.setDrawCircleHole(false);//设置是否在数据点中间显示一个孔
            dataSets.add(set);
        }
        LineData data = new LineData(dataSets);
        data.setDrawValues(true);//设置不显示数据点
        lineChart.setData(data);
        lineChart.invalidate();
    }





}
