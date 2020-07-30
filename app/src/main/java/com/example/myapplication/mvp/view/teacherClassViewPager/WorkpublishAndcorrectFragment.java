package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.teacherClassFunction.HomeworkResultActivity;
import com.example.myapplication.mvp.view.teacherClassFunction.createClassQuestionActivity;
import com.hb.dialog.myDialog.MyAlertInputDialog;


public class WorkpublishAndcorrectFragment extends BaseFragment {

    /**可以输入文字的dialog*/
    private MyAlertInputDialog myAlertInputDialog;

    private RelativeLayout homework1;
    View view;

    /**构造方法：创建新实例*/
    private static WorkpublishAndcorrectFragment instance=null;
    public static WorkpublishAndcorrectFragment newInstance(){
        if(instance==null){
            instance= new WorkpublishAndcorrectFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workpublishandcorrect, container, false);
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        homework1=view.findViewById(R.id.homework1);
        homework1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(HomeworkResultActivity.class);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.work_public_correct_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.work_publish_correct:
                myAlertInputDialog(getActivity(),"作业名称");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 创建班级的弹出框
     * @param context
     * @param msg
     */
    void myAlertInputDialog(Context context, String msg){
        myAlertInputDialog = new MyAlertInputDialog(context).builder()
                .setTitle(msg)
                .setEditText("");
        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok();
                myAlertInputDialog.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    public void ok(){
        changeActivity(createClassQuestionActivity.class);
    }

    public void cancel(){

    }
}
