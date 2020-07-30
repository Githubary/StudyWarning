package com.example.myapplication.mvp.view.studentClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.utils.alertUtils.BottomDialog;


public class StudentFileFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static StudentFileFragment instance=null;
    public static StudentFileFragment newInstance(){
        if(instance==null){
            instance= new StudentFileFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fileup, container, false);
        return view;
    }


}
