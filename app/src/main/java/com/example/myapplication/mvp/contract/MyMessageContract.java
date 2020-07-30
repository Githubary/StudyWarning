package com.example.myapplication.mvp.contract;

import com.example.myapplication.base.Bactivity.IBaseActivity;
import com.example.myapplication.base.Bpresenter.IBasePresenter;

import java.io.File;

public interface MyMessageContract {
    interface activity extends IBaseActivity<presenter>{

        void AddPersonalInformationSuccessful(String result);
    }
    interface presenter extends IBasePresenter{
        void AddPersonalInformationRequest(String nickName, String gender, String signature, String username, File file);
    }
}
