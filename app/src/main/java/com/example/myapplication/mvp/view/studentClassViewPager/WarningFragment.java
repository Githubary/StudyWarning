package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;


public class WarningFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static WarningFragment instance=null;
    public static WarningFragment newInstance(){
        if(instance==null){
            instance= new WarningFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warning, container, false);
        return view;
    }
}
