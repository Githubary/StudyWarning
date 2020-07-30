package com.example.myapplication.utils.alertUtils;

import android.content.Context;
import android.view.View;

import com.example.myapplication.R;
import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.dialog.LoadingDialog;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;


/**
 * 提示框、确认框、输入框、加载框管理工具
 * 特殊的，通过导包获取的别人封装好的工具
 */
public  class DialogUtil {
    /**删除确认dialog*/
    private MyAlertDialog myAlertDialog;
    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;
    /**可以输入文字的dialog*/
    private MyAlertInputDialog myAlertInputDialog;
    /**Loading的dialog*/
    private LoadingDialog loadingDialog;

    /**
     * 删除确认的方法
     * @param context 当前场景，背景，上下文
     * @param title   确认框主题
     * @param msg     确认信息
     */
    void ConfirmDelete(Context context,String title, String msg){
        this.myAlertDialog=new MyAlertDialog(context).builder()
                .setTitle(title)
                .setMsg(msg)
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        success();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        failure();
                    }
                });
        myAlertDialog.show();
    }

    /**
     *  确认提示的方法
     * @param context 当前场景，背景，上下文
     */
    void confirmHint(Context context){
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("提示");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }


    void myAlertInputDialog(Context context){
        myAlertInputDialog = new MyAlertInputDialog(context).builder()
                .setTitle("请输入你要加入的班级号")
                .setEditText("");
        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok();
                myAlertInputDialog.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }



    /**
     * 加载页面时等待过程
     * @param context 当前场景，背景，上下文
     */
    void loading(Context context){
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setMessage("loading");
        loadingDialog.show();
    }

    //确认之后进行的操作还没有写

    private void success() {

    }

    private void failure() {

    }



    private void cancel() {

    }

    private void ok() {

    }
}
