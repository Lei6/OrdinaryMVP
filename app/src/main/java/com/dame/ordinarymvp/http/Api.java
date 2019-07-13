package com.dame.ordinarymvp.http;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Api {

    @FormUrlEncoded
    @POST
    Observable<HttpResult<Object>> login();
}
