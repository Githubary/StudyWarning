package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.teacherClassFunction.classQuestionResultActivity;
import com.example.myapplication.mvp.view.teacherClassFunction.createClassQuestionActivity;


public class ClassQuestionFragment extends BaseFragment {


    private RelativeLayout classQuestion2;
    View view;

    /**构造方法：创建新实例*/
    private static ClassQuestionFragment instance=null;
    public static ClassQuestionFragment newInstance(){
        if(instance==null){
            instance= new ClassQuestionFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classquestion, container, false);
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.classquestiontoolbar,menu);
        classQuestion2=view.findViewById(R.id.classQuestion2);
        classQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(classQuestionResultActivity.class);
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.classQuestion:
                changeActivity(createClassQuestionActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
