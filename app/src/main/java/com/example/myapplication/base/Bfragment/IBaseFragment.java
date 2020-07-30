package com.example.myapplication.base.Bfragment;

public interface IBaseFragment<T> {
    /**
     * 设置 Fragment 的 Presenter引用
     * @param presenter
     */
    void setPresenter(T presenter);
}
