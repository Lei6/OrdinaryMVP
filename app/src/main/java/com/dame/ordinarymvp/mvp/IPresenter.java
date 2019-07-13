package com.dame.ordinarymvp.mvp;

public interface IPresenter<V extends IView> {

    /**
     * 绑定view
     */
    void attachView(V mView);

    /**
     * 解除View
     */

    void detachView();
}
