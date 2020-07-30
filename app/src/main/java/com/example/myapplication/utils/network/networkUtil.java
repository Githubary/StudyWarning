package com.example.myapplication.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络监控工具
 */
public class networkUtil {
    /**定义三个代表网络状态的静态常量*/
    public static final int NETTYPE_WIFI = 1;
    public static final int NETTYPE_CMWAP= 2;
    public static final int NETTYPE_CMNET= 3;

    /**
     * 判断传入环境是否具有网络
     * @param context
     * @return networkInfo 网络实例
     */
    public static boolean networkIsAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if(connectivityManager!=null){
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo!=null&&networkInfo.isConnectedOrConnecting();
    }




}
