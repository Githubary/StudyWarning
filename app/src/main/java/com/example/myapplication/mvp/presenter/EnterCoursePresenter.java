package com.example.myapplication.mvp.presenter;

import com.example.myapplication.entity.EnterCourse;
import com.example.myapplication.mvp.contract.EnterCourseContract;
import com.example.myapplication.mvp.model.EnterCourseModel;

import java.util.List;

public class EnterCoursePresenter implements EnterCourseContract.presenter, EnterCourseModel.EnterCourseDao {

    private EnterCourseContract.activity enterCourseActivity;
    private EnterCourseModel enterCourseModel;

    public EnterCoursePresenter(EnterCourseContract.activity enterCourseActivity){
        this.enterCourseActivity=enterCourseActivity;
        enterCourseModel = new EnterCourseModel();
        enterCourseModel.setEnterCourseDao(this);
        enterCourseActivity.setPresenter(this);
    }

    /**
     * 查询课程的请求
     * @param courseNumber
     */
    @Override
    public void enterCourseShowRequest(String courseNumber) {
        enterCourseModel.EnterCourse(courseNumber);
    }

    @Override
    public void JoinTheCourseRequest(String username, String courseNumber) {
        enterCourseModel.JoinTheCourse(username,courseNumber);
    }


    /**
     * 查询课程失败的回调
     */
    @Override
    public void EnterCourseShowFailure() {
        enterCourseActivity.CourseNotExit();
    }

    /**
     * 查询课程成功的回调
     * @param enterCourseList
     */
    @Override
    public void EnterCourseShowSuccessful(List<EnterCourse> enterCourseList) {
        enterCourseActivity.ShowEnterCourse(enterCourseList);
    }

    @Override
    public void JoinTheCourseSuccessful(String result) {
        enterCourseActivity.JoinTheCourseResult(result);
    }

    @Override
    public void start() {

    }
}
