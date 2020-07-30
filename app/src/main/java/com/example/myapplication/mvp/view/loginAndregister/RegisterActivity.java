package com.example.myapplication.mvp.view.loginAndregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.contract.RegisterContract;
import com.example.myapplication.mvp.presenter.RegisterPresenter;
import com.example.myapplication.mvp.view.originalFragment.DrawerActivity;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,View.OnFocusChangeListener, RegisterContract.activity {

    /**初始化控件*/
    private TextView register_student_btn;
    private TextView register_teacher_btn;
    private TextView getCode;
    private EditText register_username;
    private EditText register_password;
    private EditText register_confirmPW;
    private EditText register_telephone;
    private EditText register_verification_code;

    /**初始化常量*/
    private String username;
    private String password;
    private String confirmPW;
    private String telephone;
    private String identity;

    private boolean u=false;
    private boolean p=false;
    private boolean c=false;
    private boolean t=false;

    /**在activity中要拿到presenter的引用*/
    private RegisterContract.presenter registerPresenter;
    private RegisterContract.activity registerActivity;


    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        register_teacher_btn=findViewById(R.id.register_teacher_btn);
        register_student_btn=findViewById(R.id.register_student_btn);
        getCode=findViewById(R.id.getCode);
        register_username=findViewById(R.id.register_username);
        register_password=findViewById(R.id.register_password);
        register_confirmPW=findViewById(R.id.register_confirmPW);
        register_telephone=findViewById(R.id.register_telephone);
        register_verification_code=findViewById(R.id.register_verification_code);
    }

    @Override
    public void setPresenter(RegisterContract.presenter presenter) {
            this.registerPresenter=presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        registerActivity=this;
        registerPresenter=new RegisterPresenter(registerActivity);


        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);
        register_teacher_btn.setOnClickListener(this);
        register_student_btn.setOnClickListener(this);
        getCode.setOnClickListener(this);

        /**
         * 对用户名，密码，电话进行焦点验证
         */
        register_username.setOnFocusChangeListener(this);
        register_password.setOnFocusChangeListener(this);
        register_confirmPW.setOnFocusChangeListener(this);
        register_telephone.setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(View v,boolean hasFocus){
        switch (v.getId()){
            case R.id.register_username:
                if(hasFocus){

                }else{
                    username=register_username.getText().toString();
                    if(username.length()<4){
                        toastCenter(this,"请输入至少4位的用户名");
                    }else if(!username.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5\\s·]+$")){
                        toastCenter(this,"用户名不能有特殊字符");
                    }else{
                        u=true;
                    }
                }
                break;
            case R.id.register_password:
                if(hasFocus){

                }else{
                    password=register_password.getText().toString();
                    if(password.length()<6){
                        toastCenter(this,"密码至少需要6位，且不能超过18位");
                    } else if(!password.matches("^[A-Za-z0-9]\\w{5,17}$")){
                        toastCenter(this,"密码由字母和数字和下划线组成");
                    }else{
                        p=true;
                    }
                }
                break;
            case R.id.register_confirmPW:
                if(hasFocus){

                }else{
                    confirmPW=register_confirmPW.getText().toString();
                    if(!confirmPW.equals(password)){
                        toastCenter(this,"两次输入密码不一致");
                    }else{
                        c=true;
                    }
                }
                break;
            case R.id.register_telephone:
                if(hasFocus){

                }else{
                    telephone=register_telephone.getText().toString();
                    if(!telephone.matches("[1][3456789]\\d{9}")){
                        toastCenter(this,"输入的手机号码非法");
                    }else{
                        t=true;
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_student_btn:
                if(check()==true){

                    identity="student";
                    registerPresenter.RegisterRequest(username,password,telephone,identity);
                }
                break;
            case R.id.register_teacher_btn:
                if(check()==true){
                    identity="teacher";
                    registerPresenter.RegisterRequest(username,password,telephone,identity);
                }
                break;
            case R.id.getCode:
                toastShort(this,"验证码发送成功！");
                break;
        }
    }

    public boolean check(){
        if (u == false) {
            toastShort(this,"请输入至少4位的不含特殊字符的用户名");
            return false;
        }else if(p==false){
            toastShort(this,"密码格式不对");
            return false;
        }else if(c==false){
            toastShort(this,"两次输入密码不一致");
            return false;
        }else if(t==false){
            toastShort(this,"电话格式不对");
            return false;
        }else{
            return true;
        }
    }

    public void gotoindex() {
        Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
        startActivity(intent);
        this.onDestroy();
    }

    @Override
    public void ErrorMessage(Exception e) {
        toastCenter(this,"注册功能出错啦！");
    }

    @Override
    public void RegisterDataProcess(String resultData) {
        Log.d(TAG,resultData);
        if(resultData.equals("注册成功")){
            gotoindex();
        }else if(resultData.equals("该手机号已注册")){
            toastCenter(this,"该手机号已注册");
        }else{
            toastCenter(this,"用户名已经存在");
        }
    }

}
