package com.example.myapplication.mvp.contract;

import com.example.myapplication.base.Bfragment.IBaseFragment;
import com.example.myapplication.base.Bpresenter.IBasePresenter;
import com.example.myapplication.entity.Course;

import java.util.List;

public interface TeacherCourseContract {
    interface fragment extends IBaseFragment<presenter>{
        void ErrorMessage(Exception e);
        void ShowTeacherCourse(List<Course> courseList);
    }
    interface presenter extends IBasePresenter{
        public void TeacherCourseRequest(String username);
        public void DeleteCourseRequest(String deleteCourseNumber);
    }
}
