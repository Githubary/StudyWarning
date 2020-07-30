package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;


public class StatisticsFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static StatisticsFragment instance=null;
    public static StatisticsFragment newInstance(){
        if(instance==null){
            instance= new StatisticsFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        return view;
    }
}
