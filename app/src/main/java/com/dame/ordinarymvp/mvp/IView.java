package com.dame.ordinarymvp.mvp;

public interface IView {

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 隐藏loading
     */
    void hideLoading();

    /**
     * 显示toast
     */
    void showError(String msg);

}
