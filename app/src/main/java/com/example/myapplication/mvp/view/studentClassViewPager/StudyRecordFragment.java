package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.studentClassFunction.StudyRecord_kaoqingCount_Activity;


public class StudyRecordFragment extends BaseFragment {

    private TextView kaoqingCount;
    View view;

    /**构造方法：创建新实例*/
    private static StudyRecordFragment instance=null;
    public static StudyRecordFragment newInstance(){
        if(instance==null){
            instance= new StudyRecordFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_studyrecord, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        kaoqingCount=view.findViewById(R.id.kaoqingCount);
        kaoqingCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(StudyRecord_kaoqingCount_Activity.class);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
