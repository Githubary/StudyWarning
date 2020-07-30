package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;


public class TeachingProgramFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static TeachingProgramFragment instance=null;
    public static TeachingProgramFragment newInstance(){
        if(instance==null){
            instance= new TeachingProgramFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachingprogram, container, false);
        return view;
    }
}
