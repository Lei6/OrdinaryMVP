package com.dame.ordinarymvp.contract;

import com.dame.ordinarymvp.bean.JokesBean;
import com.dame.ordinarymvp.http.HttpResult;
import com.dame.ordinarymvp.base.IModel;
import com.dame.ordinarymvp.base.IView;

import java.util.Map;

import io.reactivex.Observable;

public interface MainContract {

    interface MineView extends IView{
        void showJokes(JokesBean jokesBean);
    }

    interface MineModel extends IModel{
        Observable<HttpResult<JokesBean>> getJokes(Map<String,String> map);
    }

    interface MineP{
        void requestData(Map<String,String> map);
    }

}
