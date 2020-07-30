package com.example.myapplication.mvp.contract;

import com.example.myapplication.base.Bactivity.IBaseActivity;
import com.example.myapplication.base.Bpresenter.IBasePresenter;

public interface RegisterContract {

    interface activity extends IBaseActivity<presenter> {

        /**
         * 失败信息
         * @param e
         */
        void ErrorMessage(Exception e);

        /**
         * 登录成功返回数据处理
         * @param resultData
         */
        void RegisterDataProcess(String resultData);
    }
    interface presenter extends IBasePresenter{
        /**
         * 发送登录请求
         * @param username
         * @param password
         * @param telephone
         * @param identity
         */
        public void RegisterRequest(String username, String password,String telephone,String identity);
    }
}
