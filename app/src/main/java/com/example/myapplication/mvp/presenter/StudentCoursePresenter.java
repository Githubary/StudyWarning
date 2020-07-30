package com.example.myapplication.mvp.presenter;

import com.example.myapplication.entity.Course;
import com.example.myapplication.mvp.contract.StudentCourseContract;
import com.example.myapplication.mvp.model.StudentCourseModel;

import java.util.List;

public class StudentCoursePresenter implements StudentCourseContract.presenter, StudentCourseModel.StudentCourseDao {

    private StudentCourseModel studentCourseModel;
    private StudentCourseContract.fragment studentCourseFragment;

    public StudentCoursePresenter(StudentCourseContract.fragment studentCourseFragment){
        studentCourseModel = new StudentCourseModel();
        studentCourseModel.setStudentCourseDao(this);
        this.studentCourseFragment = studentCourseFragment;
        studentCourseFragment.setPresenter(this);
    }

    public void StudentCourseRequest(String username){
        studentCourseModel.StudentCourseShow(username);
    }

    @Override
    public void QuitCourseRequest(String username, String courseNumber) {
        studentCourseModel.QuitCourse(username,courseNumber);
    }


    @Override
    public void StudentCourseShowFailure() {
        studentCourseFragment.ErrorMessage();
    }

    @Override
    public void StudentCourseShowSuccessful(List<Course> courseList) {
        studentCourseFragment.ShowStudentCourse(courseList);
    }

    @Override
    public void QuitStudentCourseSuccessful(String resultData) {

    }


    @Override
    public void start() {

    }


}
