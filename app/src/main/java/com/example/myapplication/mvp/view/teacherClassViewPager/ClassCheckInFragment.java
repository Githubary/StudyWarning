package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.teacherClassFunction.checkResultActivity;
import com.example.myapplication.mvp.view.teacherClassFunction.createCheckInActivity;


public class ClassCheckInFragment extends BaseFragment implements View.OnClickListener{


    View view;
    private RelativeLayout kaoqin2;
    private TextView CountDown;

    /**构造方法：创建新实例*/
    private static ClassCheckInFragment instance=null;
    public static ClassCheckInFragment newInstance(){
        if(instance==null){
            instance= new ClassCheckInFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classcheckin, container, false);
        setHasOptionsMenu(true);
        timerStart();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        kaoqin2=view.findViewById(R.id.kaoqin2);
        kaoqin2.setOnClickListener(this);
        CountDown=view.findViewById(R.id.Countdown);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kaoqin2:
                changeActivity(checkResultActivity.class);
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.checkintoolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.create_checkIn:
                changeActivity(createCheckInActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 尝试实现一下倒计时的功能
     */
    private CountDownTimer timer = new CountDownTimer(2*60*1000,1000) {
        /**
         * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            CountDown.setText(formatTime(millisUntilFinished));
        }

      /**
         * 倒计时完成时被调用
         */
        @Override
        public void onFinish() {
            CountDown.setText("00:00");
        }
    };


    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    private String formatTime(long millisecond) {
        int minute;
        int second;
        minute= (int)((millisecond/1000)/60);
        second= (int)((millisecond/1000)%60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        }else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }

    /**
     * 取消倒计时
     */
    public void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
    }

}

