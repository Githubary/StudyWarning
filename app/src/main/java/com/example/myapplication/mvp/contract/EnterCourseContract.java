package com.example.myapplication.mvp.contract;

import com.example.myapplication.base.Bactivity.IBaseActivity;
import com.example.myapplication.base.Bpresenter.IBasePresenter;
import com.example.myapplication.entity.EnterCourse;

import java.util.List;

public interface EnterCourseContract {
    interface activity extends IBaseActivity<presenter>{

        void ShowEnterCourse(List<EnterCourse> enterCourseList);

        void CourseNotExit();

        void JoinTheCourseResult(String result);
    }
    interface presenter extends IBasePresenter {
        void enterCourseShowRequest(String courseNumber);
        void JoinTheCourseRequest(String username,String courseNumber);
    }
}
