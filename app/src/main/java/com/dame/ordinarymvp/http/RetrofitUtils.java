package com.dame.ordinarymvp.http;

/**
 * @author 姚明亮
 * @date 2019/7/9
 */

public class RetrofitUtils extends BaseRetrofit {
    private static Api httpService;
    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static Api getHttpService() {
        if (httpService == null) {
            httpService = init().create(Api.class);
        }
        return httpService;
    }
}
