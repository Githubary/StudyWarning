package com.example.myapplication.mvp.presenter;

import com.example.myapplication.mvp.contract.MyMessageContract;
import com.example.myapplication.mvp.model.MyMessageModel;

import java.io.File;

public class MyMessagePresenter implements MyMessageModel.MyMessageDao,MyMessageContract.presenter {

    private MyMessageContract.activity myMessageActivity;
    private MyMessageModel myMessageModel;


    public  MyMessagePresenter(MyMessageContract.activity myMessageActivity){
         this.myMessageActivity=myMessageActivity;
         myMessageModel = new MyMessageModel();
         myMessageModel.setMyMessageDao(this);
         myMessageActivity.setPresenter(this);
    }



    public void AddPersonalInformationRequest(String nickName, String gender, String signature, String username, File file){
        myMessageModel.AddPersonalInformation(nickName,gender,signature,username,file);
    }


    @Override
    public void start() {

    }

    @Override
    public void AddPersonalInformationSuccessful(String result) {
            myMessageActivity.AddPersonalInformationSuccessful(result);
    }

    @Override
    public void AddPersonalInformationFailure() {

    }
}
