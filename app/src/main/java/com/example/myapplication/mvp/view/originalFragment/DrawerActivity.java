package com.example.myapplication.mvp.view.originalFragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.R;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.myapplication.mvp.view.myCourseFragmentFunction.CreateCourseActivity;
import com.example.myapplication.mvp.view.myCourseFragmentFunction.EnterCourseActivity;
import com.example.myapplication.utils.refreshHeader.QQRefreshHeader;
import com.example.myapplication.utils.refreshHeader.RefreshLayout;
import com.google.android.material.navigation.NavigationView;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {


    /**设置顶部菜单栏*/
    private Toolbar toolbar;

    /**设置顶部菜单栏居中的textView的标题*/
    private TextView title;

    /**设置底部三个导航栏的切换方式*/
    private TextView bottom_index;
    private TextView bottom_book;
    private TextView bottom_my;

    /**定义在这个页面加载的fragment*/
    private indexFragment indexfragment;
    private PersonalCenterFragment mymessagefragment;
    private StudentCourseFragment mycoursefragment;
    private TeacherCourseFragment myteachcoursefragment;

    /**定义记录当前正在使用的fragment*/
    private static Fragment usingfragment ;

    private TextView navigation_name;
    private TextView navigation_personal;

    /**可以输入文字的dialog*/
    private MyAlertInputDialog myAlertInputDialog;
    /**删除确认dialog*/
    private MyAlertDialog myAlertDialog;

    /**定义添加控件的最外层布局*/
    private LinearLayout courseLinearLayout;

    /**定义数组，存放动态添加的布局*/
    HashMap<TextView,RelativeLayout> delete_RelativeLayout ;

    /**定义一个存放delete_course的数组，方便找其下标*/
    LinkedList<TextView> textViewLinkedList;


    private Intent intent;

    private String courseNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer);

        /**关闭状态栏*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initFragment(savedInstanceState);

        delete_RelativeLayout = new HashMap<>();
        textViewLinkedList=new LinkedList<>();


        /**设置顶部工具栏*/
        toolbar = findViewById(R.id.toolbar2);
//        toolbar.inflateMenu(R.menu.indextoolbar);
        toolbar.setTitle("");
        setSupportActionBar(this.toolbar);

        /**获取抽屉布局*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        /**获取导航*/
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        /**设置监听状态*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        /**设置抽屉监听者*/
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        /**设置底部按钮监听*/
        bottom_index=findViewById(R.id.bottom_index);
        bottom_book=findViewById(R.id.bottom_book);
        bottom_my=findViewById(R.id.bottom_my);
        title=findViewById(R.id.title);
        navigation_name=findViewById(R.id.navigation_name);
        navigation_personal=findViewById(R.id.navigation_personal);


//        /**动态加载名字和个性签名在navigation中*/
//        navigation_name.setText(pref.getString("name","zhangsan"));
//        navigation_personal.setText(pref.getString("personal","hhhhhhhhh"));

        bottom_index.setOnClickListener(this);
        bottom_book.setOnClickListener(this);
        bottom_my.setOnClickListener(this);


        changeBottomColor(bottom_index,R.drawable.ic_link,false);
        changeBottomColor(bottom_book,R.drawable.ic_book2,true);
        changeBottomColor(bottom_my,R.drawable.ic_wode,false);





        /**
         * 添加下拉刷新功能
         */

//        listView = (ListView) findViewById(R.id.listView);
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        if (refreshLayout != null) {
            // 刷新状态的回调
            refreshLayout.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // 延迟1秒后刷新成功
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.refreshComplete();
//                            if (listView != null) {
//                                listView.setAdapter(new MainAdapter());
//                            }
                        }
                    }, 1000);
                }
            });
        }
        QQRefreshHeader header  = new QQRefreshHeader(this);
        refreshLayout.setRefreshHeader(header);
        refreshLayout.autoRefresh();

    }

    /**
     * 为页面加载初始状态的fragment
     */
    public void initFragment(Bundle savedInstanceState) {
                     //判断activity是否重建，如果不是，则不需要重新建立fragment.
        savedInstanceState=null;
          if(savedInstanceState==null) {
              FragmentManager fm = getSupportFragmentManager();
              FragmentTransaction ft = fm.beginTransaction();

                  SharedPreferences pref = getSharedPreferences("currentUserMessage",MODE_PRIVATE);
                  if(pref.getString("identity","").equals("student")) {
                      usingfragment = mycoursefragment = new StudentCourseFragment();
                      ft.replace(R.id.fragment_book, usingfragment).commit();
                  }else if(pref.getString("identity","").equals("teacher")){
                      usingfragment = myteachcoursefragment = new TeacherCourseFragment();
                      ft.replace(R.id.fragment_book, usingfragment).commit();
                  }
          }
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 将写在res/menu/drawer下的文件下的新增item，添加到菜单栏里
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.indextoolbar, menu);
        return true;
    }

    /**
     * toolbar中的item如果受到点击，可以在这里添加点击事件
     * 需要在AndroidManifest.xml里设置父活动，用以更改home和up的操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_search:
                Toast.makeText(this,"1111",Toast.LENGTH_LONG).show();
                break;
            case R.id.saoyisao:
                if (!initPermission()) {
                    new AlertDialog.Builder(this)
                            .setMessage("没有开启摄像机权限，是否去设置开启？")
                            .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //调用系统内部去开启权限
                                    ApplicationInfo(DrawerActivity.this);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                    break;
                }
                break;
            case R.id.enterCourse:
                SharedPreferences pref = getSharedPreferences("currentUserMessage",MODE_PRIVATE);
                if(pref.getString("identity","").equals("student")){
                    changeActivity(EnterCourseActivity.class);
                }else{
                    changeActivity(CreateCourseActivity.class);
                }
                break;

            }
        return super.onOptionsItemSelected(item);
        }



    /**
     * 跳转页面
     * @param activity
     */
    public void changeActivity(Class activity){
        intent = new Intent(getApplicationContext(),activity);
        startActivity(intent);
    }

    /**
     * navigation中的item如果受到点击，可以在这里添加点击事件
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
//            replaceFragment(new PersonalCenterFragment(),"我的",false);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        /**在点击完后，关闭抽屉*/
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 创建班级的弹出框
     * @param context
     * @param msg
     */
    void myAlertInputDialog(Context context,String msg){



//        myAlertInputDialog = new MyAlertInputDialog(context).builder()
//                .setTitle(msg)
//                .setEditText("");
//        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ok();
//                myAlertInputDialog.dismiss();
//            }
//        }).setNegativeButton("取消", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancel();
//                myAlertInputDialog.dismiss();
//            }
//        });
//        myAlertInputDialog.show();
    }
