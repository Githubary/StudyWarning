package com.example.myapplication.base.Bfragment;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private Intent intent;


    /**
     * 跳转页面
     * @param activity
     */
    public void changeActivity(Class activity){
        intent = new Intent(getActivity(),activity);
        startActivity(intent);
    }

}
