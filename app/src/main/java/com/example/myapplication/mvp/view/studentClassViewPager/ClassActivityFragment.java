package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;


public class ClassActivityFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static ClassActivityFragment instance=null;
    public static ClassActivityFragment newInstance(){
        if(instance==null){
            instance= new ClassActivityFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_activity, container, false);
        return view;
    }


}
