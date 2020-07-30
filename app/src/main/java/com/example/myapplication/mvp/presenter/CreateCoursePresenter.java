package com.example.myapplication.mvp.presenter;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.myapplication.entity.Course;
import com.example.myapplication.entity.User;
import com.example.myapplication.mvp.contract.CreateCourseContract;
import com.example.myapplication.mvp.model.CreateCourseModel;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class CreateCoursePresenter implements CreateCourseContract.presenter, CreateCourseModel.CreateCourseDao {

    /**需要在这个presenter页面注入activity*/
    private CreateCourseContract.activity createCourseActivity;
    private CreateCourseModel createCourseModel;


    public CreateCoursePresenter(CreateCourseContract.activity createCourseActivity){
        this.createCourseActivity=createCourseActivity;
        createCourseModel = new CreateCourseModel();
        createCourseModel.setCreateCourseDao(this);
        createCourseActivity.setPresenter(this);
    }

    /**
     * 让model层执行获取数据的请求
     * @param username
     * @param courseName
     * @param courseNumber
     * @param courseTeacher
     * @param file
     */
    @Override
    public void CreateCourseRequest(String username, String courseName,String courseNumber,String courseTeacher, File file){
        createCourseModel.createCourseHttp(username,courseName,courseNumber,courseTeacher,file);
    }


    @Override
    public void createCourseFailure(Exception e) {
        createCourseActivity.ErrorMessage(e);
    }

    @Override
    public void createCourseSuccessful(String resultDate) {
        createCourseActivity.CreateCourseDataProcess(resultDate);
    }

    @Override
    public void start() {

    }

}
