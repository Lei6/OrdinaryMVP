package com.dame.ordinarymvp.presenter;


import com.dame.ordinarymvp.bean.JokesBean;
import com.dame.ordinarymvp.contract.MainContract;
import com.dame.ordinarymvp.http.BaseObserver;
import com.dame.ordinarymvp.http.HttpResult;
import com.dame.ordinarymvp.model.MainModel;
import com.dame.ordinarymvp.mvp.BasePresenter;
import com.dame.ordinarymvp.rx.RxSchedulers;

import java.util.Map;

public class MainPresenter extends BasePresenter<MainContract.MineModel, MainContract.MineView> implements MainContract.MineP {

    @Override
    public void requestData(Map<String, String> map) {
        getModel().getJokes(map)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<JokesBean>(getView()) {
                    @Override
                    public void onSuccess(HttpResult<JokesBean> result) {
                        if (result.isSuccess()) {
                            getView().showJokes(result.data);
                        }
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }

    @Override
    protected MainContract.MineModel createModel() {
        return new MainModel();
    }
}
