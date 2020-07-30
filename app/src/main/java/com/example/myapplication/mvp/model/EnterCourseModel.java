package com.example.myapplication.mvp.model;

import com.example.myapplication.entity.EnterCourse;
import com.example.myapplication.entity.QuitCourse;
import com.example.myapplication.utils.httpUtils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class EnterCourseModel {

    private EnterCourseDao enterCourseDao;
    private List<EnterCourse> enterCourseList;

    private final String url = "http://47.92.142.206:8080/backCourseMess?courseNumber=";
    private String joinUrl = "http://47.92.142.206:8080/joinCourse";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody requestBody;


    public void setEnterCourseDao(EnterCourseDao enterCourseDao){
        this.enterCourseDao = enterCourseDao;
    }

    public void EnterCourse(String courseNumber) {
        System.out.println("准备请求了");
        OkHttpUtils.getAsync(url + courseNumber, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                enterCourseDao.EnterCourseShowFailure();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                enterCourseList = resultToObjectArray(result);
                enterCourseDao.EnterCourseShowSuccessful(enterCourseList);
            }
        });
    }

    private List<EnterCourse> resultToObjectArray(String result) {
        System.out.println("result:"+result);
        if(result!=null){
            Gson gson = new Gson();
            //如果是多条json数据，使用List来构造返回一个List对象的javaBean
            enterCourseList = gson.fromJson(result,new TypeToken<List<EnterCourse>>(){}.getType());
            return  enterCourseList;
        }else{
            return null;
        }
    }

    public void JoinTheCourse(String username, String courseNumber) {
        QuitCourse quitCourse =  new QuitCourse();
        quitCourse.setUsername(username);
        quitCourse.setCourseNumber(courseNumber);
        String json = new Gson().toJson(quitCourse);
        requestBody = RequestBody.create(JSON,json);
        OkHttpUtils.postRequestBodyAsync(joinUrl, requestBody, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                enterCourseDao.JoinTheCourseSuccessful(result);
            }
        });
    }

    public interface EnterCourseDao{
        void EnterCourseShowFailure();
        void EnterCourseShowSuccessful(List<EnterCourse> enterCourseList);
        void JoinTheCourseSuccessful(String result);
    }

}
