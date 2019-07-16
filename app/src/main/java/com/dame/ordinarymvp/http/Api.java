package com.dame.ordinarymvp.http;

import com.dame.ordinarymvp.bean.JokesBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Api {

    @FormUrlEncoded
    @POST("jokes/list")
    Observable<HttpResult<JokesBean>> getJokes(@FieldMap Map<String,String> map);
}
