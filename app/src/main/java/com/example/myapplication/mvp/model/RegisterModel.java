package com.example.myapplication.mvp.model;

import com.example.myapplication.entity.User;
import com.example.myapplication.utils.httpUtils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public  class RegisterModel {
    private RequestBody requestBody;

    private User user;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final String url = "http://47.92.142.206:8080/register";

    private RegisterModel.RegisterDao registerDao;

    public void setRegisterDao(RegisterModel.RegisterDao registerDao){
        this.registerDao=registerDao;
    }
    //请求方法
    public void registerHttp(String username, String password,String telephone,String identity){
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setIdentity(identity);
        String json = new Gson().toJson(user);
        System.out.println("发送的注册内容"+json);
        requestBody=RequestBody.create(JSON,json);
        /**
         * result即，result=response.body().toString()
         */
        OkHttpUtils.postRequestBodyAsync(url, requestBody, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                registerDao.registerFailure(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                registerDao.registerSuccessful(result);
            }
        });
    }
    public interface RegisterDao{
        void registerFailure(Exception e);
        void registerSuccessful(String resultData);
    }
}
