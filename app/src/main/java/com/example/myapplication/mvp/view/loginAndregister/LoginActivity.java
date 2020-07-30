package com.example.myapplication.mvp.view.loginAndregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.entity.User;
import com.example.myapplication.mvp.contract.LoginContract;
import com.example.myapplication.mvp.presenter.LoginPresenter;
import com.example.myapplication.mvp.view.originalFragment.DrawerActivity;
import com.google.gson.Gson;

public class LoginActivity extends BaseActivity implements LoginContract.activity {

    private Button login_btn;
    private TextView forget_tv;
    private TextView changeUser_tv;
    private TextView register_tv;
    private EditText username_et;
    private EditText password_et;
    String username;
    String password;
    String identity;

    //用于保存登录信息的SharedPreferences
    SharedPreferences currentUserMessage;
    SharedPreferences.Editor currentUserMessageEditor;

    private LoginContract.presenter loginPresenter;


    public void LoginActivity(LoginPresenter loginPresenter){
        this.loginPresenter = loginPresenter;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        login_btn=findViewById(R.id.login_btn);
        forget_tv=findViewById(R.id.forget_tv);
        changeUser_tv=findViewById(R.id.changeUser_tv);
        register_tv=findViewById(R.id.register_tv);
        username_et=findViewById(R.id.username_et);
        password_et=findViewById(R.id.password_et);
        SharedPreferences pref = getSharedPreferences("loginmessage",MODE_PRIVATE);
        username_et.setText(pref.getString("name",""));
        password_et.setText(pref.getString("password",""));
    }



    @Override
    public void setPresenter(@NonNull LoginContract.presenter presenter) {
        loginPresenter = presenter;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //初始化页面信息
        setTitle(false);       //写在onCreate之前
        setState(false);       //写在onCreate之前
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences("currentUserMessage",MODE_PRIVATE);
        Boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        if(isLogin==true){
            gotoindex();
        }

        loginPresenter = new LoginPresenter(this);

        //answerState删除
        SharedPreferences pref = getSharedPreferences("answerChangeState",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("state");
        editor.commit();

        //写入登录按钮监听方法，向loginPresenter传送数据
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref = getSharedPreferences("registermessage",MODE_PRIVATE);
                System.out.println(pref.getString("username",""));

                //      这个是记录选择过的信息的
                currentUserMessage=getSharedPreferences("currentUserMessage", Context.MODE_PRIVATE);
                identity=currentUserMessage.getString("identity","");

                /**获取到输入的用户名和密码*/
                username = username_et.getText().toString().trim();
                password = password_et.getText().toString().trim();

                Log.d("s",pref.getString("username",""));
                Log.d("s",pref.getString("password",""));

                if(username==null||password==null){
                    showInputNoNull();
                }else{
                    /**进入控制层执行post请求*/
                    loginPresenter.LoginRequest(username,password,identity);
                }

//                else if(username==pref.getString("username","")&&password==pref.getString("password","")){
////                    gotoindex();
//                }
            }
        });


        register_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(RegisterActivity.class);
            }
        });

        forget_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(ForgetPasswordActivity.class);
            }
        });

        changeUser_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(loginchooseActivity.class);
            }
        });

    }
    /**
     * 这段功能是想实现，登录一次后，即时退出程序，下次再进去也不必从登录页面开始。
     */
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhh");
//        SharedPreferences sharedPreferences=getSharedPreferences("isLogin",MODE_PRIVATE);
//        isLogin = sharedPreferences.getBoolean("isLogin",false);//从sp中取出状态，判断是否已登录
//        if(isLogin){
//            gotoindex();
//            editor.putBoolean("isLogin",true);
//            editor.apply();
//        }else{
//
//        }
//    }

    /**
     * 这段功能是想实现，登录一次后，即时退出程序，下次再进去也不必从登录页面开始。
     */
//    @Override
//    protected void onStart() {
//        super.onStart();
//        System.out.println("ssssssssssssssssssssssssssssssssssssssssssshhhhhhhhhhhhhh");
//        SharedPreferences sharedPreferences=getSharedPreferences("isLogin",MODE_PRIVATE);
//        isLogin = sharedPreferences.getBoolean("isLogin",false);//从sp中取出状态，判断是否已登录
//        if(isLogin){
//            editor.putBoolean("isLogin",true);
//            editor.apply();
//            System.out.println(sharedPreferences.getBoolean("isLogin",false));
//            gotoindex();
//
//        }else{
//            editor.putBoolean("isLogin",true);
//            editor.apply();
//        }
//    }



    public void LoginDataProcess(String loginData){
        System.out.println("得到的登录数据:"+loginData);
        User currentUser = new Gson().fromJson(loginData, User.class);
        System.out.println("json数据转成对象后:" + currentUser.toString());
        String state = currentUser.getResult();
        System.out.println("登录状态"+state);
        if(state.equals("密码错误")){
            toastLong(this,"密码错误");
        }else if(state.equals("该用户不存在")){
            toastLong(this,"该身份用户不存在");
        }else {
            saveUserMessage(currentUser);
            gotoindex();
        }
    }

    public void ErrorMessage(Exception e){
        toastCenter(this,"登录功能出错啦！");
    }


    public void showInputNoNull(){
        toastLong(getApplicationContext(),"用户名和密码不能为空！");
    }
    public void showError(){
        toastLong(getApplicationContext(),"格式不正确！");
    }

    @Override
    public void gotoindex() {
        Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void saveUserMessage(User user) {
        currentUserMessageEditor=getSharedPreferences("currentUserMessage",MODE_PRIVATE).edit();
        String json = new Gson().toJson(user);
        String username = user.getUsername();
        String telephone = user.getTelephone();
        String nickName = user.getNickName();
        String gender = user.getGender();
        String signature = user.getSignature();
        String headImage = user.getHeadImage();
        System.out.println("拿到的头像路径："+headImage);
        currentUserMessageEditor.putString("currentUserName",username);
        currentUserMessageEditor.putString("currentUserTelephone",telephone);
        currentUserMessageEditor.putString("currentUserNickName",nickName);
        currentUserMessageEditor.putString("currentUserGender",gender);
        currentUserMessageEditor.putString("currentUserSignature",signature);
        currentUserMessageEditor.putString("currentUserHeadImage",headImage);
        currentUserMessageEditor.putBoolean("isLogin",true);
        currentUserMessageEditor.apply();
        System.out.println("当前保存进去的用户名"+currentUserMessage.getString("currentUserName",""));
    }
}
