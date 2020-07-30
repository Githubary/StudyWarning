package com.example.myapplication.mvp.model;

import com.example.myapplication.utils.httpUtils.OkHttpUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyMessageModel {

    private RequestBody requestBody;
    private MyMessageDao myMessageDao;

    public static final MediaType ContentType = MediaType.parse("image/jpg");

    private final String url = "http://47.92.142.206:8080/uploadUserMess";

    public void setMyMessageDao(MyMessageDao myMessageDao){
        this.myMessageDao=myMessageDao;
    }

    public void AddPersonalInformation(String nickName, String gender, String signature, String username, File file) {
        String FileName=null;
        if(file!=null){
            FileName = file.getName();
            System.out.println("获取到的文件名"+FileName);
            requestBody = RequestBody.create(ContentType,file);
        }else{
            requestBody=RequestBody.create(ContentType,"");
        }
        RequestBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("username",username)
                .addFormDataPart("nickName",nickName)
                .addFormDataPart("gender",gender)
                .addFormDataPart("signature",signature)
                .addFormDataPart("file",FileName,requestBody)
                .build();
        OkHttpUtils.postMultipartAsync(url, multipartBody, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                    myMessageDao.AddPersonalInformationFailure();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                    myMessageDao.AddPersonalInformationSuccessful(result);
            }
        });
    }


    public interface MyMessageDao{
        void AddPersonalInformationSuccessful(String result);
        void AddPersonalInformationFailure();
    }
}
