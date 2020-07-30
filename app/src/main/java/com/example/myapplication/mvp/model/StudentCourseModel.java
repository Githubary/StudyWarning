package com.example.myapplication.mvp.model;

import com.example.myapplication.entity.Course;
import com.example.myapplication.entity.QuitCourse;
import com.example.myapplication.utils.httpUtils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class StudentCourseModel {

    private StudentCourseDao studentCourseDao;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody requestBody;
    private String showUrl = "http://47.92.142.206:8080/backStuCourMess?username=";
    private String quitUrl = "http://47.92.142.206:8080/quitStuCourse";
    private String joinUrl = "http://47.92.142.206:8080/joinCourse";

    private List<Course> courseList;



    public void setStudentCourseDao(StudentCourseDao studentCourseDao){
        this.studentCourseDao=studentCourseDao;
    }

    public void StudentCourseShow(String username){
        OkHttpUtils.getAsync(showUrl + username, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                studentCourseDao.StudentCourseShowFailure();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                System.out.println(result);
                courseList=resultToObjectArray(result);
                System.out.println("拿到的学生班级："+courseList.get(0));
                studentCourseDao.StudentCourseShowSuccessful(courseList);
            }
        });
    }

    public void QuitCourse(String username, String courseNumber) {
        QuitCourse quitCourse =  new QuitCourse();
        quitCourse.setUsername(username);
        quitCourse.setCourseNumber(courseNumber);
        String json = new Gson().toJson(quitCourse);
        requestBody = RequestBody.create(JSON,json);
        OkHttpUtils.postRequestBodyAsync(quitUrl, requestBody, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                studentCourseDao.QuitStudentCourseSuccessful(result);
            }
        });
    }


    public interface StudentCourseDao{
        void StudentCourseShowFailure();
        void StudentCourseShowSuccessful(List<Course> courseList);
        void QuitStudentCourseSuccessful(String resultData);
    }

    private List<Course> resultToObjectArray(String result) {
        Gson gson = new Gson();
        //如果是多条json数据，使用List来构造返回一个List对象的javaBean
        courseList = gson.fromJson(result,new TypeToken<List<Course>>(){}.getType());
        return  courseList;
    }

}
