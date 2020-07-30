package com.example.myapplication.mvp.view.teacherClassFunction;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.myapplication.R;
import com.example.myapplication.mvp.view.CheckInWaysFragmentFunction.checkGesture;
import com.example.myapplication.mvp.view.CheckInWaysFragmentFunction.checkNumber;
import com.example.myapplication.mvp.view.CheckInWaysFragmentFunction.checkPosition;
import com.example.myapplication.mvp.view.CheckInWaysFragmentFunction.checkQRcode;
import com.example.myapplication.mvp.view.teacherClassViewPager.teacherClassMainActivity;
import com.example.myapplication.utils.FragmentPagerAdapter.mFragmentPagerAdapter;
import com.example.myapplication.utils.ScrollSelector.NumPicker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class createCheckInActivity extends AppCompatActivity implements checkNumber.checkNumberListener {


    private LinearLayout linearLayout;
    private TextView checkIn_activeTime;
    /**
     * 引入制作各种签到方式的viewpager主管理activity
     */
    private XTabLayout checkInWaysXTabLayout;
    private ViewPager checkInWaysViewPager;

    private ArrayList<String> tab_title_list=new ArrayList<>();  //存放标签页标题的数链表
    private ArrayList<Fragment> fragment_list=new ArrayList<>(); //存放viewpager管理着的fragment

    private mFragmentPagerAdapter adapter;

    private Toolbar toolbar;
    private TextView confirmCreateCheckIn;
    private EditText checkIn_number;

    /**初始化数字选择器*/
    NumPicker np;

    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_check_in);

        toolbar = findViewById(R.id.checkIn_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(this.toolbar);



        linearLayout=findViewById(R.id.checkIn_Time);
        checkIn_activeTime=findViewById(R.id.CheckIn_activeTime);
        np= new NumPicker(this);
        /**
         * 当点击取消时关闭数字滚动选择器
         */
        np.setOnCancelListener(new NumPicker.OnCancelClickListener() {
            @Override
            public void onClick() {
                np.dismiss();
            }
        });

        /**
         * 当点击阙定时，将选择到的数据添加到textView当中
         * 然后关闭数字滚动选择器
         */
        np.setOnComfirmListener(new NumPicker.onComfirmClickListener() {
            @Override
            public void onClick(int num) {
                checkIn_activeTime.setText(num+"分钟");
                np.dismiss();
            }
        });


        /**
         * 当点击时，触发滚动选择器
         */
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                np.show();
            }
        });







        checkInWaysXTabLayout=findViewById(R.id.checkIn_ways_tabLayout);
        checkInWaysViewPager=findViewById(R.id.checkIn_ways_viewpager_fragment);

        /*开始朝标题链表中添加标题*/
        tab_title_list.add("数字");
        tab_title_list.add("手势");
        tab_title_list.add("位置");
        tab_title_list.add("二维码");

        //将链表中的标题送进tablayout中
        checkInWaysXTabLayout.addTab(checkInWaysXTabLayout.newTab().setText(tab_title_list.get(0)));
        checkInWaysXTabLayout.addTab(checkInWaysXTabLayout.newTab().setText(tab_title_list.get(1)));
        checkInWaysXTabLayout.addTab(checkInWaysXTabLayout.newTab().setText(tab_title_list.get(2)));
        checkInWaysXTabLayout.addTab(checkInWaysXTabLayout.newTab().setText(tab_title_list.get(3)));

        //将fragment放进链表当中
        fragment_list.add(checkNumber.newInstance());
        fragment_list.add(checkGesture.newInstance());
        fragment_list.add(checkPosition.newInstance());
        fragment_list.add(checkQRcode.newInstance());

        //再将链表中的fragment送进viewpager给它去管理
        adapter=new mFragmentPagerAdapter(getSupportFragmentManager(),tab_title_list,fragment_list);
        checkInWaysViewPager.setAdapter(adapter);
        checkInWaysXTabLayout.setupWithViewPager(checkInWaysViewPager);//将TabLayout与Viewpager联动起来
        checkInWaysXTabLayout.setTabsFromPagerAdapter(adapter);//给TabLayout设置适配器

        checkInWaysXTabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
                checkInWaysViewPager.setCurrentItem(tab.getPosition(), false);
                checkInWaysViewPager.setCurrentItem(tab.getPosition());
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

    @Override
    public void sendValue(String value) {
        this.text=value;
    }
}
