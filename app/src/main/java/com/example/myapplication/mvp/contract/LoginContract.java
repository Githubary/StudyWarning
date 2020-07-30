package com.example.myapplication.mvp.contract;


import com.example.myapplication.base.Bactivity.IBaseActivity;
import com.example.myapplication.base.Bpresenter.IBasePresenter;
import com.example.myapplication.entity.User;


public interface LoginContract {


    /**
     * 登录界面的view层引用
     */
    public interface activity extends IBaseActivity<presenter> {
        //用户名和密码不能为空
        public void showInputNoNull();

        //格式不正确
        public void showError();

        //跳转到首页
        public void gotoindex();

        //保存用户登录信息
        void saveUserMessage(User user);

        public void ErrorMessage(Exception e);

        public void LoginDataProcess(String resultData);
    }


    /**
     * 登录界面的presenter层的引用
     */
    interface presenter extends IBasePresenter {
        public void  LoginRequest(String username, String password, String identity);
    }
}