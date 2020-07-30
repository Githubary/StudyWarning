package com.example.myapplication.mvp.model;

import com.example.myapplication.entity.Course;
import com.example.myapplication.utils.httpUtils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class TeacherCourseModel {

    private List<Course> courseList;

    private final String showUrl ="http://47.92.142.206:8080/backAllCourse?username=";
    private final String deleteUrl ="http://47.92.142.206:8080/deleteCourse?courseNumber=";
    private TeacherCourseDao teacherCourseDao;

    public void setTeacherCourseDao(TeacherCourseDao teacherCourseDao){
        this.teacherCourseDao = teacherCourseDao;
    }


    public void TeacherCourseShow(String username){
        OkHttpUtils.getAsync(showUrl+username, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                teacherCourseDao.TeacherCourseShowFailure(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                courseList = resultToObjectArray(result);
                teacherCourseDao.TeacherCourseShowSuccessful(courseList);
            }
        });
    }

    public void DeleteCourse(String deleteCourseNumber){
        OkHttpUtils.getAsync(deleteUrl+deleteCourseNumber, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                teacherCourseDao.TeacherCourseShowFailure(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                teacherCourseDao.DeleteTeacherCourseSuccessful(result);
            }
        });
    }


    private List<Course> resultToObjectArray(String result) {
        Gson gson = new Gson();
        //如果是多条json数据，使用List来构造返回一个List对象的javaBean
        courseList = gson.fromJson(result,new TypeToken<List<Course>>(){}.getType());
        return  courseList;
    }

    public interface TeacherCourseDao{
        void TeacherCourseShowFailure(Exception e);
        void TeacherCourseShowSuccessful(List<Course> courseList);
        void DeleteTeacherCourseSuccessful(String resultData);
    }

}
