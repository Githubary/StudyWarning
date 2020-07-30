package com.example.myapplication.utils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.myapplication.base.Bactivity.BaseActivity;

/**
 * 监听网络变化
 */
public class networkListen extends BroadcastReceiver {

    //一旦网络发生变化，这个方法就会执行
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean netWorkState = networkUtil.networkIsAvailable(context);

            if (BaseActivity.netEvent != null)
                BaseActivity.netEvent.onNetChange(netWorkState);
        }
    }


    public interface NetChangeListener{
        void onNetChange(boolean netWorkState);
    }

}
