package com.example.myapplication.mvp.view.studentClassViewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.myapplication.R;
import com.example.myapplication.utils.FragmentPagerAdapter.mFragmentPagerAdapter;

import java.util.ArrayList;

public class studentClassMainActivity extends AppCompatActivity {


    private XTabLayout studentTabLayout;
    private ViewPager viewPager;
    private ArrayList<String> tab_title_list = new ArrayList<>();//存放标签页标题
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment
    private mFragmentPagerAdapter adapter;//适配器

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_main);



        toolbar = findViewById(R.id.student_course_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(this.toolbar);
        studentTabLayout = findViewById(R.id.student_course_tabLayout);
        viewPager =findViewById(R.id.student_course_viewpager_fragment);
        tab_title_list.add("章节内容");
        tab_title_list.add("课堂活动");
        tab_title_list.add("做作业");
        tab_title_list.add("学习记录");
        tab_title_list.add("资料");


        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(0)).setIcon(R.drawable.ic_chapter));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(1)).setIcon(R.drawable.ic_teachprogram));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(2)).setIcon(R.drawable.ic_workpublish));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(3)).setIcon(R.drawable.ic_kaoqin));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(4)).setIcon(R.drawable.ic_kaoqin));


        fragment_list.add(ChaptersFragment.newInstance());
        fragment_list.add(ClassActivityFragment.newInstance());
        fragment_list.add(HomeworkFragment.newInstance());
        fragment_list.add(StudyRecordFragment.newInstance());
        fragment_list.add(StudentFileFragment.newInstance());

        adapter = new mFragmentPagerAdapter(getSupportFragmentManager(), tab_title_list, fragment_list);
        viewPager.setAdapter(adapter);//给ViewPager设置适配器
        studentTabLayout.setupWithViewPager(viewPager);//将TabLayout与Viewpager联动起来
        studentTabLayout.setTabsFromPagerAdapter(adapter);//给TabLayout设置适配器



        studentTabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
                viewPager.setCurrentItem(tab.getPosition(), false);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.voidtoolbar, menu);
        return true;
    }
}
