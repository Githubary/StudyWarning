package com.example.myapplication.utils.ActivityList;

import android.app.Activity;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;


/**
 * activity管理类
 * 将所有的activity加入到List当中
 * 单例模式下管理activity
 */
public class activityManageUtil {

    /**创建一个集合， 用来存放activity*/
    private List<Activity> activityList = new ArrayList<>();

    /**创建一个例子，用来管理activity*/
    private static activityManageUtil instance;

    /**
     * 单例模式下，获取唯一的ExitApplication实例
     * @return
     */
    public static synchronized activityManageUtil getInstance(){
        //如果最开始ArrayList中没有实例的话，我们先new一个activityUtil
        if(null==instance){
            instance =new activityManageUtil();
        }
        return instance;
    }


    /**
     * 添加activity到List当中
     * @param activity
     */
    public void addActivity(Activity activity){
        if(activityList==null){
            activityList = new ArrayList<>();
            activityList.add(activity);
        }
    }

    /**
     * 从list当中移除activity
     * @param activity
     */
    public void removeActivty(Activity activity){
        if(activityList!=null)
            activityList.remove(activity);
    }


    /**
     * 当退出系统时候，遍历整个链表，关闭所有的activity，减少资源损耗
     */
    public void exitSystem(){
        for(Activity activity:activityList){
            if(activity!=null)
                activity.finish();
        }
        // 退出进程
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

}
