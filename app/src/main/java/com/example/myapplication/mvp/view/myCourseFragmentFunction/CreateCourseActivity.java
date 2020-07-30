package com.example.myapplication.mvp.view.myCourseFragmentFunction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mvp.contract.CreateCourseContract;
import com.example.myapplication.mvp.presenter.CreateCoursePresenter;
import com.example.myapplication.utils.Photo.OkhttpUpPhoto;
import com.hb.dialog.dialog.ConfirmDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class CreateCourseActivity extends AppCompatActivity implements CreateCourseContract.activity,View.OnClickListener{

    /**设置控件*/
    private EditText create_course_name;
    private EditText create_course_number;
    private EditText create_course_teacher;
    private TextView confirm_create_course;
    private TextView up_course_cover;
    private ImageView course_cover;

    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;

    /**设置存取控制库*/
    private SharedPreferences.Editor editor;

    private String courseName;
    private String courseNumber;
    private String courseTeacher;
    private String courseCover=null;

    /**照片本身，上传至服务器*/
    private File courseCoverFile=null;

    /**注入presenter*/
    private CreateCourseContract.presenter createCoursePresenter;
    private CreateCourseContract.activity createCourseActivity;

    SharedPreferences currentUser;

    OkhttpUpPhoto okhttpUpPhoto;

    private Toast toast;

    @Override
    public void setPresenter(CreateCourseContract.presenter presenter) {
        this.createCoursePresenter=presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTitle(false);
//        setState(false);
        super.onCreate(savedInstanceState);

        /**关闭状态栏*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_course);

        /**初始化控件*/
        create_course_name=findViewById(R.id.create_course_name);
        create_course_number=findViewById(R.id.create_course_number);
        create_course_teacher=findViewById(R.id.create_course_teacher);
        confirm_create_course=findViewById(R.id.confirm_create_course);
        up_course_cover=findViewById(R.id.up_course_background);
        course_cover=findViewById(R.id.course_cover);

        /**初始化数据库*/
        editor=getSharedPreferences("coursemessage",MODE_PRIVATE).edit();


        /**设置控件点击事件*/
        confirm_create_course.setOnClickListener(this);
        up_course_cover.setOnClickListener(this);


        createCourseActivity=this;
        /**创建实际的控制层*/
        createCoursePresenter = new CreateCoursePresenter(createCourseActivity);

        currentUser =getSharedPreferences("currentUserMessage",MODE_PRIVATE);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            /**确认创建课程*/
            case R.id.confirm_create_course:
                courseName=create_course_name.getText().toString().trim();
                courseNumber=create_course_number.getText().toString().trim();
                courseTeacher=create_course_teacher.getText().toString().trim();
                if(courseNumber.equals("")||courseName.equals("")||courseTeacher.equals("")){
                    System.out.println("创建课程的信息都是空的");
                    showInputNoNull(this);
                }else{
                    System.out.println("照片路径："+courseCover);
                    confirmCreate(this,courseName,courseNumber,courseTeacher);
                }
                break;
            /**打开系统相册*/
            case R.id.up_course_background:
                if (ContextCompat.checkSelfPermission(CreateCourseActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreateCourseActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //打开系统相册
                    okhttpUpPhoto=OkhttpUpPhoto.getInstance();
                    okhttpUpPhoto.openAlbum(this);
                }
                break;
        }
    }



    private void confirmCreate(final Context context, final String courseName, final String courseNumber, final String courseTeacher) {

        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("确认创建该课程吗");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                editor.putString("courseName",courseName);
                editor.putString("courseNumber",courseNumber);
                editor.putString("courseTeacher",courseTeacher);
                editor.apply();
                if(courseCover==null){
//                    courseCoverFile =new File("main\\res\\drawable\\defaultcoursebackground.jpg");
                }else{
                    courseCoverFile = new File(courseCover);
                }
                String username = currentUser.getString("currentUserName","");
                createCoursePresenter.CreateCourseRequest(username,courseName,courseNumber,courseTeacher,courseCoverFile);
            }

            @Override
            public void cancel() {
                Toast.makeText(context,"你没有创建该课程",Toast.LENGTH_SHORT).show();
            }
        });
        confirmDialog.show();
    }


    public void showInputNoNull(final Context context){
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("课程名称、课程号、任课教师不能为空！");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
            }
            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }

    public void gotoTeacherCourseFragment() {
        CreateCourseActivity.this.finish();
    }

    @Override
    public void ErrorMessage(Exception e) {
        e.printStackTrace();
        toast=Toast.makeText(this,"创建课程失败了！",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void CreateCourseDataProcess(String resultDate) {
        System.out.println("创建课程后返回的数据" + resultDate);
        //在这里对返回内容进行处理
        if (resultDate.equals("创建课程成功")) {
            gotoTeacherCourseFragment();
        } else if (resultDate.equals("该课程号已存在")) {
            toast = Toast.makeText((Context) createCourseActivity, "课程号已经存在", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    okhttpUpPhoto.openAlbum(this);
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    /**
     * 当选择完图片后会回到onActivityResult方法中，在该方法中来处理图片。
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            if (Build.VERSION.SDK_INT >= 19) {
                courseCover=okhttpUpPhoto.handleImageOnKitkat(this,data);
                displayImage(courseCover);
            } else {
                courseCover=okhttpUpPhoto.handleImageBeforeKitkat(this,data);
                displayImage(courseCover);
            }
        }else if (requestCode == 2 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            course_cover.setImageBitmap(photo);
        }
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            course_cover.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

}
