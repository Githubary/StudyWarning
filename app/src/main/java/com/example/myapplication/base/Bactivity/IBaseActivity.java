package com.example.myapplication.base.Bactivity;

public interface IBaseActivity<T> {

    /**
     * 设置Activity 的 Presenter引用
     * @param presenter
     */
    void setPresenter(T presenter);
}
