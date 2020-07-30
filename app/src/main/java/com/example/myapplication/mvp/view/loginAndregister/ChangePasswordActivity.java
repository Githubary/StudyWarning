package com.example.myapplication.mvp.view.loginAndregister;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;

public class ChangePasswordActivity extends BaseActivity {
    private Button changePassword_Confirm;

    @Override
    protected int initLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {
        changePassword_Confirm=findViewById(R.id.changePassword_confirm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        changePassword_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(LoginActivity.class);
            }
        });
    }
}
