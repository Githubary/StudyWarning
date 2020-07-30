package com.example.myapplication.base.Bactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.example.myapplication.utils.ActivityListManager.ActivityManageUtil;
import com.example.myapplication.utils.network.networkListen;


/**
 * 在这个类当中，我们会写一些常用的供页面调用的方法
 */
public abstract class BaseActivity extends Activity implements networkListen.NetChangeListener {

    /**网络状态改变监听事件,接口回调*/
    public static networkListen.NetChangeListener netEvent;
    /**是否显示标题栏*/
    private boolean isShowTitle =true;
    /**是否全屏显示*/
    private boolean isShowState =true;
    /**是否沉浸状态栏*/
    private boolean isSetStatusBar = true;
    /**封装Toast对象*/
    private static Toast toast;
    /**封装Intent对象*/
    private static Intent intent;

    /**activity管理者*/
    private ActivityManageUtil activityManageUtil;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //这两个都要写在指定布局文件之前！！！

        /**去掉标题栏*/
        if(!isShowTitle){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        /**设置全屏*/
        if(!isShowState){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        /**沉浸状态栏*/
        if(!isSetStatusBar){
            //判断当前设备版本号是否为4.4以上，如果是，则通过调用setTranslucentStatus让状态栏变透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }

        // 添加到activityUtil工具类当中的list中
        activityManageUtil=ActivityManageUtil.getInstance();
        activityManageUtil.addActivity(this);

        // 初始化netEvent
        netEvent = this;

        /**设置布局页面*/
        setContentView(initLayout());
        /**初始化控件*/
        initView();
    }

    /**
     * 布局接口
     *
     * @return
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 设置是否显示标题
     * @param isShow
     */
    public void setTitle(boolean isShow){
        this.isShowTitle=isShow;
    }

    /**
     * 设置是否显示状态栏
     * @param isShow
     */
    public void setState(boolean isShow){
        this.isShowState=isShow;
    }



    /**
     * 显示长toast
     * @param msg
     */
    public void toastLong(Context context,String msg){
       toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * 显示短toast
     * @param msg
     */
    public void toastShort(Context context,String msg){
        toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示居中toast
     * @param context
     * @param msg
     */
    public void toastCenter(Context context,String msg){
        toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
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
     * 用来判断网络是否存在
     * 若不存在，则加载静态页面
     * @param netWorkState true表示有网络，false表示没有
     */
    @Override
    public void onNetChange(boolean netWorkState) { }


    /**
     * 重写Activity的生命周期方法
     * 当需要时，可以添加操作
      */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        activityManageUtil.removeActivty(this);
//        activityManageUtil.exitSystem();
        super.onDestroy();
    }

}
