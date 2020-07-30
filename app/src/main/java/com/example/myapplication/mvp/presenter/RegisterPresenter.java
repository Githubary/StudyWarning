package com.example.myapplication.mvp.presenter;

import com.example.myapplication.mvp.contract.RegisterContract;
import com.example.myapplication.mvp.model.RegisterModel;

public class RegisterPresenter implements RegisterContract.presenter, RegisterModel.RegisterDao {

    private RegisterContract.activity registerActivity;
    private RegisterModel  registerModel;

    /**封装Toast对象*/


    public RegisterPresenter(RegisterContract.activity registerActivity){
        this.registerActivity=registerActivity;
        registerModel = new RegisterModel();
        registerModel.setRegisterDao(this);
        registerActivity.setPresenter(this);
    }

    public void RegisterRequest(String username, String password,String telephone,String identity){
        registerModel.registerHttp(username,password,telephone,identity);
    }

    @Override
    public void registerFailure(Exception e) {
           registerActivity.ErrorMessage(e);
    }

    @Override
    public void registerSuccessful(String resultData) {
        registerActivity.RegisterDataProcess(resultData);
    }

    @Override
    public void start() {

    }
}
