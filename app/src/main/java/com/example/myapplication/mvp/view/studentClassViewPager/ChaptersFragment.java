package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;



public class ChaptersFragment extends BaseFragment {
    View view;
    private TextView pptlong;

    /**构造方法：创建新实例*/
    private static ChaptersFragment instance=null;
    public static ChaptersFragment newInstance(){
        if(instance==null){
            instance= new ChaptersFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chapters, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        pptlong=view.findViewById(R.id.pptlong);
        pptlong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(PPT2Activity.class);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
