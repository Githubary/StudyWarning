package com.example.myapplication.mvp.presenter;

import com.example.myapplication.mvp.contract.LoginContract;
import com.example.myapplication.mvp.model.LoginModel;

public class LoginPresenter implements LoginContract.presenter, LoginModel.LoginDao {

    private LoginContract.activity loginActivity;
    private LoginModel loginModel;

    public LoginPresenter(LoginContract.activity loginActivity){
        this.loginActivity=loginActivity;
        loginModel = new LoginModel();
        loginModel.setLoginDao(this);
        loginActivity.setPresenter(this);
    }

    @Override
    public void LoginRequest(String username, String password, String identity) {
        loginModel.loginHttp(username,password,identity);
    }


    @Override
    public void loginFailure(Exception e) {
        loginActivity.ErrorMessage(e);
    }

    @Override
    public void loginSuccessful(String resultData) {
        loginActivity.LoginDataProcess(resultData);
    }



    @Override
    public void start() {

    }


}
