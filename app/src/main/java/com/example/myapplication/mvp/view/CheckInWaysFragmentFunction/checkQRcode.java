package com.example.myapplication.mvp.view.CheckInWaysFragmentFunction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;

public class checkQRcode extends BaseFragment {

    private static checkQRcode instance=null;

    public static checkQRcode newInstance(){
        if(instance==null){
            instance=new checkQRcode();
        }
        return instance;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkqrcode, container, false);
        return view;
    }
}
