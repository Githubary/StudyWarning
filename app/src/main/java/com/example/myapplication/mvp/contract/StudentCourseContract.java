package com.example.myapplication.mvp.contract;

import com.example.myapplication.base.Bfragment.IBaseFragment;
import com.example.myapplication.base.Bpresenter.IBasePresenter;
import com.example.myapplication.entity.Course;

import java.util.List;


public interface StudentCourseContract {

    interface fragment extends IBaseFragment<StudentCourseContract.presenter> {

        void ErrorMessage();
        void ShowStudentCourse(List<Course> courseList);
    }
    interface presenter extends IBasePresenter {
        void StudentCourseRequest(String username);
        void QuitCourseRequest(String username,String courseNumber);
    }
}
