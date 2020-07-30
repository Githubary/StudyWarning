package com.example.myapplication.mvp.view.teacherClassViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TeacherClassFragmentPagerAdapter extends FragmentPagerAdapter {

    /**存放标签页标题*/
    private ArrayList<String> tab_title_list;
    /**存放ViewPager下的fragment*/
    private ArrayList<Fragment> fragment_list;

    public TeacherClassFragmentPagerAdapter(@NonNull FragmentManager fm, ArrayList<String> tab_title_list, ArrayList<Fragment> fragment_list) {
        super(fm);
        this.tab_title_list = tab_title_list;
        this.fragment_list = fragment_list;
    }

    /**
     * 获取到指定位置的fragment
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    /**
     * 获取fragment的长度，即个数
     * @return
     */
    @Override
    public int getCount() {
        return fragment_list.size();
    }


    /**
     * 获取标题
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title_list.get(position);
    }
}
