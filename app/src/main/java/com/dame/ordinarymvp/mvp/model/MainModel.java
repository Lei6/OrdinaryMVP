package com.dame.ordinarymvp.mvp.model;

import com.dame.ordinarymvp.bean.JokesBean;
import com.dame.ordinarymvp.contract.MainContract;
import com.dame.ordinarymvp.http.HttpResult;
import com.dame.ordinarymvp.http.RetrofitUtils;
import com.dame.ordinarymvp.base.BaseModel;

import java.util.Map;

import io.reactivex.Observable;

public class MainModel extends BaseModel implements MainContract.MineModel {
    @Override
    public Observable<HttpResult<JokesBean>> getJokes(Map<String, String> map) {
        return RetrofitUtils.getHttpService().getJokes(map);
    }

}
