package com.example.myapplication.utils.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 图片轮播启动类
 */
public class ImgScroll extends androidx.viewpager.widget.ViewPager {


    Activity activity; // 上下文
    List<View> listViews; // 图片组
    int scrollTime = 4000;
    Timer timer=null;
    int oldIndex = 0;
    int curIndex = 0;

    public ImgScroll(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *
     * @param activity 显示广告的主界面
     * @param imgList  图片列表
     * @param scrollTime    滚动时间间隔
     */
    public void start(Activity activity, List<View> imgList, int scrollTime){
        this.activity=activity;
        this.listViews=imgList;
        this.scrollTime=scrollTime;
        //设置适配器
        this.setAdapter(new ViewPagerAdapter(activity,listViews));
        startTimer();
        //触摸时停止
        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startTimer();
                } else {
                    stopTimer();
                }
                return false;
            }
        });
    }



    /**
     * 停止滚动
     */
    public void stopTimer() {
        if (timer != null) {
            Log.e("","我停止了");
            timer.cancel();
            timer = null;
        }
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImgScroll.this.setCurrentItem(ImgScroll.this.getCurrentItem()+1);
                    }
                });
            }
        },scrollTime,scrollTime);
    }
}
