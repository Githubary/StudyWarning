package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.myapplication.R;
import com.example.myapplication.utils.FragmentPagerAdapter.mFragmentPagerAdapter;

import java.util.ArrayList;

public class teacherClassMainActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_teacher_class_main);



        toolbar = findViewById(R.id.teacher_course_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(this.toolbar);
//        setSupportActionBar(toolbar);
        studentTabLayout = findViewById(R.id.teacher_course_tabLayout);
        viewPager =findViewById(R.id.teacher_course_viewpager_fragment);
        tab_title_list.add("课堂考勤");
        tab_title_list.add("随堂提问");
        tab_title_list.add("作业批改与发布");
        tab_title_list.add("资料上传");
//        tab_title_list.add("统计");
//        tab_title_list.add("ppt更换");


        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(0)).setIcon(R.drawable.ic_chapter));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(1)).setIcon(R.drawable.ic_teachprogram));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(2)).setIcon(R.drawable.ic_workpublish));
        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(3)).setIcon(R.drawable.ic_warning));
//        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(4)).setIcon(R.drawable.ic_kaoqin));
//        studentTabLayout.addTab(studentTabLayout.newTab().setText(tab_title_list.get(5)).setIcon(R.drawable.ic_wrongset));


        fragment_list.add(ClassCheckInFragment.newInstance());
        fragment_list.add(ClassQuestionFragment.newInstance());
        fragment_list.add(WorkpublishAndcorrectFragment.newInstance());
        fragment_list.add(FileUpFragment.newInstance());
//        fragment_list.add(StatisticsFragment.newInstance());
//        fragment_list.add(PPTchangeFragment.newInstance());

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