//
//    private void cancel() {
//
//    }
//
//    private void ok() {
//        /**往数据库里插入一条数据*/
//        /**并通知适配器更新*/
//        courseNumber=myAlertInputDialog.getResult();
//        Bundle bundle = new Bundle();
//        bundle.putString("courseNumber",courseNumber);
//        usingfragment.setArguments(bundle);
//    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bottom_index:
                if(indexfragment==null){
                    indexfragment=new indexFragment();
                }
                changeBottomColor(bottom_index,R.drawable.ic_link2,true);
                changeBottomColor(bottom_book,R.drawable.ic_book,false);
                changeBottomColor(bottom_my,R.drawable.ic_wode,false);
                switchContent(usingfragment,indexfragment,"推荐链接");
                break;
            case R.id.bottom_book:
                changeBottomColor(bottom_book,R.drawable.ic_book2,true);
                changeBottomColor(bottom_my,R.drawable.ic_wode,false);
                changeBottomColor(bottom_index,R.drawable.ic_link,false);
                SharedPreferences pref = getSharedPreferences("currentUserMessage",MODE_PRIVATE);
                if(pref.getString("identity","").equals("student")) {
                    if (mycoursefragment == null) {
                        mycoursefragment = new StudentCourseFragment();
                        switchContent(usingfragment,mycoursefragment,"我的课程");
                    }else {
                        switchContent(usingfragment,mycoursefragment,"我的课程");
                    }
                }else if(pref.getString("identity","").equals("teacher")){
                    if(myteachcoursefragment==null){
                        myteachcoursefragment=new TeacherCourseFragment();
                        switchContent(usingfragment,myteachcoursefragment,"我的课程");
                    }else {
                        switchContent(usingfragment,myteachcoursefragment,"我的课程");
                    }
                }
                break;
            case R.id.bottom_my:
                if(mymessagefragment==null){
                    mymessagefragment=new PersonalCenterFragment();
                }
                changeBottomColor(bottom_my,R.drawable.ic_wode2,true);
                changeBottomColor(bottom_index,R.drawable.ic_link,false);
                changeBottomColor(bottom_book,R.drawable.ic_book,false);
                switchContent(usingfragment,mymessagefragment,"个人中心");
                break;
        }
    }


    public void changeBottomColor(TextView textView,int i,boolean b){
        Drawable drawable=getResources().getDrawable(i);
        drawable.setBounds(1,1,70,70);
        textView.setCompoundDrawablesRelative(null,drawable,null,null);
        if(b){
            textView.setTextColor(0xFF00BFFF);
        }else {
            textView.setTextColor(0xFF707070);
        }
    }




    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     * @param willHide
     * @param willShow
     */
    public void switchContent(Fragment willHide, Fragment willShow,String msg) {
        if (usingfragment != willShow) {
            usingfragment = willShow;

            FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画
            FragmentTransaction ft = fm.beginTransaction();
            if (!willShow.isAdded()) {    // 先判断是否被add过
                ft.hide(willHide).add(R.id.fragment_book, willShow).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(willHide).show(willShow).commit(); // 隐藏当前的fragment，显示下一个
            }
            title.setText(msg);
        }
    }



    //查看是否开启摄像头权限

    private boolean initPermission() {
        //需要在Android里面找到你要开的权限
        String permissions = Manifest.permission.CAMERA;
        boolean ret = false;
        //Android 6.0以上才有动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//permission granted 说明权限开了
            ret = PermissionChecker.checkSelfPermission(this, permissions) == PermissionChecker.PERMISSION_GRANTED;
        }
        return ret;
    }

    /**
     * 应用信息界面
     * 打开自己手机去看权限
     *
     * @param activity
     */
    public static void ApplicationInfo(Activity activity) {

        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
            }
            activity.startActivity(localIntent);
        } catch (Exception e) {

        }
    }








}


