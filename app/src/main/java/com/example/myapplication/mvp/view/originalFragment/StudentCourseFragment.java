package com.example.myapplication.mvp.view.originalFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.entity.Course;
import com.example.myapplication.mvp.contract.StudentCourseContract;
import com.example.myapplication.mvp.presenter.StudentCoursePresenter;
import com.example.myapplication.mvp.view.warningActivity.student_warning_Activity;
import com.example.myapplication.mvp.view.warningActivity.teacher_warning_Activity;
import com.example.myapplication.mvp.viewAdapter.TeacherCourseItemAdapter;
import com.example.myapplication.mvp.viewAdapter.StudentCourseItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseFragment extends BaseFragment implements StudentCourseContract.fragment, StudentCourseItemAdapter.QuitCourseCallBack {

    private View view;
    private ListView courseItemListView;
    private Toast toast;
    private StudentCourseContract.presenter studentCoursePresenter;
    private StudentCourseItemAdapter adapter;
    private SharedPreferences currentUser;
    private String username;

    @Override
    public void setPresenter(StudentCourseContract.presenter presenter) {
            this.studentCoursePresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mycoursefragment, container, false);
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        System.out.println("我回到了这个页面");
        studentCoursePresenter = new StudentCoursePresenter(this);
        courseItemListView = view.findViewById(R.id.list_myCourse);
        currentUser = getActivity().getSharedPreferences("currentUserMessage", Context.MODE_PRIVATE);
        username=currentUser.getString("currentUserName","");
        studentCoursePresenter.StudentCourseRequest(username);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.creatcoursetoolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void ErrorMessage() {
        toast= Toast.makeText(getActivity(),"加入课程失败！",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void ShowStudentCourse(List<Course> courseList) {
        BinderListView(courseList);
    }



    @Override
    public void QuitCourse(String courseNumber) {
        studentCoursePresenter.QuitCourseRequest(username,courseNumber);
    }
    /**
     * 绑定课程数据到ListView
     * @param courseList
     */
    private void BinderListView(List<Course> courseList) {
        //创建adapter对象s
        adapter  = new StudentCourseItemAdapter(getContext(),R.layout.s_course_item,courseList);
        adapter.setQuitCourseCallBack(this);
        //将Adapter绑定到courseItemListView中
        courseItemListView.setAdapter(adapter);
        /**为整个Item添加点击事件*/
        courseItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeActivity(student_warning_Activity.class);
            }
        });
    }

    @Override
    public void onStart() {
        System.out.println("我执行onStart");
        studentCoursePresenter.StudentCourseRequest(username);
        super.onStart();
    }
}
