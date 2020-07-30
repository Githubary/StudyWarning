package com.example.myapplication.utils.ViewPager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Activity activity;
    private List<View> list;

    public ViewPagerAdapter() {

    }

    public ViewPagerAdapter(Activity activity, List<View> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list.size() == 1) {// 一张图片时不用流动
            return list.size();
        }
        return Integer.MAX_VALUE;
    }




    @Override
    public Object instantiateItem(ViewGroup v, int i) {
        // container: 容器: ViewPager
        // position: 当前要显示条目的位置 0 -> 3
        //newPosition = position % 4
        int newPosition = i % list.size();
        View img = list.get(newPosition);
        // a. 把View对象添加到container中
        v.addView(img);
        // b. 把View对象返回给框架, 适配器
        return img;

    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int position, Object object) {
        viewGroup.removeView((View) object);
    }
}