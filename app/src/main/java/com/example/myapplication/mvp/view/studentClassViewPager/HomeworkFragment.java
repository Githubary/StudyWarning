package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.studentClassFunction.HomeworkDetailActivity;


public class HomeworkFragment extends BaseFragment implements View.OnClickListener{

    private View view;

    private RelativeLayout homework1;


    /**构造方法：创建新实例*/
    private static HomeworkFragment instance=null;
    public static HomeworkFragment newInstance(){
        if(instance==null){
            instance= new HomeworkFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homework, container, false);
        homework1=view.findViewById(R.id.homework1);
        homework1.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homework1:
                changeActivity(HomeworkDetailActivity.class);
                break;
        }
    }
}
