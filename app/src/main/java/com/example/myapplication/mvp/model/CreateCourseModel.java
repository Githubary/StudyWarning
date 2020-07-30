package com.example.myapplication.mvp.model;

import com.example.myapplication.entity.Course;
import com.example.myapplication.utils.httpUtils.OkHttpUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CreateCourseModel {
    private RequestBody requestBody;

    private Course course;

    public static final MediaType ContentType = MediaType.parse("image/jpg");

    private final String url = "http://47.92.142.206:8080/createCourse";

    private CreateCourseModel.CreateCourseDao createCourseDao;

    public void setCreateCourseDao(CreateCourseModel.CreateCourseDao createCourseDao){
        this.createCourseDao=createCourseDao;
    }

    /**
     * 创建课程的网络请求
     * @param username
     * @param courseName
     * @param courseNumber
     * @param courseTeacher
     * @param file
     */
    public void createCourseHttp(String username, String courseName,String courseNumber,String courseTeacher, File file ){

        String FileName=null;
        if(file!=null){
            FileName = file.getName();
            System.out.println("获取到的文件名"+FileName);
            requestBody = RequestBody.create(ContentType,file);
        }else{
            requestBody=RequestBody.create(ContentType,"");
        }
        RequestBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("username",username)
                .addFormDataPart("courseName",courseName)
                .addFormDataPart("courseNumber",courseNumber)
                .addFormDataPart("courseTeacher",courseTeacher)
                .addFormDataPart("file",FileName,requestBody)
                .build();
        OkHttpUtils.postMultipartAsync(url, multipartBody, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                createCourseDao.createCourseFailure(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                createCourseDao.createCourseSuccessful(result);
            }
        });
    }

    public interface CreateCourseDao{
        void createCourseFailure(Exception e);
        void createCourseSuccessful(String resultDate);
    }

}
