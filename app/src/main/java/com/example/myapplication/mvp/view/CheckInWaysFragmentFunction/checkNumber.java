package com.example.myapplication.mvp.view.CheckInWaysFragmentFunction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.teacherClassViewPager.teacherClassMainActivity;
import com.example.myapplication.utils.alertUtils.DialogUtil;
import com.hb.dialog.dialog.ConfirmDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkNumber extends BaseFragment {

    private static checkNumber instance=null;
    private View view;
    private EditText checkIn_number;
    private TextView checkIn_number_create;
    private checkNumberListener listener;
    Matcher m;
    Pattern p;

    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;

    String text;
    public static checkNumber newInstance(){
        if(instance==null){
            instance=new checkNumber();
        }
        return instance;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checknumber, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkIn_number=view.findViewById(R.id.checkIn_number);
        checkIn_number_create=view.findViewById(R.id.checkIn_number_create);


        p= Pattern.compile("[0-9]*");


        checkIn_number_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=checkIn_number.getText().toString();
                m = p.matcher(text);
                        if(text.length()<=0||text.length()>6){
                            Toast.makeText(getContext(),"请输入0-6位数",Toast.LENGTH_SHORT).show();
                        }else if(!m.matches()){
                            Toast.makeText(getContext(),"请输入数字",Toast.LENGTH_SHORT).show();
                        }else {
                            confirmHint(getContext());
                        }

                listener.sendValue(text);
            }
        });
    }

    public interface checkNumberListener{
        public void sendValue(String value);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener=(checkNumberListener)getActivity();
    }


    /**
     *  确认提示的方法
     * @param context 当前场景，背景，上下文
     */
    void confirmHint(Context context){
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("已向全体学生发送签到活动");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }
}
