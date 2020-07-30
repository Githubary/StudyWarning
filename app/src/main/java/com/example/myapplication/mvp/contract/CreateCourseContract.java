package com.example.myapplication.mvp.contract;

import com.example.myapplication.base.Bactivity.IBaseActivity;
import com.example.myapplication.base.Bpresenter.IBasePresenter;

import java.io.File;

public interface CreateCourseContract {

    interface activity extends IBaseActivity<presenter>{
        void ErrorMessage(Exception e);
        void CreateCourseDataProcess(String resultDate);
    }

    interface presenter extends IBasePresenter{
        /**发送课程封面的请求*/
        public void CreateCourseRequest(String username, String courseName,String courseNumber,String courseTeacher, File file);
    }
}
