package com.example.myapplication.mvp.model;

import com.example.myapplication.entity.User;
import com.example.myapplication.utils.httpUtils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LoginModel{

    private RequestBody requestBody;

    private User user;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String url = "http://47.92.142.206:8080/login";

    private LoginDao loginDao;

    public void setLoginDao(LoginDao loginDao){
        this.loginDao=loginDao;
    }
    //请求方法
    public void loginHttp(String username, String password, String identity){
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIdentity(identity);
        String json = new Gson().toJson(user);
        System.out.println(json);
        requestBody=RequestBody.create(JSON,json);
        /**
         * result即，result=response.body().toString()
         */
        OkHttpUtils.postRequestBodyAsync(url, requestBody, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                loginDao.loginFailure(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                System.out.println("拿到的所有学生的信息："+result);
                loginDao.loginSuccessful(result);
            }
        });
    }
    public interface LoginDao{
        void loginFailure(Exception e);
        void loginSuccessful(String resultData);
    }
}

