package com.example.myapplication.mvp.presenter;

import com.example.myapplication.entity.Course;
import com.example.myapplication.mvp.contract.TeacherCourseContract;
import com.example.myapplication.mvp.model.TeacherCourseModel;
import java.util.List;

public class TeacherCoursePresenter implements TeacherCourseContract.presenter, TeacherCourseModel.TeacherCourseDao {

    private TeacherCourseContract.fragment teacherCourseFragment;
    private TeacherCourseModel teacherCourseModel;

    public TeacherCoursePresenter(TeacherCourseContract.fragment teacherCourseFragment){
        this.teacherCourseFragment=teacherCourseFragment;
        teacherCourseModel = new TeacherCourseModel();
        teacherCourseModel.setTeacherCourseDao(this);
        teacherCourseFragment.setPresenter(this);
    }

    /***
     * 去model层请求课程数据
     * @param username
     */
    @Override
    public void TeacherCourseRequest(String username){
        teacherCourseModel.TeacherCourseShow(username);
    }

    /**
     * 去model层删除课程
     * @param deleteCourseNumber
     */
    @Override
    public void DeleteCourseRequest(String deleteCourseNumber) {
        teacherCourseModel.DeleteCourse(deleteCourseNumber);
    }

    @Override
    public void TeacherCourseShowFailure(Exception e) {
        teacherCourseFragment.ErrorMessage(e);
    }


    @Override
    public void TeacherCourseShowSuccessful(List<Course> courseList) {
        teacherCourseFragment.ShowTeacherCourse(courseList);
    }

    @Override
    public void DeleteTeacherCourseSuccessful(String resultData) {

    }

    @Override
    public void start() {

    }

}
