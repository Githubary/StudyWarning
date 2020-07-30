package com.example.myapplication.mvp.view.myCourseFragmentFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.entity.Course;
import com.example.myapplication.entity.EnterCourse;
import com.example.myapplication.mvp.contract.EnterCourseContract;
import com.example.myapplication.mvp.presenter.EnterCoursePresenter;
import com.example.myapplication.mvp.presenter.StudentCoursePresenter;
import com.example.myapplication.mvp.view.warningActivity.student_warning_Activity;
import com.example.myapplication.mvp.viewAdapter.EnterCourseItemAdapter;
import com.example.myapplication.mvp.viewAdapter.StudentCourseItemAdapter;

import java.util.List;

public class EnterCourseActivity extends AppCompatActivity implements EnterCourseContract.activity, EnterCourseItemAdapter.EnterCourseCallBack {


    private EditText searchCourseEdit;
    private ListView listViewCourse;
    private EnterCourseItemAdapter adapter;
    private EnterCourseContract.presenter enterCoursePresenter;
    private String username;
    private String courseNumber;
    SharedPreferences  currentUser;
    Toast toast;

    @Override
    public void setPresenter(EnterCourseContract.presenter presenter) {
        this.enterCoursePresenter=presenter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /**关闭状态栏*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_course);
        searchCourseEdit = findViewById(R.id.searchCourse);
        listViewCourse = findViewById(R.id.list_viewCourse);
        currentUser = this.getSharedPreferences("currentUserMessage", Context.MODE_PRIVATE);
        username=currentUser.getString("currentUserName","");
        enterCoursePresenter = new EnterCoursePresenter(this);

        searchCourseEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                courseNumber = searchCourseEdit.getText().toString();
                System.out.println(courseNumber);
                if(courseNumber.length()>=5){
                    enterCoursePresenter.enterCourseShowRequest(courseNumber);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
    /**
     * 绑定课程数据到ListView
     * @param enterCourseList
     */
    private void BinderListView(List<EnterCourse> enterCourseList) {
        //创建adapter对象s
        adapter  = new EnterCourseItemAdapter(getApplicationContext(),R.layout.enter_course_list,enterCourseList);
        adapter.setEnterCourseCallBack(this);
        //将Adapter绑定到courseItemListView中
        listViewCourse.setAdapter(adapter);
    }


    @Override
    public void ShowEnterCourse(List<EnterCourse> enterCourseList) {
        BinderListView(enterCourseList);
    }

    @Override
    public void enterCourse() {
        enterCoursePresenter.JoinTheCourseRequest(username,courseNumber);
    }

    @Override
    public void CourseNotExit() {
        System.out.println("课程号不存在");
    }

    @Override
    public void JoinTheCourseResult(String result) {
        if(result.equals("没有该课程")){
            toast= Toast.makeText(this,"找不到这个课程号",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else if(result.equals("你已加入该课程")){
            toast=Toast.makeText(this,"你已加入该课程",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else if(result.equals("加入课程成功")){
            toast=Toast.makeText(this,"加入课程成功",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            EnterCourseActivity.this.finish();
        }
    }

}
