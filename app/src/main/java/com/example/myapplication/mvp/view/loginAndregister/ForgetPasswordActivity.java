package com.example.myapplication.mvp.view.loginAndregister;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener{

    private Button forget_btn;
    private TextView forget_getCode;

    @Override
    protected int initLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        forget_btn=findViewById(R.id.forget_btn);
        forget_getCode=findViewById(R.id.forger_getCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        forget_btn.setOnClickListener(this);
        forget_getCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_btn:
                changeActivity(ChangePasswordActivity.class);
                break;
            case R.id.forger_getCode:
                toastLong(this,"发送验证码成功！");
                break;
        }
    }
}
