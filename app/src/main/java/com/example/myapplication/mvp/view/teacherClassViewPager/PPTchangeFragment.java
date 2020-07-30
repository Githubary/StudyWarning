package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;


public class PPTchangeFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static PPTchangeFragment instance=null;
    public static PPTchangeFragment newInstance(){
        if(instance==null){
            instance= new PPTchangeFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pptchange, container, false);
        return view;
    }
}
