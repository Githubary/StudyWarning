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
import com.example.myapplication.mvp.contract.TeacherCourseContract;
import com.example.myapplication.mvp.presenter.TeacherCoursePresenter;
import com.example.myapplication.mvp.view.warningActivity.teacher_warning_Activity;
import com.example.myapplication.mvp.viewAdapter.TeacherCourseItemAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;


public class TeacherCourseFragment extends BaseFragment implements TeacherCourseContract.fragment, TeacherCourseItemAdapter.DeleteCourseCallBack {


        private ListView courseItemListView;
        private View view;
        private TeacherCourseContract.presenter teacherCoursePresenter;
        private SharedPreferences currentUser;
        private String username;
        private TeacherCourseItemAdapter adapter;
        private Toast toast;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.myteachcoursefragment, container, false);
            setHasOptionsMenu(true);
            return view;
        }

    @Override
    public void setPresenter(TeacherCourseContract.presenter presenter) {
            this.teacherCoursePresenter=presenter;
    }


        @SneakyThrows
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            teacherCoursePresenter=new TeacherCoursePresenter(this);
            /**ListView*/
            courseItemListView=view.findViewById(R.id.list_courseItem);
            currentUser = getActivity().getSharedPreferences("currentUserMessage",Context.MODE_PRIVATE);
            username=currentUser.getString("currentUserName","");
            /**在这一块准备好请求服务端拿到的List<Course>数据*/
            System.out.println("当前登录用户的用户名"+username);
            //在这个页面restart之后，执行这个
            teacherCoursePresenter.TeacherCourseRequest(username);

            super.onActivityCreated(savedInstanceState);
        }




    @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu.clear();
            inflater.inflate(R.menu.creatcoursetoolbar, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }


    /**
     * 绑定课程数据到ListView
     * @param courseList
     */
    private void BinderListView(List<Course> courseList) {
        //创建adapter对象
        adapter  = new TeacherCourseItemAdapter(getContext(),R.layout.t_course_item,courseList);
        adapter.setDeleteCourseCallBack(this);
        //将Adapter绑定到courseItemListView中
        courseItemListView.setAdapter(adapter);
        /**为整个Item添加点击事件*/
        courseItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeActivity(teacher_warning_Activity.class);
            }
        });
        }

    @Override
    public void ErrorMessage(Exception e) {
        toast= Toast.makeText(getActivity(),"创建课程失败了！",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void ShowTeacherCourse(List<Course> courseList) {
        BinderListView(courseList);
    }

    @Override
    public void onResume() {
        teacherCoursePresenter.TeacherCourseRequest(username);
        super.onResume();
    }

    @Override
    public void DeleteCourse(String deleteCourseNumber) {
        teacherCoursePresenter.DeleteCourseRequest(deleteCourseNumber);
    }
}


